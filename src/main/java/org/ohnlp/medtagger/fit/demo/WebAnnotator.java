package org.ohnlp.medtagger.fit.demo;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.json.simple.JSONArray;
import org.ohnlp.medtagger.type.ConceptMention;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createJCas;

public class WebAnnotator {

    static String runPipeline(String documentText, AnalysisEngineDescription desc)
            throws UIMAException {

        ResourceManager resMgr = ResourceManagerFactory.newResourceManager();
        AnalysisEngineDescription aaeDesc = AnalysisEngineFactory.createEngineDescription(desc);
        AnalysisEngine aae = UIMAFramework.produceAnalysisEngine(aaeDesc, resMgr, null);

        TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription("org.ohnlp.medtagger.ie.types.MedTaggerIETypes");
        tsd.resolveImports(resMgr);
        JCas sourceCas = createJCas(tsd);

        sourceCas.setDocumentText(documentText);

        aae.process(sourceCas);

        Collection<ConceptMention> cms = JCasUtil.select(sourceCas, ConceptMention.class);
        JSONArray cmList = new JSONArray();
        cmList.addAll(cms);
        aae.collectionProcessComplete();
        return cmList.toJSONString();
    }


    public static void main(String[] args) throws Exception {

        String documentText = "I have a dry cough and high body temperature";

        Path ruleDirPath = Paths.get("src\\main\\resources\\medtaggerieresources\\covid19");
        System.out.println("IE Rules:\t" + ruleDirPath.toAbsolutePath().toString());

        AnalysisEngineDescription descMedTaggerTAE = createEngineDescription(
                "desc.medtaggeriedesc.aggregate_analysis_engine.MedTaggerIEAggregateTAE");

        AnalysisEngineMetaData metadata = descMedTaggerTAE.getAnalysisEngineMetaData();

        ConfigurationParameterSettings settings = metadata.getConfigurationParameterSettings();
        settings.setParameterValue("Resource_dir", ruleDirPath.toString());
        metadata.setConfigurationParameterSettings(settings);
        runPipeline(documentText, descMedTaggerTAE);
    }
}
