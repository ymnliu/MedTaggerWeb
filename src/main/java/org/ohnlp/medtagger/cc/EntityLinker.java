/*******************************************************************************
 * Copyright: (c)  2020  Mayo Foundation for Medical Education and
 *  Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 *  triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 *  Except as contained in the copyright notice above, or as used to identify 
 *  MFMER as the author of this software, the trade names, trademarks, service
 *  marks, or product names of the copyright holder shall not be used in
 *  advertising, promotion or otherwise in connection with this software without
 *  prior written authorization of the copyright holder.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 *******************************************************************************/
package org.ohnlp.medtagger.cc;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JFSIndexRepository;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.typesystem.type.structured.Document;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Link entities within sentence
 *
 * @author Sijia Liu
 */
public class EntityLinker extends CasConsumer_ImplBase {


    /**
     * initialize
     */
    public void initialize() throws ResourceInitializationException {

    }


    /**
     * process
     */
    public void processCas(CAS aCAS) throws ResourceProcessException {

        JCas jcas;
        try {
            jcas = aCAS.getJCas();
        } catch (CASException e) {
            throw new ResourceProcessException(e);
        }
        printAnnotationsInline(jcas);
    }


    // print out match and concept mention
    public void printAnnotationsInline(JCas jcas) {
        JFSIndexRepository indexes = jcas.getJFSIndexRepository();
        FSIterator<TOP> docIterator = indexes.getAllIndexedFS(Document.type);
        File inFile = null;
        if (docIterator.hasNext()) {
            Document docAnn = (Document) docIterator.next();
            String fileLoc = docAnn.getFileLoc();
            try {
                inFile = new File(new URL(fileLoc).getPath());
                String outFileName = inFile.getName();
                outFileName = outFileName + ".ann";
            } catch (MalformedURLException e1) {
            }
        }

        String toprint = "";

        // document text
        String doctext = jcas.getDocumentText();

        // get concept mention index
        FSIterator<? extends Annotation> cmIter = jcas.getAnnotationIndex(ConceptMention.type).iterator();

        while (cmIter.hasNext()) {
            ConceptMention cm = (ConceptMention) cmIter.next();
            toprint += inFile.getName() + "\t";
            toprint += "\"" + cm.getCoveredText() + "\""; // covered text;
            toprint += "\t\"" + cm.getBegin() + "\"\t" + "end=\"" + cm.getEnd() + "\"";
            toprint += "\t\"" + cm.getCertainty() + "\"";
            toprint += "\t\"" + cm.getStatus() + "\"";
            toprint += "\t\"" + cm.getExperiencer() + "\"";
            toprint += "\t\"" + cm.getNormTarget() + "\"";
            toprint += "\t\"" + cm.getSemGroup() + "\"";
            toprint += "\t\"" + cm.getSentence().getSegment().getValue() + "\"";
            toprint += "\t\"" + cm.getSentence().getId() + "\"";
            toprint += "\t\"" + cm.getSentence().getCoveredText().replaceAll("\n", " ").replaceAll("\r", " ") + "\"\n";
        }
    }

}
