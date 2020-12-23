

/* First created by JCasGen Tue Sep 24 19:27:47 CDT 2013 */
package org.ohnlp.typesystem.type.refsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** The proximity of the location in anatomical terms (distal, proximal, superior, anterior and etc.). This is finer-grained to allow combinations of values.
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class BodyLaterality extends Attribute {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(BodyLaterality.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected BodyLaterality() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public BodyLaterality(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public BodyLaterality(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** 
   * <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: value

  /** getter for value - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValue() {
    if (BodyLaterality_Type.featOkTst && ((BodyLaterality_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.typesystem.type.refsem.BodyLaterality");
    return jcasType.ll_cas.ll_getStringValue(addr, ((BodyLaterality_Type)jcasType).casFeatCode_value);}
    
  /** setter for value - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValue(String v) {
    if (BodyLaterality_Type.featOkTst && ((BodyLaterality_Type)jcasType).casFeat_value == null)
      jcasType.jcas.throwFeatMissing("value", "org.ohnlp.typesystem.type.refsem.BodyLaterality");
    jcasType.ll_cas.ll_setStringValue(addr, ((BodyLaterality_Type)jcasType).casFeatCode_value, v);}    
  }

    