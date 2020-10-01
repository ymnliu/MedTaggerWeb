package org.ohnlp.web;

import com.onelogin.saml2.Auth;
import com.onelogin.saml2.servlet.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.ohnlp.n3c.N3CNLPEngine;
import org.ohnlp.util.BioPortalAPI;
import org.ohnlp.util.IEEditorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


@RestController
public class WebServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static N3CNLPEngine n3CNLPEngine;
    @Value("${bioportal.apikey}")
    private String bioportalAPIKey;

    /**
     * To initialize UIMA resources and type systesms
     */
    public WebServiceController() {
        n3CNLPEngine = new N3CNLPEngine();
    }

    /**
     * The index page. Display a default sentence to start.
     * 
     * @return the initial view out of index-template
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("demo");
        return indexView;
    }

    @GetMapping("/index_v0")
    public ModelAndView index_v0() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index_v0");
        indexView.addObject("input_text", "The patient had dry cough and took Aspirin yesterday.");
        return indexView;
    }

    @GetMapping("/about")
    public ModelAndView about() {
        ModelAndView aboutView = new ModelAndView();
        aboutView.setViewName("about");
        return aboutView;
    }

    @PostMapping("/acs")
    public String dummy(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        Auth auth = new Auth(request, response);
        StringBuilder sb = new StringBuilder();
        auth.processResponse(request.getRequestedSessionId());

        if (!auth.isAuthenticated()) {
            sb.append("<div class=\"alert alert-danger\" role=\"alert\">Not authenticated</div>");
        }
        List<String> errors = auth.getErrors();
        if (!errors.isEmpty()) {
            sb.append("<p>" + StringUtils.join(errors, ", ") + "</p>");
            if (auth.isDebugActive()) {
                String errorReason = auth.getLastErrorReason();
                if (errorReason != null && !errorReason.isEmpty()) {
                    sb.append("<p>" + auth.getLastErrorReason() + "</p>");
                }
            }
            sb.append("<a href=\"dologin.jsp\" class=\"btn btn-primary\">Login</a>");
        } else {
            Map<String, List<String>> attributes = auth.getAttributes();
            String nameId = auth.getNameId();
            String nameIdFormat = auth.getNameIdFormat();
            String sessionIndex = auth.getSessionIndex();
            String nameidNameQualifier = auth.getNameIdNameQualifier();
            String nameidSPNameQualifier = auth.getNameIdSPNameQualifier();
            session.setAttribute("attributes", attributes);
            session.setAttribute("nameId", nameId);
            session.setAttribute("nameIdFormat", nameIdFormat);
            session.setAttribute("sessionIndex", sessionIndex);
            session.setAttribute("nameidNameQualifier", nameidNameQualifier);
            session.setAttribute("nameidSPNameQualifier", nameidSPNameQualifier);

            String relayState = request.getParameter("RelayState");
            if (relayState != null && !relayState.isEmpty() && !relayState.equals(ServletUtils.getSelfRoutedURLNoQuery(request)) &&
                    !relayState.contains("/dologin.jsp")) { // We don't want to be redirected to login.jsp neither
                response.sendRedirect(request.getParameter("RelayState"));
            } else {

                if (attributes.isEmpty()) {
                } else {
                    Collection<String> keys = attributes.keySet();
                    for (String name : keys) {
                        sb.append("<tr><td>" + name + "</td><td>");
                        List<String> values = attributes.get(name);
                        for (String value : values) {
                            sb.append("<li>" + value + "</li>");
                        }

                        sb.append("</td></tr>");
                    }
                }
            }
        }

        return sb.toString();
    }

    @GetMapping("/sls")
    public String sls(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return "Logout.html";
    }

    @GetMapping("/attrs")
    public String showAttrs(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Boolean found = false;
        @SuppressWarnings("unchecked")
        Enumeration<String> elems = (Enumeration<String>) session.getAttributeNames();

        while (elems.hasMoreElements() && !found) {
            String value = (String) elems.nextElement();
            if (value.equals("attributes") || value.equals("nameId")) {
                found = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (found) {
            String nameId = (String) session.getAttribute("nameId");
            @SuppressWarnings("unchecked")
            Map<String, List<String>> attributes = (Map<String, List<String>>) session.getAttribute("attributes");
            if (!nameId.isEmpty()) {
                sb.append("<div><b> NameId:</b> " + nameId + "</div>");
            }

            if (attributes.isEmpty()) {
                sb.append("No attributes");
            }
            else {
                Collection<String> keys = attributes.keySet();
                for(String name :keys){
                    sb.append(name + "<br>");
                    List<String> values = attributes.get(name);
                    for(String value :values) {
                        sb.append(value + "<br>");

                    }

                }
            }
        } else {
            sb.append("<div class=\"alert alert-danger\" role=\"alert\">Not authenticated</div>");
        }

        return sb.toString();
    }


        /**
         * How to display NLP results .
         *
         * @return the updated view out of index-template
         */
    @PostMapping("/submit")
    public ModelAndView submit(@ModelAttribute WebInputText webInputText) {
        // To get a list of concept mentions

        if(webInputText == null){
            webInputText = new WebInputText("test post login");
        }

        HashMap<String, Collection> annotMap = n3CNLPEngine.getResultMap(webInputText.getDocText());

        // render template for the fields in index-template
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", webInputText.getDocText());
        logger.info(String.format("Input for submit \"%s\"", webInputText.getDocText()));
        logger.info(String.format("Output map \"%s\"", annotMap));

        // get a list of concepts as JSON list to feed into the template

        JSONAnnotation jsAnnot = JSONAnnotation.generateConceptMentionBratJson(annotMap.get("cm"));

        indexView.addObject("cmList", jsAnnot.getCmList());
        indexView.addObject("attribList", jsAnnot.getAttribList());
        indexView.addObject("isResult", true);

        return indexView;
    }

    /**
     * Parse the input text
     * 
     * @param doc_text
     * @return attributes and concepts
     */
    @PostMapping("/parse")
    public JSONObject parse(@RequestParam(name = "doc_text") String doc_text) {
        return n3CNLPEngine.getResultJSON(doc_text);
    }

    /**
     * The covid 19 demo page.
     * 
     * @return the demo view
     */
    @GetMapping("/demo")
    public ModelAndView demo() {
        final ModelAndView indexView = new ModelAndView();
        indexView.setViewName("demo");
        return indexView;
    }

    /**
     * The dictionary builder page.
     * 
     * @return the dictionary builder view
     */
    @GetMapping("/dict_builder")
    public ModelAndView dict_builder() {
        final ModelAndView indexView = new ModelAndView();
        indexView.setViewName("dict_builder");
        return indexView;
    }

    /**
     * Get the ontology root from BioPortal
     * 
     * @param acronym
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/get_ontology_root")
    public String get_ontology_root(@RequestParam(name = "acronym") String acronym) throws Exception {
        String ret = BioPortalAPI.getOntologyRoot(acronym, bioportalAPIKey);
        return ret;
    }

    /**
     * Get the ontology children of a class from BioPortal
     * 
     * @param acronym
     * @param classid
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/get_ontology_children")
    public String get_ontology_children(@RequestParam(name = "acronym") String acronym,
            @RequestParam(name = "classid") String classid) throws Exception {
        String ret = BioPortalAPI.getOntologyChildren(acronym, classid, bioportalAPIKey);
        return ret;
    }

    /**
     * Get the sliced ontology CSV data from BioPortal
     * 
     * @param acronym
     * @return
     * @throws Exception
     */
    @CrossOrigin
    @GetMapping("/get_ontology_csv")
    public String get_ontology_csv(@RequestParam(name = "acronym") String acronym) throws Exception {
        String ret = BioPortalAPI.downloadAndExtractOntology(acronym, bioportalAPIKey);
        return ret;
    }

    /**
     * The IE rule editor page.
     * 
     * @return the dictionary builder view
     */
    @GetMapping("/ie_editor")
    public ModelAndView ie_editor(HttpSession session) {
        String username = (String) session.getAttribute("username");

        System.out.println("* username: " + username);
        final ModelAndView indexView = new ModelAndView();
        indexView.setViewName("ie_editor");
        return indexView;
    }

    /**
     * The IE rule test
     * 
     * @throws IOException
     */
    @PostMapping("/ie_editor_test")
    public JSONObject ie_editor_test(@RequestParam(name = "rulepack") String rulepack,
            @RequestParam(name = "doc_text") String docText) {

        // save the rulepack to guest temp folder
        try {
            Path userTempPath = IEEditorHelper.saveIERulePack(rulepack);
            N3CNLPEngine userEngine = new N3CNLPEngine(userTempPath.toAbsolutePath().toString());
            JSONObject ret = userEngine.getResultJSON(docText);
            return ret;
            
        } catch (Exception e) {
            //TODO: handle exception
            JSONObject failRet = new JSONObject();
            failRet.put("success", false);
            failRet.put("msg", e.getMessage());
            logger.error(e.getMessage());

            return failRet;
        }
    }

    /**
     * Login (fake)
     * @return the dictionary builder view
     */
    @GetMapping("/login")
    public String fake_login(@RequestParam(name = "username") String username, HttpSession session) {
        // set user name
        session.setAttribute("username", username);
        System.out.println("* set username: " + username);
        return "Set username: " + username;
    }


    // Login form
    @RequestMapping("/dologin")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Auth auth = new Auth(request, response);
        auth.login("http://localhost/attrs");

        System.out.println(auth.isAuthenticated());
        return "login.html";
    }

    /**
     * Logout (fake)
     * @return the dictionary builder view
     */
    @GetMapping("/fake_logout")
    public String fake_logout(HttpSession session) {
        // set user name
        session.setAttribute("username", null);
        System.out.println("* Logged out username.");
        return "Logged out";
    }
}
