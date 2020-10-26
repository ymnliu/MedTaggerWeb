
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.structured;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** Extend from Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.DocumentID, but its supertype changed to uima.cas.TOP
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * @generated */
public class Document_Type extends TOP_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Document.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.structured.Document");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.ohnlp.typesystem.type.structured.Document");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.ohnlp.typesystem.type.structured.Document");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fileLoc;
  /** @generated */
  final int     casFeatCode_fileLoc;
  /** @generated */ 
  public String getFileLoc(int addr) {
        if (featOkTst && casFeat_fileLoc == null)
      jcas.throwFeatMissing("fileLoc", "org.ohnlp.typesystem.type.structured.Document");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fileLoc);
  }
  /** @generated */    
  public void setFileLoc(int addr, String v) {
        if (featOkTst && casFeat_fileLoc == null)
      jcas.throwFeatMissing("fileLoc", "org.ohnlp.typesystem.type.structured.Document");
    ll_cas.ll_setStringValue(addr, casFeatCode_fileLoc, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public Document_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_fileLoc = jcas.getRequiredFeatureDE(casType, "fileLoc", "uima.cas.String", featOkTst);
    casFeatCode_fileLoc  = (null == casFeat_fileLoc) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fileLoc).getCode();

  }
}



    