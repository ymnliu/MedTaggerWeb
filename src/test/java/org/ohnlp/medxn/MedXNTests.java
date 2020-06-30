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
import org.junit.Test;
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

public class MedXNTests {

    @Test
    public void processorTest() {
        AnalysisEngine aae = null;

        try {
            AnalysisEngineDescription descMedXNTAE = createEngineDescription(
                    "desc.medxndesc.aggregate_analysis_engine.MedXNAggregateTAE");

            ResourceManager resMgr = ResourceManagerFactory.newResourceManager();
            AnalysisEngineDescription aaeDesc = AnalysisEngineFactory.createEngineDescription(descMedXNTAE);
            aae = UIMAFramework.produceAnalysisEngine(aaeDesc, resMgr, null);
            CAS cas = CasCreationUtils.createCas(Arrays.asList(aae.getMetaData()),
                    null, resMgr);

            File inFile = new File(
                    getClass().getClassLoader().getResource("testdata/medxn/in/oneline.txt").getFile()
            );

            File outFile = new File(
                    getClass().getClassLoader().getResource("testdata/medxn/output/oneline_out.txt").getFile());


            String content = new String(readAllBytes(inFile.toPath()));

            cas.setDocumentText(content);
            aae.process(cas);
            Iterator<?> drugIter = cas.getAnnotationIndex(getType(cas, Drug.class)).iterator();
            String generatedString = "";

            while (drugIter.hasNext()) {
                Drug drug = (Drug) drugIter.next();
                generatedString += MedXNCC.buildDrugOutputString(cas, "oneline.txt", "|", drug);
            }

            String gsFileString = FileUtils.readFileToString(outFile, "utf-8").trim();

            assertEquals("Output string differs from ground truth - ",
                    gsFileString,
                    generatedString);

            cas.reset();
            aae.collectionProcessComplete();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ResourceInitializationException ex) {
            ex.printStackTrace();
        } catch (AnalysisEngineProcessException ex) {
            ex.printStackTrace();
        } catch (InvalidXMLException ex) {
            ex.printStackTrace();
        } catch (CASException e) {
            e.printStackTrace();
        } finally {
            LifeCycleUtil.destroy(new Resource[]{aae});
        }

    }

}
