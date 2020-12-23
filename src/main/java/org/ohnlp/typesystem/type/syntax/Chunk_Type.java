
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.syntax;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** Chunks are syntactic groupings of tokens, and are the results of a shallow parser (chunker).  Possible chunkTypes follow Penn Treebank guidelines, e.g., noun phrase (NP) or prepositional phrase (PP) 
Equivalent to Mayo cTAKES version 2.5: edu.mayo.bmi.uima.chunker.type.Chunk
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * @generated */
public class Chunk_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Chunk.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.syntax.Chunk");
 
  /** @generated */
  final Feature casFeat_chunkType;
  /** @generated */
  final int     casFeatCode_chunkType;
  /** @generated */ 
  public String getChunkType(int addr) {
        if (featOkTst && casFeat_chunkType == null)
      jcas.throwFeatMissing("chunkType", "org.ohnlp.typesystem.type.syntax.Chunk");
    return ll_cas.ll_getStringValue(addr, casFeatCode_chunkType);
  }
  /** @generated */    
  public void setChunkType(int addr, String v) {
        if (featOkTst && casFeat_chunkType == null)
      jcas.throwFeatMissing("chunkType", "org.ohnlp.typesystem.type.syntax.Chunk");
    ll_cas.ll_setStringValue(addr, casFeatCode_chunkType, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Chunk_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_chunkType = jcas.getRequiredFeatureDE(casType, "chunkType", "uima.cas.String", featOkTst);
    casFeatCode_chunkType  = (null == casFeat_chunkType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_chunkType).getCode();

  }
}



    