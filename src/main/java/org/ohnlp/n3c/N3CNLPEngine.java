package org.ohnlp.n3c;

import org.apache.uima.UIMAException;
import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.analysis_engine.metadata.AnalysisEngineMetaData;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.internal.ResourceManagerFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceManager;
import org.apache.uima.resource.metadata.ConfigurationParameterSettings;
import org.apache.uima.resource.metadata.TypeSystemDescription;
import org.apache.uima.util.InvalidXMLException;
import org.ohnlp.medtagger.cr.FileSystemReader;
import org.ohnlp.medtagger.ie.cc.IETabDelimitedWriter;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medxn.cc.MedXNCC;
import org.ohnlp.util.SimpleCliPipeline;
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
        initUIMAModel();
    }

    private void initUIMAModel(){
        // TODO: Change hard code path from the file system into resource reference
        Path ruleDirPath = Paths.get("covid19");
        logger.info("IE Rules:\t" + ruleDirPath.toAbsolutePath());

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
    public HashMap<String, Collection<ConceptMention>> runPipeline(String docText) {

        HashMap<String, Collection<ConceptMention>> annotMap = new HashMap<String, Collection<ConceptMention>>();
        Collection<ConceptMention> cms = null;
        try {
            JCas cmCas = createJCas(tsd);
            cmCas.setDocumentText(docText);
            cmAae.process(cmCas);
            cms = JCasUtil.select(cmCas, ConceptMention.class);

            annotMap.put("cm", cms);

            cmAae.collectionProcessComplete();

        } catch (UIMAException e) {
            e.printStackTrace();
        }

        return annotMap;
    }


}
