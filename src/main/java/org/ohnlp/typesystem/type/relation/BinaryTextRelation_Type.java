
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.relation;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** A super-type for relationships between two spans of text.
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * @generated */
public class BinaryTextRelation_Type extends Relation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = BinaryTextRelation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.relation.BinaryTextRelation");
 
  /** @generated */
  final Feature casFeat_arg1;
  /** @generated */
  final int     casFeatCode_arg1;
  /** @generated */ 
  public int getArg1(int addr) {
        if (featOkTst && casFeat_arg1 == null)
      jcas.throwFeatMissing("arg1", "org.ohnlp.typesystem.type.relation.BinaryTextRelation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_arg1);
  }
  /** @generated */    
  public void setArg1(int addr, int v) {
        if (featOkTst && casFeat_arg1 == null)
      jcas.throwFeatMissing("arg1", "org.ohnlp.typesystem.type.relation.BinaryTextRelation");
    ll_cas.ll_setRefValue(addr, casFeatCode_arg1, v);}
    
  
 
  /** @generated */
  final Feature casFeat_arg2;
  /** @generated */
  final int     casFeatCode_arg2;
  /** @generated */ 
  public int getArg2(int addr) {
        if (featOkTst && casFeat_arg2 == null)
      jcas.throwFeatMissing("arg2", "org.ohnlp.typesystem.type.relation.BinaryTextRelation");
    return ll_cas.ll_getRefValue(addr, casFeatCode_arg2);
  }
  /** @generated */    
  public void setArg2(int addr, int v) {
        if (featOkTst && casFeat_arg2 == null)
      jcas.throwFeatMissing("arg2", "org.ohnlp.typesystem.type.relation.BinaryTextRelation");
    ll_cas.ll_setRefValue(addr, casFeatCode_arg2, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public BinaryTextRelation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_arg1 = jcas.getRequiredFeatureDE(casType, "arg1", "org.ohnlp.typesystem.type.relation.RelationArgument", featOkTst);
    casFeatCode_arg1  = (null == casFeat_arg1) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_arg1).getCode();

 
    casFeat_arg2 = jcas.getRequiredFeatureDE(casType, "arg2", "org.ohnlp.typesystem.type.relation.RelationArgument", featOkTst);
    casFeatCode_arg2  = (null == casFeat_arg2) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_arg2).getCode();

  }
}



    