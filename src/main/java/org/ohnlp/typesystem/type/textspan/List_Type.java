
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.textspan;

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

/** A semi-structured text span, containing other Annotations (typically Sentences, other Lists, etc).
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * @generated */
public class List_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = List.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.textspan.List");
 
  /** @generated */
  final Feature casFeat_id;
  /** @generated */
  final int     casFeatCode_id;
  /** @generated */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.ohnlp.typesystem.type.textspan.List");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }
  /** @generated */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "org.ohnlp.typesystem.type.textspan.List");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_items;
  /** @generated */
  final int     casFeatCode_items;
  /** @generated */ 
  public int getItems(int addr) {
        if (featOkTst && casFeat_items == null)
      jcas.throwFeatMissing("items", "org.ohnlp.typesystem.type.textspan.List");
    return ll_cas.ll_getRefValue(addr, casFeatCode_items);
  }
  /** @generated */    
  public void setItems(int addr, int v) {
        if (featOkTst && casFeat_items == null)
      jcas.throwFeatMissing("items", "org.ohnlp.typesystem.type.textspan.List");
    ll_cas.ll_setRefValue(addr, casFeatCode_items, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public List_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id  = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_id).getCode();

 
    casFeat_items = jcas.getRequiredFeatureDE(casType, "items", "uima.cas.FSList", featOkTst);
    casFeatCode_items  = (null == casFeat_items) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_items).getCode();

  }
}



    