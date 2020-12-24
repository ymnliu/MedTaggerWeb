package org.ohnlp.n3c;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
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
import org.json.simple.JSONObject;
import org.ohnlp.medtagger.cr.FileSystemReader;
import org.ohnlp.medtagger.ie.cc.IETabDelimitedWriter;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medtime.type.MedTimex3;
import org.ohnlp.medxn.cc.MedXNCC;
import org.ohnlp.util.SimpleCliPipeline;
import org.ohnlp.web.JSONAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createJCas;

public class N3CNLPEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static TypeSystemDescription tsd;
    private AnalysisEngine cmAae;

    public N3CNLPEngine(){
        initUIMAModel("covid19");
    }

    public N3CNLPEngine(String ruleDir) {
        initUIMAModel(ruleDir);
    }

    private void initUIMAModel(String ruleDir){
        Path ruleDirPath = Paths.get(ruleDir);
        logger.info("IE Rules:\t" + ruleDirPath.toAbsolutePath());

        try {
            ResourceManager resMgr = ResourceManagerFactory.newResourceManager();
            AnalysisEngineDescription descN3cTAE = createEngineDescription(
                    "desc.n3cdesc.aggregate_analysis_engine.N3CAggregateTAE");
                    // "desc.medtaggerdesc.aggregate_analysis_engine.N3CAggregateTAE");
            AnalysisEngineMetaData metadata = descN3cTAE.getAnalysisEngineMetaData();

            ConfigurationParameterSettings settings = metadata.getConfigurationParameterSettings();
            settings.setParameterValue("Resource_dir", ruleDirPath.toString());
            metadata.setConfigurationParameterSettings(settings);

            // modified MedXNTypes now importing MedTaggerIE types
            tsd = TypeSystemDescriptionFactory.createTypeSystemDescription("org.ohnlp.n3c.types.N3cTypes");
            tsd.resolveImports(resMgr);

            cmAae = UIMAFramework.produceAnalysisEngine(descN3cTAE, resMgr, null);

            assert(cmAae != null);
        } catch (InvalidXMLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceInitializationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Trigger UIMA pipeline from the given texts and return only the concept
     * mentions
     *
     * @param docText
     * @return
     */
    public HashMap<String, Collection> getResultMap(String docText) {

        HashMap<String, Collection> annotMap = new HashMap();
        try {
            JCas cmCas = createJCas(tsd);
            cmCas.setDocumentText(docText);
            cmAae.process(cmCas);
            annotMap.put("cm", JCasUtil.select(cmCas, ConceptMention.class));
            annotMap.put("timex3", JCasUtil.select(cmCas, MedTimex3.class));

            cmAae.collectionProcessComplete();

        } catch (UIMAException e) {
            e.printStackTrace();
        }

        return annotMap;
    }

    public JSONObject getResultJSON(String docText) {

        HashMap<String, Collection> resultMap = getResultMap(docText);
        JSONAnnotation jsAnnot = JSONAnnotation.generateConceptMentionBratJson(resultMap.get("cm"));

        jsAnnot.add(JSONAnnotation.generateTimex3BratJson(resultMap.get("timex3"), jsAnnot.getAnnotMentionSize() + 1,
                jsAnnot.getAnnotAttribSize() + 1));

        // build the output data
        JSONObject data = new JSONObject();
        data.put("attributes", jsAnnot.getAttribList());
        data.put("entities", jsAnnot.getCmList());
        data.put("text", docText);

        // build the output json
        JSONObject ret = new JSONObject();
        ret.put("data", data);
        ret.put("success", true);
        ret.put("msg", "text is parsed.");

        return ret;
    }
}
