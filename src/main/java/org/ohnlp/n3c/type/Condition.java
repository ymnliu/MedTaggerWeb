

/* First created by JCasGen Fri Oct 23 17:11:18 CDT 2020 */
package org.ohnlp.n3c.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medtime.type.MedTimex3;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class Condition extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Condition.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Condition() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Condition(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Condition(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Condition(JCas jcas, int begin, int end) {
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
  //* Feature: SignSymptom

  /** getter for SignSymptom - gets SignSymptom of COVID events
   * @generated
   * @return value of the feature 
   */
  public ConceptMention getSignSymptom() {
    if (Condition_Type.featOkTst && ((Condition_Type)jcasType).casFeat_SignSymptom == null)
      jcasType.jcas.throwFeatMissing("SignSymptom", "org.ohnlp.n3c.type.Condition");
    return (ConceptMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Condition_Type)jcasType).casFeatCode_SignSymptom)));}
    
  /** setter for SignSymptom - sets SignSymptom of COVID events 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSignSymptom(ConceptMention v) {
    if (Condition_Type.featOkTst && ((Condition_Type)jcasType).casFeat_SignSymptom == null)
      jcasType.jcas.throwFeatMissing("SignSymptom", "org.ohnlp.n3c.type.Condition");
    jcasType.ll_cas.ll_setRefValue(addr, ((Condition_Type)jcasType).casFeatCode_SignSymptom, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Subject

  /** getter for Subject - gets Family Members as concept mentions
   * @generated
   * @return value of the feature 
   */
  public ConceptMention getSubject() {
    if (Condition_Type.featOkTst && ((Condition_Type)jcasType).casFeat_Subject == null)
      jcasType.jcas.throwFeatMissing("Subject", "org.ohnlp.n3c.type.Condition");
    return (ConceptMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Condition_Type)jcasType).casFeatCode_Subject)));}
    
  /** setter for Subject - sets Family Members as concept mentions 
   * @generated
   * @param v value to set into the feature 
   */
  public void setSubject(ConceptMention v) {
    if (Condition_Type.featOkTst && ((Condition_Type)jcasType).casFeat_Subject == null)
      jcasType.jcas.throwFeatMissing("Subject", "org.ohnlp.n3c.type.Condition");
    jcasType.ll_cas.ll_setRefValue(addr, ((Condition_Type)jcasType).casFeatCode_Subject, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: Time

  /** getter for Time - gets 
   * @generated
   * @return value of the feature 
   */
  public MedTimex3 getTime() {
    if (Condition_Type.featOkTst && ((Condition_Type)jcasType).casFeat_Time == null)
      jcasType.jcas.throwFeatMissing("Time", "org.ohnlp.n3c.type.Condition");
    return (MedTimex3)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Condition_Type)jcasType).casFeatCode_Time)));}
    
  /** setter for Time - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTime(MedTimex3 v) {
    if (Condition_Type.featOkTst && ((Condition_Type)jcasType).casFeat_Time == null)
      jcasType.jcas.throwFeatMissing("Time", "org.ohnlp.n3c.type.Condition");
    jcasType.ll_cas.ll_setRefValue(addr, ((Condition_Type)jcasType).casFeatCode_Time, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    