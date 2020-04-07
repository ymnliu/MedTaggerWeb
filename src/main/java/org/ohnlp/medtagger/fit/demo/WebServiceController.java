package org.ohnlp.medtagger.fit.demo;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.ohnlp.medtagger.type.ConceptMention;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createJCas;

@RestController
public class WebServiceController {

    private static AnalysisEngineDescription descMedTaggerTAE;
    private static TypeSystemDescription tsd;
    private AnalysisEngine aae;

    /**
     * Trigger UIMA pipeline from the given texts and return only the concept mentions
     *
     * @param docText
     * @return
     */
    Collection<ConceptMention> runPipeline(String docText) {
        Collection<ConceptMention> cms = null;
        try {
            JCas sourceCas = createJCas(tsd);
            sourceCas.setDocumentText(docText);
            aae.process(sourceCas);
            cms = JCasUtil.select(sourceCas, ConceptMention.class);
            aae.collectionProcessComplete();
        } catch (UIMAException e) {
            e.printStackTrace();
        }
        return cms;
    }

    /**
     * Wrap list of concept mentions into json list to feed into the "entity" js field.
     * @param cms List of ConceptMentions from UIMA
     * @return list of JSON array for Brat to display in the html template
     */
    public static JSONArray generateBratJson(Collection<ConceptMention> cms) {
        JSONArray cmList = new JSONArray();

        int entityIdInt = 1;
        for (ConceptMention cm : cms) {
            JSONObject cmj = new JSONObject();

            // Format: [${ID}, ${TYPE}, [[${START}, ${END}]]]
            // note that range of the offsets are [${START},${END})
            // ref: https://github.com/arne-cl/brat-embedded-visualization-examples/blob/master/minimal-brat-embedded.htm

            // TODO: Add properties to show normalized concepts
            JSONArray entityProperties = new JSONArray();
            entityProperties.add(String.format("T%d", entityIdInt++));
            entityProperties.add("Cond");
            JSONArray spans = new JSONArray();
            JSONArray tokenBeginEnd = new JSONArray();
            tokenBeginEnd.add(cm.getBegin());
            tokenBeginEnd.add(cm.getEnd());
            spans.add(tokenBeginEnd);
            entityProperties.add(spans);
            cmList.add(entityProperties);
        }

        return cmList;
    }

    /**
     *  To initialize UIMA resources and type systesms
     */
    public WebServiceController() {

        // TODO: Change hard code path from the file system into resource reference
        Path ruleDirPath = Paths.get("src\\main\\resources\\medtaggerieresources\\covid19");
        System.out.println("IE Rules:\t" + ruleDirPath.toAbsolutePath().toString());

        try {
            ResourceManager resMgr = ResourceManagerFactory.newResourceManager();

            descMedTaggerTAE = createEngineDescription(
                    "desc.medtaggeriedesc.aggregate_analysis_engine.MedTaggerIEAggregateTAE");
            AnalysisEngineMetaData metadata = descMedTaggerTAE.getAnalysisEngineMetaData();

            ConfigurationParameterSettings settings = metadata.getConfigurationParameterSettings();
            settings.setParameterValue("Resource_dir", ruleDirPath.toString());
            metadata.setConfigurationParameterSettings(settings);

            aae = UIMAFramework.produceAnalysisEngine(descMedTaggerTAE, resMgr, null);

            tsd = TypeSystemDescriptionFactory.
                    createTypeSystemDescription("org.ohnlp.medtagger.ie.types.MedTaggerIETypes");
            tsd.resolveImports(resMgr);


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
     * @return the initial view out of index-template
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", "I have a dry cough and fever.");
        return indexView;
    }

    /**
     * How to display NLP results .
     * @return the updated view out of index-template
     */

    @PostMapping("/")
    public ModelAndView submit(@ModelAttribute WebInputText webInputText) {
        // To get a list of concept mentions

        Collection<ConceptMention> cms = runPipeline(webInputText.getDocText());

        // render template for the fields in index-template
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", webInputText.getDocText());

        // get a list of concepts as JSON list to feed into the template
        JSONArray cmList = generateBratJson(cms);

        indexView.addObject("cmList", cmList);
        indexView.addObject("message", "The results may take several seconds to load.");
        return indexView;

    }
}
