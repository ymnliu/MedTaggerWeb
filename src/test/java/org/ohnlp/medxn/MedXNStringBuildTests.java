package org.ohnlp.medxn;

import org.apache.commons.io.FileUtils;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.util.LifeCycleUtil;
import org.apache.uima.resource.Resource;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.util.CasCreationUtils;
import org.apache.uima.util.InvalidXMLException;
import org.junit.*;
import org.ohnlp.medxn.cc.MedXNCC;
import org.ohnlp.medxn.type.Drug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

import static java.nio.file.Files.readAllBytes;
import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.util.CasUtil.getType;
import static org.junit.Assert.assertEquals;

public class MedXNStringBuildTests {

    static AnalysisEngine aae;
    static ResourceManager resMgr;
    static AnalysisEngineDescription descMedXNTAE;
    static CAS cas;
    static ClassLoader clsLoader;

    @BeforeClass
    public static void initPipeline() throws ResourceInitializationException, IOException, InvalidXMLException {
        clsLoader = MedXNStringBuildTests.class.getClassLoader();

        descMedXNTAE = createEngineDescription(
                "desc.medxndesc.aggregate_analysis_engine.MedXNAggregateTAE");

        resMgr = ResourceManagerFactory.newResourceManager();
        AnalysisEngineDescription aaeDesc = AnalysisEngineFactory.createEngineDescription(descMedXNTAE);
        aae = UIMAFramework.produceAnalysisEngine(aaeDesc, resMgr, null);
        cas = CasCreationUtils.createCas(Arrays.asList(aae.getMetaData()), null, resMgr);
    }

    @Test
    public void processorTestOneline() throws ResourceInitializationException, IOException, AnalysisEngineProcessException, CASException {

        File inFile = new File(
                clsLoader.getResource("testdata/medxn/in/oneline.txt").getFile());

        File gsFile = new File(
                clsLoader.getResource("testdata/medxn/output/oneline_out.txt").getFile());
        String gsFileString = FileUtils.readFileToString(gsFile, "utf-8").trim();


        String inputContent = new String(readAllBytes(inFile.toPath()));
        String generatedString = processString(inputContent, inFile.getName());

        assertEquals("One line output differs from ground truth - ", gsFileString, generatedString);
    }

    @Test
    public void processorTestMultiLine() throws IOException, AnalysisEngineProcessException, CASException {

        File inFile = new File(
                clsLoader.getResource("testdata/medxn/in/sample.txt").getFile());
        File gsFile = new File(
                clsLoader.getResource("testdata/medxn/output/sample_out.txt").getFile());

        String inputContent = new String(readAllBytes(inFile.toPath()));
        String generatedString = processString(inputContent, inFile.getName());
        String gsFileString = FileUtils.readFileToString(gsFile, "utf-8").trim();

        assertEquals("Multi-line Output differs from ground truth - ", gsFileString, generatedString);
    }

    private String processString(String inputContent, String docName) throws AnalysisEngineProcessException, CASException {
        cas.setDocumentText(inputContent);
        aae.process(cas);
        Iterator<?> drugIter = cas.getAnnotationIndex(getType(cas, Drug.class)).iterator();
        String generatedString = "";

        while (drugIter.hasNext()) {
            Drug drug = (Drug) drugIter.next();
            generatedString += MedXNCC.buildDrugOutputString(cas, docName, "|", drug);
        }
        cas.reset();
        return generatedString.trim();
    }

    @AfterClass
    static public void cleanup() throws AnalysisEngineProcessException {
        aae.collectionProcessComplete();
    }
}
