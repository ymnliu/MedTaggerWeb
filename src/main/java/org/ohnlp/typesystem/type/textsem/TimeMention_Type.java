
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.textsem;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A text string (IdentifiedAnnotation) that refers to a Time (i.e., TIMEX3).
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * @generated */
public class TimeMention_Type extends IdentifiedAnnotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TimeMention.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.textsem.TimeMention");
 
  /** @generated */
  final Feature casFeat_time;
  /** @generated */
  final int     casFeatCode_time;
  /** @generated */ 
  public int getTime(int addr) {
        if (featOkTst && casFeat_time == null)
      jcas.throwFeatMissing("time", "org.ohnlp.typesystem.type.textsem.TimeMention");
    return ll_cas.ll_getRefValue(addr, casFeatCode_time);
  }
  /** @generated */    
  public void setTime(int addr, int v) {
        if (featOkTst && casFeat_time == null)
      jcas.throwFeatMissing("time", "org.ohnlp.typesystem.type.textsem.TimeMention");
    ll_cas.ll_setRefValue(addr, casFeatCode_time, v);}
    
  
 
  /** @generated */
  final Feature casFeat_timeClass;
  /** @generated */
  final int     casFeatCode_timeClass;
  /** @generated */ 
  public String getTimeClass(int addr) {
        if (featOkTst && casFeat_timeClass == null)
      jcas.throwFeatMissing("timeClass", "org.ohnlp.typesystem.type.textsem.TimeMention");
    return ll_cas.ll_getStringValue(addr, casFeatCode_timeClass);
  }
  /** @generated */    
  public void setTimeClass(int addr, String v) {
        if (featOkTst && casFeat_timeClass == null)
      jcas.throwFeatMissing("timeClass", "org.ohnlp.typesystem.type.textsem.TimeMention");
    ll_cas.ll_setStringValue(addr, casFeatCode_timeClass, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public TimeMention_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_time = jcas.getRequiredFeatureDE(casType, "time", "org.ohnlp.typesystem.type.refsem.Time", featOkTst);
    casFeatCode_time  = (null == casFeat_time) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_time).getCode();

 
    casFeat_timeClass = jcas.getRequiredFeatureDE(casType, "timeClass", "uima.cas.String", featOkTst);
    casFeatCode_timeClass  = (null == casFeat_timeClass) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timeClass).getCode();

  }
}



    