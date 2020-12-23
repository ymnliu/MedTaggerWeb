

/* First created by JCasGen Tue Sep 24 19:27:47 CDT 2013 */
package org.ohnlp.medtagger.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** Stores the original entry of the dictionary
 * Updated by JCasGen Fri Oct 23 17:11:16 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class DictContext extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DictContext.class);
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
  protected DictContext() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DictContext(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DictContext(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public DictContext(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
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
  //* Feature: entry

  /** getter for entry - gets This contains the original dictionary entry
   * @generated
   * @return value of the feature 
   */
  public String getEntry() {
    if (DictContext_Type.featOkTst && ((DictContext_Type)jcasType).casFeat_entry == null)
      jcasType.jcas.throwFeatMissing("entry", "org.ohnlp.medtagger.type.DictContext");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DictContext_Type)jcasType).casFeatCode_entry);}
    
  /** setter for entry - sets This contains the original dictionary entry 
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntry(String v) {
    if (DictContext_Type.featOkTst && ((DictContext_Type)jcasType).casFeat_entry == null)
      jcasType.jcas.throwFeatMissing("entry", "org.ohnlp.medtagger.type.DictContext");
    jcasType.ll_cas.ll_setStringValue(addr, ((DictContext_Type)jcasType).casFeatCode_entry, v);}    
  }

    