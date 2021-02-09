package org.ohnlp.n3c;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medtime.type.MedTimex3;
import org.ohnlp.medxn.MedXNStringBuildTests;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.junit.Assert.assertNotEquals;

public class FamilyHistoryTests {
    static AnalysisEngine cmAae;
    static ResourceManager resMgr;
    static CAS cas;
    static ClassLoader clsLoader;


    @BeforeClass
    public static void initPipeline() throws ResourceInitializationException, IOException, InvalidXMLException {
        clsLoader = MedXNStringBuildTests.class.getClassLoader();

        resMgr = ResourceManagerFactory.newResourceManager();
        AnalysisEngineDescription descN3cTAE = createEngineDescription(
                "desc.medtaggerdesc.aggregate_analysis_engine.N3CAggregateTAE");
        AnalysisEngineMetaData metadata = descN3cTAE.getAnalysisEngineMetaData();

        ConfigurationParameterSettings settings = metadata.getConfigurationParameterSettings();
        Path ruleDirPath = Paths.get("fh");
        System.out.println("IE Rules:\t" + ruleDirPath.toAbsolutePath().toString());

        settings.setParameterValue("Resource_dir", ruleDirPath.toString());
//        metadata.setConfigurationParameterSettings(settings);

        settings.setParameterValue("MedTime_Resource_Dir", "medtimeresources");
        metadata.setConfigurationParameterSettings(settings);

        cmAae = UIMAFramework.produceAnalysisEngine(descN3cTAE, resMgr, null);

        cas = CasCreationUtils.createCas(Arrays.asList(cmAae.getMetaData()), null, resMgr);

    }

    @Test
    public void processString() throws AnalysisEngineProcessException, CASException {
        String text = "Father died of cardiomyopathy in his mid-50s; he also had coronary disease.";
        cas.setDocumentText(text);
        cmAae.process(cas);
        Collection<ConceptMention> results = JCasUtil.select(cas.getJCas(), ConceptMention.class);
        System.out.println(results);

        Collection<MedTimex3> times = JCasUtil.select(cas.getJCas(), MedTimex3.class);
        System.out.println(times);

        assertNotEquals(0, results.size());
    }
}
