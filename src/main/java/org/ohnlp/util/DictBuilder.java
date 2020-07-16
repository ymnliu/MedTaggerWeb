package org.ohnlp.util;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.uima.UIMAFramework;
import org.apache.uima.collection.CollectionProcessingEngine;
import org.apache.uima.collection.metadata.CpeDescription;
import org.apache.uima.util.XMLInputSource;

public class DictBuilder {
    public static void main(String[] args) throws Exception {

        Path cpeFilePath = Paths.get(args[0]);
        Path inputFilePath = Paths.get(args[1]);
        Path outputFilePath = Paths.get(args[2]);

        System.out.println("CPE Desc File:\t" + cpeFilePath.toAbsolutePath().toString());
        System.out.println("Input File:\t" + inputFilePath.toAbsolutePath().toString());
        System.out.println("Output File:\t" + outputFilePath.toAbsolutePath().toString());

        // follow the instruction on
        // https://uima.apache.org/d/uimaj-3.0.0/tutorials_and_users_guides.html#ugr.tug.cpe.running_cpe_from_application

        // get the cpe desc
        CpeDescription cpeDesc = UIMAFramework.getXMLParser()
            .parseCpeDescription(
                new XMLInputSource(
                    cpeFilePath.toAbsolutePath().toString()
                )
            );

        System.out.println("created CPE description");

        // update the configuration of input file
        cpeDesc.getAllCollectionCollectionReaders()[0]
            .getConfigurationParameterSettings()
            .setParameterValue(
                "InputFile", 
                inputFilePath.toAbsolutePath().toString()
            );
        
        // update the config of output file
        cpeDesc.getCpeCasProcessors().getAllCpeCasProcessors()[1]
            .getConfigurationParameterSettings()
            .setParameterValue(
                "OutputFile", 
                outputFilePath.toAbsolutePath().toString()
            );
            
        // create CPE
        CollectionProcessingEngine descDictPrepareCPE = 
            UIMAFramework.produceCollectionProcessingEngine(cpeDesc);

        // // run CPE
        descDictPrepareCPE.process();

    }
}