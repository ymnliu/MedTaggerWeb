

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.structured;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** Extend from Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.DocumentID, but its supertype changed to uima.cas.TOP
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class Document extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Document.class);
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
  protected Document() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Document(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Document(JCas jcas) {
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
  //* Feature: id

  /** getter for id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.ohnlp.typesystem.type.structured.Document");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Document_Type)jcasType).casFeatCode_id);}
    
  /** setter for id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "org.ohnlp.typesystem.type.structured.Document");
    jcasType.ll_cas.ll_setStringValue(addr, ((Document_Type)jcasType).casFeatCode_id, v);}    
   
    
  //*--------------*
  //* Feature: fileLoc

  /** getter for fileLoc - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFileLoc() {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_fileLoc == null)
      jcasType.jcas.throwFeatMissing("fileLoc", "org.ohnlp.typesystem.type.structured.Document");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Document_Type)jcasType).casFeatCode_fileLoc);}
    
  /** setter for fileLoc - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFileLoc(String v) {
    if (Document_Type.featOkTst && ((Document_Type)jcasType).casFeat_fileLoc == null)
      jcasType.jcas.throwFeatMissing("fileLoc", "org.ohnlp.typesystem.type.structured.Document");
    jcasType.ll_cas.ll_setStringValue(addr, ((Document_Type)jcasType).casFeatCode_fileLoc, v);}    
  }

    