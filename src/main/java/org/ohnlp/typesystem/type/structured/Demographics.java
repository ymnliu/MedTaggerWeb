

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.structured;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** Demographic information about the patient in a clinical document.  Typically comes from structured metadata.
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class Demographics extends TOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Demographics.class);
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
  protected Demographics() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Demographics(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Demographics(JCas jcas) {
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
  //* Feature: birthDate

  /** getter for birthDate - gets 
   * @generated
   * @return value of the feature 
   */
  public String getBirthDate() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_birthDate == null)
      jcasType.jcas.throwFeatMissing("birthDate", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_birthDate);}
    
  /** setter for birthDate - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBirthDate(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_birthDate == null)
      jcasType.jcas.throwFeatMissing("birthDate", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_birthDate, v);}    
   
    
  //*--------------*
  //* Feature: deathDate

  /** getter for deathDate - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDeathDate() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_deathDate == null)
      jcasType.jcas.throwFeatMissing("deathDate", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_deathDate);}
    
  /** setter for deathDate - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDeathDate(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_deathDate == null)
      jcasType.jcas.throwFeatMissing("deathDate", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_deathDate, v);}    
   
    
  //*--------------*
  //* Feature: gender

  /** getter for gender - gets 
   * @generated
   * @return value of the feature 
   */
  public String getGender() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_gender);}
    
  /** setter for gender - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setGender(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_gender == null)
      jcasType.jcas.throwFeatMissing("gender", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_gender, v);}    
   
    
  //*--------------*
  //* Feature: firstName

  /** getter for firstName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFirstName() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_firstName == null)
      jcasType.jcas.throwFeatMissing("firstName", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_firstName);}
    
  /** setter for firstName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFirstName(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_firstName == null)
      jcasType.jcas.throwFeatMissing("firstName", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_firstName, v);}    
   
    
  //*--------------*
  //* Feature: middleName

  /** getter for middleName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getMiddleName() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_middleName == null)
      jcasType.jcas.throwFeatMissing("middleName", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_middleName);}
    
  /** setter for middleName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setMiddleName(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_middleName == null)
      jcasType.jcas.throwFeatMissing("middleName", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_middleName, v);}    
   
    
  //*--------------*
  //* Feature: lastName

  /** getter for lastName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLastName() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_lastName == null)
      jcasType.jcas.throwFeatMissing("lastName", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_lastName);}
    
  /** setter for lastName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLastName(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_lastName == null)
      jcasType.jcas.throwFeatMissing("lastName", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_lastName, v);}    
   
    
  //*--------------*
  //* Feature: firstNameSoundex

  /** getter for firstNameSoundex - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFirstNameSoundex() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_firstNameSoundex == null)
      jcasType.jcas.throwFeatMissing("firstNameSoundex", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_firstNameSoundex);}
    
  /** setter for firstNameSoundex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFirstNameSoundex(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_firstNameSoundex == null)
      jcasType.jcas.throwFeatMissing("firstNameSoundex", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_firstNameSoundex, v);}    
   
    
  //*--------------*
  //* Feature: lastNameSoundex

  /** getter for lastNameSoundex - gets 
   * @generated
   * @return value of the feature 
   */
  public String getLastNameSoundex() {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_lastNameSoundex == null)
      jcasType.jcas.throwFeatMissing("lastNameSoundex", "org.ohnlp.typesystem.type.structured.Demographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_lastNameSoundex);}
    
  /** setter for lastNameSoundex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLastNameSoundex(String v) {
    if (Demographics_Type.featOkTst && ((Demographics_Type)jcasType).casFeat_lastNameSoundex == null)
      jcasType.jcas.throwFeatMissing("lastNameSoundex", "org.ohnlp.typesystem.type.structured.Demographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((Demographics_Type)jcasType).casFeatCode_lastNameSoundex, v);}    
  }

    