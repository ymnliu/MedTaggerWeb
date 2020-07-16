package org.ohnlp.util.demo;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medxn.type.Drug;
import org.ohnlp.util.BioPortalAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createJCas;

@RestController
public class WebServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static TypeSystemDescription tsd;
    private AnalysisEngine cmAae;

    @Value("${bioportal.apikey}")
    private String bioportalAPIKey;

    /**
     * Trigger UIMA pipeline from the given texts and return only the concept
     * mentions
     *
     * @param docText
     * @return
     */
    HashMap<String, Collection> runPipeline(String docText) {

        HashMap<String, Collection> annotMap = new HashMap<>();
        Collection<ConceptMention> cms = null;
        Collection<Drug> drugs = null;
        try {
            JCas cmCas = createJCas(tsd);
            cmCas.setDocumentText(docText);
            cmAae.process(cmCas);

            cms = JCasUtil.select(cmCas, ConceptMention.class);
            drugs = JCasUtil.select(cmCas, Drug.class);

            annotMap.put("cms", cms);
            annotMap.put("drugs", drugs);

            cmAae.collectionProcessComplete();

        } catch (UIMAException e) {
            e.printStackTrace();
        }

        return annotMap;
    }

    /**
     * Wrap list of concept mentions into json list to feed into the "entity" js
     * field.
     * 
     * @param cms List of Annotation from UIMA
     * @return list of JSON array for Brat to display in the html template
     */
    static JSONAnnotation generateConceptMentionBratJson(final Collection<ConceptMention> cms) {
        JSONArray cmList = new JSONArray();
        JSONArray attribList = new JSONArray();

        int entityIdInt = 1;
        int attribIdInt = 1;

        if (cms == null) {
            return new JSONAnnotation(cmList, attribList);
        }

        for (final ConceptMention cm : cms) {

            // Format: [${ID}, ${TYPE}, [[${START}, ${END}]]]
            // note that range of the offsets are [${START},${END})
            // ref:
            // https://github.com/arne-cl/brat-embedded-visualization-examples/blob/master/minimal-brat-embedded.htm

            JSONArray entityProperties = new JSONArray();
            String bratEntityId = String.format("T%d", entityIdInt++);
            entityProperties.add(bratEntityId);
            entityProperties.add("Condition");
            JSONArray spans = new JSONArray();
            JSONArray tokenBeginEnd = new JSONArray();
            tokenBeginEnd.add(cm.getBegin());
            tokenBeginEnd.add(cm.getEnd());
            spans.add(tokenBeginEnd);
            entityProperties.add(spans);
            cmList.add(entityProperties);

            /**
             * ConceptMention begin: 9 end: 18 detectionMethod: "Matched" normTarget:
             * "DRY_COUGH" Certainty: "Positive" semGroup: <null> status: "Present"
             * sentence: Sentence experiencer: "Patient"
             */

            JSONArray attribProperties = new JSONArray();
            attribProperties.add(String.format("A%d", attribIdInt++));
            attribProperties.add("norm");
            attribProperties.add(bratEntityId);
            attribProperties.add(cm.getNormTarget());
            attribList.add(attribProperties);

            JSONArray certaintyProperties = new JSONArray();
            certaintyProperties.add(String.format("A%d", attribIdInt++));
            certaintyProperties.add("certainty");
            certaintyProperties.add(bratEntityId);
            certaintyProperties.add(cm.getCertainty());
            attribList.add(certaintyProperties);

            JSONArray statusProperties = new JSONArray();
            statusProperties.add(String.format("A%d", attribIdInt++));
            statusProperties.add("status");
            statusProperties.add(bratEntityId);
            statusProperties.add(cm.getStatus());
            attribList.add(statusProperties);

            JSONArray experiencerProperties = new JSONArray();
            experiencerProperties.add(String.format("A%d", attribIdInt++));
            experiencerProperties.add("experiencer");
            experiencerProperties.add(bratEntityId);
            experiencerProperties.add(cm.getExperiencer());
            attribList.add(experiencerProperties);
        }

        return new JSONAnnotation(cmList, attribList);
    }

    /**
     * Wrap list of concept mentions into json list to feed into the "entity" js
     * field.
     * 
     * @param drugs List of Drugs
     * @return list of JSON array for Brat to display in the html template
     */
    static JSONAnnotation generateDrugBratJson(Collection<Drug> drugs, int entityIdInt, int attribIdInt) {
        JSONArray drugList = new JSONArray();
        JSONArray attribList = new JSONArray();

        if (drugs == null) {
            return new JSONAnnotation(drugList, attribList);
        }

        for (Drug drug : drugs) {

            // Format: [${ID}, ${TYPE}, [[${START}, ${END}]]]
            // note that range of the offsets are [${START},${END})
            // ref:
            // https://github.com/arne-cl/brat-embedded-visualization-examples/blob/master/minimal-brat-embedded.htm

            JSONArray entityProperties = new JSONArray();
            String bratEntityId = String.format("T%d", entityIdInt++);
            entityProperties.add(bratEntityId);
            entityProperties.add("Condition");
            JSONArray spans = new JSONArray();
            JSONArray tokenBeginEnd = new JSONArray();
            tokenBeginEnd.add(drug.getBegin());
            tokenBeginEnd.add(drug.getEnd());
            spans.add(tokenBeginEnd);
            entityProperties.add(spans);
            drugList.add(entityProperties);

            JSONArray attribProperties = new JSONArray();
            attribProperties.add(String.format("A%d", attribIdInt++));
            attribProperties.add("norm");
            attribProperties.add(bratEntityId);
            attribProperties.add(drug.getName());
            attribList.add(attribProperties);
        }

        return new JSONAnnotation(drugList, attribList);
    }

    /**
     * To initialize UIMA resources and type systesms
     */
    public WebServiceController() {

        // TODO: Change hard code path from the file system into resource reference
        Path ruleDirPath = Paths.get("covid19");
        System.out.println("IE Rules:\t" + ruleDirPath.toAbsolutePath().toString());

        try {
            ResourceManager resMgr = ResourceManagerFactory.newResourceManager();
            AnalysisEngineDescription descN3cTAE = createEngineDescription(
                    "desc.medtaggerdesc.aggregate_analysis_engine.N3CAggregateTAE");
            AnalysisEngineMetaData metadata = descN3cTAE.getAnalysisEngineMetaData();

            ConfigurationParameterSettings settings = metadata.getConfigurationParameterSettings();
            settings.setParameterValue("Resource_dir", ruleDirPath.toString());
            metadata.setConfigurationParameterSettings(settings);

            // modified MedXNTypes now importing MedTaggerIE types
            tsd = TypeSystemDescriptionFactory.createTypeSystemDescription("org.ohnlp.medxn.types.MedXNTypes");
            tsd.resolveImports(resMgr);

            cmAae = UIMAFramework.produceAnalysisEngine(descN3cTAE, resMgr, null);

        } catch (InvalidXMLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceInitializationException e) {
            e.printStackTrace();
        }
    }

    /**
     * The index page. Display a default sentence to start.
     * 
     * @return the initial view out of index-template
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index");
        // indexView.addObject("input_text", "The patient had dry cough and took Aspirin
        // yesterday.");
        return indexView;
    }


    @GetMapping("/index_v0")
    public ModelAndView index_v0() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index_v0");
        indexView.addObject("input_text", "The patient had dry cough and took Aspirin yesterday.");
        return indexView;
    }

    /**
     * How to display NLP results .
     * 
     * @return the updated view out of index-template
     */
    @PostMapping("/")
    public ModelAndView submit(@ModelAttribute WebInputText webInputText) {
        // To get a list of concept mentions

        HashMap<String, Collection> annotMap = runPipeline(webInputText.getDocText());

        // render template for the fields in index-template
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", webInputText.getDocText());
        logger.info(webInputText.getDocText());

        System.out.println(annotMap);

        // get a list of concepts as JSON list to feed into the template

        JSONAnnotation jsAnnot = generateConceptMentionBratJson(annotMap.get("cm"));
        // int drugIdStart = jsAnnot.getCmList().size() + 1;
        // int drugAttribIdStart = jsAnnot.getAttribList().size() + 1;
        //
        // jsAnnot.add(generateDrugBratJson(annotMap.get("drug"), drugIdStart,
        // drugAttribIdStart));

        indexView.addObject("cmList", jsAnnot.getCmList());
        indexView.addObject("attribList", jsAnnot.getAttribList());
        indexView.addObject("isResult", true);

        return indexView;

    }

    @PostMapping("/parse")
    public JSONObject parse(@RequestParam(name = "doc_text") String doc_text) {
        // TODO: check the input 

        // get the list of concept metions
        HashMap<String, Collection> annotMap = runPipeline(doc_text);
        JSONAnnotation jsAnnot = generateConceptMentionBratJson(annotMap.get("cm"));

        // build the output data
        JSONObject data = new JSONObject();
        data.put("attributes", jsAnnot.getAttribList());
        data.put("entities", jsAnnot.getCmList());

        // build the output json
        JSONObject ret = new JSONObject();
        ret.put("data", data);
        ret.put("success", true);
        ret.put("msg", "text is parsed.");

        return ret;
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

    @CrossOrigin
    @GetMapping("/get_ontology_root")
    public String get_ontology_root(@RequestParam(name = "acronym") String acronym) throws Exception {
        String ret = BioPortalAPI.getOntologyRoot(acronym, bioportalAPIKey);
        return ret;
    }

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
}
