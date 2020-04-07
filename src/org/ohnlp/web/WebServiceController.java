package org.ohnlp.web;

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

    String runPipeline(String docText) {
        JSONArray cmList = new JSONArray();
        try {
            JCas sourceCas = createJCas(tsd);
            sourceCas.setDocumentText(docText);
            aae.process(sourceCas);
            Collection<ConceptMention> cms = JCasUtil.select(sourceCas, ConceptMention.class);
            cmList.addAll(cms);
            aae.collectionProcessComplete();
        } catch (UIMAException e){
            e.printStackTrace();
        }
        return cmList.toJSONString();

    }

    public WebServiceController(){
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

//    @RequestMapping(value="/", method=RequestMethod.POST)
//    public ModelAndView save(@ModelAttribute WebInputText webInputText){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("user-data");
//        modelAndView.addObject("input_text", webInputText);
//        return modelAndView;
//    }


    @PostMapping("/")
    public ModelAndView submit(@ModelAttribute WebInputText webInputText) {
        String message = runPipeline(webInputText.getDocText());
        ModelAndView indexView = new ModelAndView();
        indexView.setViewName("index-template");
        indexView.addObject("input_text", webInputText.getDocText());
        indexView.addObject("message", message);
        return indexView;

    }
}
