
/* First created by JCasGen Tue Sep 24 19:27:47 CDT 2013 */
package org.ohnlp.medtagger.type;

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

/** Stores the original entry of the dictionary
 * Updated by JCasGen Fri Oct 23 17:11:16 CDT 2020
 * @generated */
public class DictContext_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DictContext.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.medtagger.type.DictContext");
 
  /** @generated */
  final Feature casFeat_entry;
  /** @generated */
  final int     casFeatCode_entry;
  /** @generated */ 
  public String getEntry(int addr) {
        if (featOkTst && casFeat_entry == null)
      jcas.throwFeatMissing("entry", "org.ohnlp.medtagger.type.DictContext");
    return ll_cas.ll_getStringValue(addr, casFeatCode_entry);
  }
  /** @generated */    
  public void setEntry(int addr, String v) {
        if (featOkTst && casFeat_entry == null)
      jcas.throwFeatMissing("entry", "org.ohnlp.medtagger.type.DictContext");
    ll_cas.ll_setStringValue(addr, casFeatCode_entry, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public DictContext_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_entry = jcas.getRequiredFeatureDE(casType, "entry", "uima.cas.String", featOkTst);
    casFeatCode_entry  = (null == casFeat_entry) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entry).getCode();

  }
}



    