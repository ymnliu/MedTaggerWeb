package org.ohnlp.web;

import org.json.simple.JSONObject;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.n3c.N3CNLPEngine;
import org.ohnlp.util.BioPortalAPI;
import org.ohnlp.util.IEEditorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;

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

    /**
     * How to display NLP results .
     *
     * @return the updated view out of index-template
     */
    @PostMapping("/")
    public ModelAndView submit(@ModelAttribute WebInputText webInputText) {
        // To get a list of concept mentions

        HashMap<String, Collection<ConceptMention>> annotMap = n3CNLPEngine.getResultMap(webInputText.getDocText());

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
    public ModelAndView ie_editor() {
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
}
