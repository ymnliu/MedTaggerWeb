
/* First created by JCasGen Fri Oct 23 17:11:18 CDT 2020 */
package org.ohnlp.n3c.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * @generated */
public class Condition_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Condition.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.n3c.type.Condition");
 
  /** @generated */
  final Feature casFeat_SignSymptom;
  /** @generated */
  final int     casFeatCode_SignSymptom;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSignSymptom(int addr) {
        if (featOkTst && casFeat_SignSymptom == null)
      jcas.throwFeatMissing("SignSymptom", "org.ohnlp.n3c.type.Condition");
    return ll_cas.ll_getRefValue(addr, casFeatCode_SignSymptom);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSignSymptom(int addr, int v) {
        if (featOkTst && casFeat_SignSymptom == null)
      jcas.throwFeatMissing("SignSymptom", "org.ohnlp.n3c.type.Condition");
    ll_cas.ll_setRefValue(addr, casFeatCode_SignSymptom, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Subject;
  /** @generated */
  final int     casFeatCode_Subject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSubject(int addr) {
        if (featOkTst && casFeat_Subject == null)
      jcas.throwFeatMissing("Subject", "org.ohnlp.n3c.type.Condition");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Subject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSubject(int addr, int v) {
        if (featOkTst && casFeat_Subject == null)
      jcas.throwFeatMissing("Subject", "org.ohnlp.n3c.type.Condition");
    ll_cas.ll_setRefValue(addr, casFeatCode_Subject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Time;
  /** @generated */
  final int     casFeatCode_Time;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTime(int addr) {
        if (featOkTst && casFeat_Time == null)
      jcas.throwFeatMissing("Time", "org.ohnlp.n3c.type.Condition");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Time);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTime(int addr, int v) {
        if (featOkTst && casFeat_Time == null)
      jcas.throwFeatMissing("Time", "org.ohnlp.n3c.type.Condition");
    ll_cas.ll_setRefValue(addr, casFeatCode_Time, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Condition_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_SignSymptom = jcas.getRequiredFeatureDE(casType, "SignSymptom", "org.ohnlp.medtagger.type.ConceptMention", featOkTst);
    casFeatCode_SignSymptom  = (null == casFeat_SignSymptom) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_SignSymptom).getCode();

 
    casFeat_Subject = jcas.getRequiredFeatureDE(casType, "Subject", "org.ohnlp.medtagger.type.ConceptMention", featOkTst);
    casFeatCode_Subject  = (null == casFeat_Subject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Subject).getCode();

 
    casFeat_Time = jcas.getRequiredFeatureDE(casType, "Time", "org.ohnlp.medtime.type.MedTimex3", featOkTst);
    casFeatCode_Time  = (null == casFeat_Time) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Time).getCode();

  }
}



    