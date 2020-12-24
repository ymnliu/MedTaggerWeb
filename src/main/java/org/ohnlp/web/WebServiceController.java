package org.ohnlp.web;

import com.onelogin.saml2.Auth;
import com.onelogin.saml2.exception.Error;
import com.onelogin.saml2.exception.SettingsException;
import com.onelogin.saml2.exception.XMLEntityException;
import com.onelogin.saml2.servlet.ServletUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.ohnlp.n3c.N3CNLPEngine;
import org.ohnlp.util.BioPortalAPI;
import org.ohnlp.util.IEEditorHelper;
import org.ohnlp.web.db.entity.Project;
import org.ohnlp.web.db.entity.Rulepack;
import org.ohnlp.web.db.entity.User;
import org.ohnlp.web.db.service.MTService;
import org.ohnlp.web.db.service.ProjectService;
import org.ohnlp.web.db.service.RulepackService;
import org.ohnlp.web.db.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Value("${medtagger.web.default.username}")
    private String defaultUsername;

    // auto-generated by Spring, we will use it to handle the user data
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private RulepackService rulepackService;
    @Autowired
    private MTService mtService;

    /**
     * To initialize UIMA resources and type systesms
     */
    @Autowired
    public WebServiceController(
        UserService userService, 
        ProjectService projectService,
        RulepackService rulepackService,
        MTService mtService) {
        n3CNLPEngine = new N3CNLPEngine();
        this.userService = userService;
        this.projectService = projectService;
        this.rulepackService = rulepackService;
        this.mtService = mtService;

        // create a guest user and user project in db
        // User user = this.userService.getOrCreateUser("guest");
        // this.projectService.getOrCreateProject(user, "guest");
        this.mtService.createUserAndRelated("guest");
        this.logger.info("Created user guest and project if not exist.");

    }

    /**
     * The index page. Display a default sentence to start.
     * 
     * @return the initial view out of index-template
     */
    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        String username = this.getCurrentUsername(session);

        ModelAndView view = new ModelAndView();
        view.setViewName("demo");
        view.addObject("username", username);
        return view;
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
    public String dummy(HttpServletRequest request, 
        HttpServletResponse response, HttpSession session) throws Exception {
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
            if (relayState != null && 
                !relayState.isEmpty() && 
                !relayState.equals(ServletUtils.getSelfRoutedURLNoQuery(request)) &&
                !relayState.contains("/dologin.jsp")) { 
                // We don't want to be redirected to login.jsp neither
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
    public String sls(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        Auth auth = new Auth(request, response);
        auth.processSLO();

        List<String> errors = auth.getErrors();
        StringBuilder sb = new StringBuilder();
        if (errors.isEmpty()) {
            sb.append("<p>Sucessfully logged out</p>");
         } else {
            sb.append("<p>");
            for(String error : errors) {
                sb.append(" " + error + ".");
            }
            sb.append("</p>");
        }

        return sb.toString();
    }

    @GetMapping("/attrs")
    public ModelAndView showAttrs(HttpServletRequest request, HttpServletResponse response, HttpSession session){
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

        session.setAttribute("username", (String) session.getAttribute("nameId"));

        String username = this.getCurrentUsername(session);
        // return sb.toString();

        final ModelAndView view = new ModelAndView();
        view.setViewName("login_result");
        view.addObject("username", username);
        view.addObject("sb", sb);
        return view;
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
    public ModelAndView demo(HttpSession session) {
        String username = this.getCurrentUsername(session);
        
        ModelAndView view = new ModelAndView();
        view.setViewName("demo");
        view.addObject("username", username);
        return view;
    }

    /**
     * The dictionary builder page.
     * 
     * @return the dictionary builder view
     */
    @GetMapping("/dict_builder")
    public ModelAndView dict_builder(HttpSession session) {
        String username = this.getCurrentUsername(session);

        final ModelAndView view = new ModelAndView();
        view.setViewName("dict_builder");
        view.addObject("username", username);
        return view;
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
        String username = this.getCurrentUsername(session);

        System.out.println("* username: " + username);
        final ModelAndView view = new ModelAndView();
        view.setViewName("ie_editor");
        view.addObject("username", username);

        return view;

    }


    /**
     * Get current username from session
     * @param session
     * @return username
     */
    private String getCurrentUsername(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username == null) {
            username = "guest";
            // check database
            // User user = this.userService.getUserByUsername(username);
            // if (user == null) {
            //     user = this.userService.createUser(username);
            //     this.projectService.createProject(user, username);
            // }

            // set session as current guest
            session.setAttribute("username", username);
        }

        return username;
    }

    /**
     * The IE rule test
     * 
     * @throws IOException
     */
    @PostMapping("/ie_editor_test")
    public JSONObject ie_editor_test(
        @RequestParam(name = "rulepack") String rulepack,
        @RequestParam(name = "doc_text") String docText
    ) {

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


    // Login form
    @RequestMapping("/dologin")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Auth auth = new Auth(request, response);
        auth.login("https://ohnlp4covid-dev.n3c.ncats.io/attrs");

        System.out.println(auth.isAuthenticated());
        return "login.html";
    }

    // test
    @RequestMapping("/test")
    public ModelAndView page_test(
        HttpServletRequest request, 
        HttpServletResponse response,
        HttpSession session) throws Exception {

        User user = userService.getUserByUsername("guest");
        System.out.println("* user: " + user);

        String username = this.getCurrentUsername(session);
        System.out.println("* username in session: " + username);

        final ModelAndView testView = new ModelAndView();
        testView.setViewName("test");
        testView.addObject("username", user);
        return testView;
    }

    
    // test
    @RequestMapping("/user_portal")
    public ModelAndView user_portal(HttpSession session) throws Exception {
        // get current user
        String username = this.getCurrentUsername(session);
        User user = this.userService.getUserByUsername(username);

        // get rulepacks
        List<Rulepack> rulepacks = this.rulepackService.getRulepackListByUser(user);

        final ModelAndView view = new ModelAndView();
        view.setViewName("user_portal");
        view.addObject("rulepacks", rulepacks);
        return view;
    }


    @GetMapping("/get_rulepack_list")
    public JSONObject get_rulepack_list(
        @RequestParam(name = "project_id") String project_id,
        HttpSession session) {
        // get username
        String username = (String) session.getAttribute("username");
        // get user
        User user = this.userService.getUserByUsername(username);

        // get rulepack list by username
        List<Rulepack> rulepacks = this.rulepackService.getRulepackListByUser(user);
        
        JSONObject ret = new JSONObject();
        ret.put("success", true);
        ret.put("data", rulepacks);

        return ret;
    }

    @GetMapping("/get_rulepack")
    public JSONObject get_rulepack(
        @RequestParam(name = "rulepack_id") int rulepack_id,
        HttpSession session) throws Exception {
        // get username
        String username = (String) session.getAttribute("username");
        // get user
        User user = this.userService.getUserByUsername(username);

        // get rulepack by rulepack_id
        Rulepack rulepack = this.rulepackService.getRulepack(rulepack_id);
        
        JSONObject ret = new JSONObject();
        ret.put("success", true);

        JSONObject r = new JSONObject();

        JSONObject d = (JSONObject) new JSONParser().parse(rulepack.getData());
        r.put("id", rulepack.getId());
        r.put("title", rulepack.getTitle());
        r.put("data", d);

        ret.put("r", r);

        return ret;
    }


    @PostMapping("/save_rulepack")
    public JSONObject save_rulepack(
        @RequestParam(name = "rulepack_id") int rulepack_id,
        @RequestParam(name = "project_id") int project_id,
        @RequestParam(name = "title") String title,
        @RequestParam(name = "data") String data,
        HttpSession session) {
        // get username
        String username = (String) session.getAttribute("username");
        // get user
        User user = this.userService.getUserByUsername(username);
        // get project 
        Project project = this.projectService.getProjectByTitle(user, username);

        // save or update the rulepack
        Rulepack rulepack = null;
        if (rulepack_id == -1) {
            // create a new rulepack
            rulepack = rulepackService.createRulepack(user, project, title, data);
        } else {
            // get rulepack by rulepack_id
            rulepack = this.rulepackService.getRulepack(rulepack_id);
            
            if (rulepack == null) {
                rulepack = rulepackService.createRulepack(user, project, title, data);
            } else {
                // update the attrs
                rulepack = this.rulepackService.setRulepack(rulepack.getId(), title, data);
            }
        }
        
        JSONObject ret = new JSONObject();
        ret.put("success", true);

        JSONObject r = new JSONObject();
        r.put("id", rulepack.getId());
        r.put("title", rulepack.getTitle());
        r.put("data", rulepack.getData());

        ret.put("r", r);

        return ret;
    }


    @PostMapping("del_rulepack")
    public JSONObject save_rulepack(
        @RequestParam(name = "rulepack_id") int rulepack_id,
        HttpSession session) {
        // get username
        String username = (String) session.getAttribute("username");
        // get user
        User user = this.userService.getUserByUsername(username);

        // save or update the rulepack
        int ret = this.rulepackService.deleteRulepack(user, rulepack_id);
        
        if (ret == 0) {
            JSONObject r = new JSONObject();
            r.put("success", true);
            return r;
        } else {
            JSONObject r = new JSONObject();
            r.put("success", false);
            return r;
        }
    }


    /**
     * SAML SLO following: https://github.com/onelogin/java-saml/tree/master/samples/java-saml-tookit-jspsample/src/main/webapp
     * @param request
     * @param response
     * @param session
     * @return
     * @throws SettingsException
     * @throws Error
     * @throws IOException
     * @throws XMLEntityException
     */
    @RequestMapping("/dologout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws SettingsException, Error, IOException, XMLEntityException {
        Auth auth = new Auth(request, response);
        String nameId = null;
        if (session.getAttribute("nameId") != null) {
            nameId = session.getAttribute("nameId").toString();
        }
        String nameIdFormat = null;
        if (session.getAttribute("nameIdFormat") != null) {
            nameIdFormat = session.getAttribute("nameIdFormat").toString();
        }
        String nameidNameQualifier = null;
        if (session.getAttribute("nameidNameQualifier") != null) {
            nameidNameQualifier = session.getAttribute("nameidNameQualifier").toString();
        }
        String nameidSPNameQualifier = null;
        if (session.getAttribute("nameidSPNameQualifier") != null) {
            nameidSPNameQualifier = session.getAttribute("nameidSPNameQualifier").toString();
        }
        String sessionIndex = null;
        if (session.getAttribute("sessionIndex") != null) {
            sessionIndex = session.getAttribute("sessionIndex").toString();
        }
        auth.logout(null, nameId, sessionIndex, nameIdFormat, nameidNameQualifier, nameidSPNameQualifier);

        return nameId + " has been logged out";
    }
    
    
    /**
     * Login locally
     * @return the local login view
     */
    @GetMapping("/_login")
    public ModelAndView local_login(HttpSession session) {
        final ModelAndView view = new ModelAndView();
        view.setViewName("local_login");
        return view;
    }

    /**
     * Login locally
     * @return login view
     */
    @PostMapping("/_login")
    public String local_login(
        @RequestParam(name = "username") String username, 
        @RequestParam(name = "password") String password, 
        @RequestParam(name = "action") String action, 
        HttpSession session) {
        // set user name
        if (action.equalsIgnoreCase("login")) {
            session.setAttribute("username", username);
            System.out.println("* session username=" + session.getAttribute("username"));
            return "Logged in! Redirecting to demo page ... <script>setTimeout('location.href=\"/\"', 4000);</script>";
        } else if (action.equalsIgnoreCase("logout")) {
            session.setAttribute("username", null);
            return "Logged out";
        } else if (action.equalsIgnoreCase("create")) {
            // create this user if not exists
            this.mtService.createUserAndRelated(username);
            // created, login
            session.setAttribute("username", username);
            return "Logged in! Redirecting to demo page ... <script>setTimeout('location.href=\"/\"', 4000);</script>";
        } else {
            return action + " - " + username;
        }
    }

    /**
     * Logout
     * @return the view
     */
    @GetMapping("/_logout")
    public String local_logout(HttpSession session) {
        // set user name
        session.setAttribute("username", "guest");
        System.out.println("* Logged out username.");
        return "Logged out. Redirecting to demo page ... <script>setTimeout('location.href=\"/\"', 4000);</script>";
    }

}
