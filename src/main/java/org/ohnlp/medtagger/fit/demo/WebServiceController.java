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

    public static JSONArray generateBratJson(Collection<ConceptMention> cms) {
        JSONArray cmList = new JSONArray();

        int entityIdInt = 1;
        for (ConceptMention cm : cms) {
            JSONObject cmj = new JSONObject();

            // Format: [${ID}, ${TYPE}, [[${START}, ${END}]]]
            // note that range of the offsets are [${START},${END})
            // ref: https://github.com/arne-cl/brat-embedded-visualization-examples/blob/master/minimal-brat-embedded.htm

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

    public WebServiceController() {
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

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", "I have a dry cough and fever.");
        return indexView;
    }


    @PostMapping("/")
    public ModelAndView submit(@ModelAttribute WebInputText webInputText) {
        Collection<ConceptMention> cms = runPipeline(webInputText.getDocText());
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", webInputText.getDocText());
        JSONArray cmList = generateBratJson(cms);
        indexView.addObject("cmList", cmList);
        indexView.addObject("message", "The results may take several seconds to load.");
        return indexView;

    }
}
