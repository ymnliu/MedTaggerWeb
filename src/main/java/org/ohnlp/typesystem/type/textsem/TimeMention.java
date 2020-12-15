

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.textsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.ohnlp.typesystem.type.refsem.Time;


/** A text string (IdentifiedAnnotation) that refers to a Time (i.e., TIMEX3).
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class TimeMention extends IdentifiedAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TimeMention.class);
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
  protected TimeMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public TimeMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public TimeMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public TimeMention(JCas jcas, int begin, int end) {
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
  //* Feature: time

  /** getter for time - gets 
   * @generated
   * @return value of the feature 
   */
  public Time getTime() {
    if (TimeMention_Type.featOkTst && ((TimeMention_Type)jcasType).casFeat_time == null)
      jcasType.jcas.throwFeatMissing("time", "org.ohnlp.typesystem.type.textsem.TimeMention");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((TimeMention_Type)jcasType).casFeatCode_time)));}
    
  /** setter for time - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTime(Time v) {
    if (TimeMention_Type.featOkTst && ((TimeMention_Type)jcasType).casFeat_time == null)
      jcasType.jcas.throwFeatMissing("time", "org.ohnlp.typesystem.type.textsem.TimeMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((TimeMention_Type)jcasType).casFeatCode_time, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: timeClass

  /** getter for timeClass - gets DATE, DURATION, QUANTIFIER, PREPOSTEXP, etc.
   * @generated
   * @return value of the feature 
   */
  public String getTimeClass() {
    if (TimeMention_Type.featOkTst && ((TimeMention_Type)jcasType).casFeat_timeClass == null)
      jcasType.jcas.throwFeatMissing("timeClass", "org.ohnlp.typesystem.type.textsem.TimeMention");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TimeMention_Type)jcasType).casFeatCode_timeClass);}
    
  /** setter for timeClass - sets DATE, DURATION, QUANTIFIER, PREPOSTEXP, etc. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTimeClass(String v) {
    if (TimeMention_Type.featOkTst && ((TimeMention_Type)jcasType).casFeat_timeClass == null)
      jcasType.jcas.throwFeatMissing("timeClass", "org.ohnlp.typesystem.type.textsem.TimeMention");
    jcasType.ll_cas.ll_setStringValue(addr, ((TimeMention_Type)jcasType).casFeatCode_timeClass, v);}    
  }

    