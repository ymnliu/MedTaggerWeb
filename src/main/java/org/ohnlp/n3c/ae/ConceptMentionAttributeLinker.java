package org.ohnlp.n3c.ae;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.text.AnnotationIndex;
import org.apache.uima.jcas.JCas;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medtime.type.MedTimex3;
import org.ohnlp.n3c.type.Condition;
import org.ohnlp.typesystem.type.textspan.Sentence;


public class ConceptMentionAttributeLinker extends JCasAnnotator_ImplBase {

	private Logger logger = Logger.getLogger(getClass().getName());
		
	@Override
	public void process(JCas jcas) throws AnalysisEngineProcessException {
		AnnotationIndex<?> lineSentIndex = jcas.getAnnotationIndex(Sentence.type);
		Iterator<?> sentItr = lineSentIndex.iterator();
		AnnotationIndex<?> timex3Idx = jcas.getAnnotationIndex(MedTimex3.type);
		AnnotationIndex<?> cmIdx = jcas.getAnnotationIndex(ConceptMention.type);
		

		while(sentItr.hasNext()){
			Sentence sent = (Sentence) sentItr.next();
			Iterator<?> timex3Iter = timex3Idx.subiterator(sent);
			Iterator<?> cmIter = cmIdx.subiterator(sent);

			LinkedList cmq = new LinkedList<ConceptMention>();
			LinkedList fmq = new LinkedList<ConceptMention>();
			
			Queue timeq = new LinkedList<MedTimex3>();
			timex3Iter.forEachRemaining(timeq::add);

			while(cmIter.hasNext()){
				ConceptMention cm = (ConceptMention) cmIter.next();
				if(cm.getNormTarget().contains("degree_relative")) {
					fmq.add(cm);
				}else {
					cmq.add(cm);
				}
				
			}
			
			if(cmq.size() + timeq.size() + fmq.size() > 25) {
				logger.info(String.format("Sentence \"%s\" contains too many (%d) Concept Mentions. Skipped without matching. ", 
						sent.getCoveredText(), cmq.size() + timeq.size() + fmq.size()));
			}
			
			matchCMwithAttr(jcas, cmq, fmq);
			
		}
	}
	
	private void matchCMwithAttr(JCas jcas, LinkedList<ConceptMention> cmq, LinkedList<ConceptMention> fmq) {
		
		
		if(cmq.size() == fmq.size()) {
			for(int i=0; i < cmq.size(); i++) {
				Condition cd = new Condition(jcas);
				cd.setSignSymptom(cmq.get(i));
				cd.setSubject(fmq.get(i));
				cd.addToIndexes();
			}
		}else if(cmq.size() == 1) {
			for(ConceptMention fh: fmq) {
				Condition cd = new Condition(jcas);
				cd.setSignSymptom(cmq.getFirst());
				cd.setSubject(fh);
				cd.addToIndexes(jcas);
			}
		}else if(fmq.size() == 1) {
			for(ConceptMention cm: cmq) {
				Condition cd = new Condition(jcas);
				cd.setSignSymptom(cm);
				cd.setSubject(fmq.getFirst());
				cd.addToIndexes();
			}
		}
	}
}
