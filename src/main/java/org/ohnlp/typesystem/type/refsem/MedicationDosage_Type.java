
/* First created by JCasGen Tue Sep 24 19:27:47 CDT 2013 */
package org.ohnlp.typesystem.type.refsem;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Dosage indicates how much of a prescribed drug should be taken.  E.g. "one" in "one 5 mg tablet twice-a-day for 2 weeks"
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * @generated */
public class MedicationDosage_Type extends Attribute_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = MedicationDosage.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.refsem.MedicationDosage");
 
  /** @generated */
  final Feature casFeat_value;
  /** @generated */
  final int     casFeatCode_value;
  /** @generated */ 
  public String getValue(int addr) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "org.ohnlp.typesystem.type.refsem.MedicationDosage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_value);
  }
  /** @generated */    
  public void setValue(int addr, String v) {
        if (featOkTst && casFeat_value == null)
      jcas.throwFeatMissing("value", "org.ohnlp.typesystem.type.refsem.MedicationDosage");
    ll_cas.ll_setStringValue(addr, casFeatCode_value, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public MedicationDosage_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_value = jcas.getRequiredFeatureDE(casType, "value", "uima.cas.String", featOkTst);
    casFeatCode_value  = (null == casFeat_value) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_value).getCode();

  }
}



    