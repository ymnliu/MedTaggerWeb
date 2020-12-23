

/* First created by JCasGen Tue Sep 24 19:27:47 CDT 2013 */
package org.ohnlp.typesystem.type.refsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.ohnlp.typesystem.type.relation.ManifestationOf;
import org.ohnlp.typesystem.type.relation.ElementRelation;
import org.ohnlp.typesystem.type.relation.TemporalRelation;
import org.ohnlp.typesystem.type.relation.LocationOf;


/** This is an Event from the UMLS semantic group of Disorders (except that Sign and Symptom types are separate).  Based on generic Clinical Element Models (CEMs)
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class DiseaseDisorder extends Event {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DiseaseDisorder.class);
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
  protected DiseaseDisorder() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public DiseaseDisorder(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public DiseaseDisorder(JCas jcas) {
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
  //* Feature: alleviatingFactor

  /** getter for alleviatingFactor - gets 
   * @generated
   * @return value of the feature 
   */
  public ElementRelation getAlleviatingFactor() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_alleviatingFactor == null)
      jcasType.jcas.throwFeatMissing("alleviatingFactor", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (ElementRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_alleviatingFactor)));}
    
  /** setter for alleviatingFactor - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAlleviatingFactor(ElementRelation v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_alleviatingFactor == null)
      jcasType.jcas.throwFeatMissing("alleviatingFactor", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_alleviatingFactor, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: associatedSignSymptom

  /** getter for associatedSignSymptom - gets 
   * @generated
   * @return value of the feature 
   */
  public ManifestationOf getAssociatedSignSymptom() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_associatedSignSymptom == null)
      jcasType.jcas.throwFeatMissing("associatedSignSymptom", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (ManifestationOf)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_associatedSignSymptom)));}
    
  /** setter for associatedSignSymptom - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAssociatedSignSymptom(ManifestationOf v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_associatedSignSymptom == null)
      jcasType.jcas.throwFeatMissing("associatedSignSymptom", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_associatedSignSymptom, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: bodyLaterality

  /** getter for bodyLaterality - gets 
   * @generated
   * @return value of the feature 
   */
  public BodyLaterality getBodyLaterality() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_bodyLaterality == null)
      jcasType.jcas.throwFeatMissing("bodyLaterality", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (BodyLaterality)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_bodyLaterality)));}
    
  /** setter for bodyLaterality - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBodyLaterality(BodyLaterality v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_bodyLaterality == null)
      jcasType.jcas.throwFeatMissing("bodyLaterality", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_bodyLaterality, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: bodySide

  /** getter for bodySide - gets 
   * @generated
   * @return value of the feature 
   */
  public BodySide getBodySide() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_bodySide == null)
      jcasType.jcas.throwFeatMissing("bodySide", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (BodySide)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_bodySide)));}
    
  /** setter for bodySide - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBodySide(BodySide v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_bodySide == null)
      jcasType.jcas.throwFeatMissing("bodySide", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_bodySide, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: bodyLocation

  /** getter for bodyLocation - gets 
   * @generated
   * @return value of the feature 
   */
  public LocationOf getBodyLocation() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_bodyLocation == null)
      jcasType.jcas.throwFeatMissing("bodyLocation", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (LocationOf)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_bodyLocation)));}
    
  /** setter for bodyLocation - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setBodyLocation(LocationOf v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_bodyLocation == null)
      jcasType.jcas.throwFeatMissing("bodyLocation", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_bodyLocation, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: course

  /** getter for course - gets 
   * @generated
   * @return value of the feature 
   */
  public Course getCourse() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_course == null)
      jcasType.jcas.throwFeatMissing("course", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (Course)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_course)));}
    
  /** setter for course - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCourse(Course v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_course == null)
      jcasType.jcas.throwFeatMissing("course", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_course, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: duration

  /** getter for duration - gets 
   * @generated
   * @return value of the feature 
   */
  public TemporalRelation getDuration() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_duration == null)
      jcasType.jcas.throwFeatMissing("duration", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (TemporalRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_duration)));}
    
  /** setter for duration - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDuration(TemporalRelation v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_duration == null)
      jcasType.jcas.throwFeatMissing("duration", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_duration, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: endTime

  /** getter for endTime - gets 
   * @generated
   * @return value of the feature 
   */
  public Time getEndTime() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_endTime == null)
      jcasType.jcas.throwFeatMissing("endTime", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_endTime)));}
    
  /** setter for endTime - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEndTime(Time v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_endTime == null)
      jcasType.jcas.throwFeatMissing("endTime", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_endTime, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: exacerbatingFactor

  /** getter for exacerbatingFactor - gets 
   * @generated
   * @return value of the feature 
   */
  public ElementRelation getExacerbatingFactor() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_exacerbatingFactor == null)
      jcasType.jcas.throwFeatMissing("exacerbatingFactor", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (ElementRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_exacerbatingFactor)));}
    
  /** setter for exacerbatingFactor - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setExacerbatingFactor(ElementRelation v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_exacerbatingFactor == null)
      jcasType.jcas.throwFeatMissing("exacerbatingFactor", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_exacerbatingFactor, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: startTime

  /** getter for startTime - gets 
   * @generated
   * @return value of the feature 
   */
  public Time getStartTime() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_startTime == null)
      jcasType.jcas.throwFeatMissing("startTime", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (Time)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_startTime)));}
    
  /** setter for startTime - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStartTime(Time v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_startTime == null)
      jcasType.jcas.throwFeatMissing("startTime", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_startTime, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: relativeTemporalContext

  /** getter for relativeTemporalContext - gets 
   * @generated
   * @return value of the feature 
   */
  public TemporalRelation getRelativeTemporalContext() {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_relativeTemporalContext == null)
      jcasType.jcas.throwFeatMissing("relativeTemporalContext", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    return (TemporalRelation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_relativeTemporalContext)));}
    
  /** setter for relativeTemporalContext - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRelativeTemporalContext(TemporalRelation v) {
    if (DiseaseDisorder_Type.featOkTst && ((DiseaseDisorder_Type)jcasType).casFeat_relativeTemporalContext == null)
      jcasType.jcas.throwFeatMissing("relativeTemporalContext", "org.ohnlp.typesystem.type.refsem.DiseaseDisorder");
    jcasType.ll_cas.ll_setRefValue(addr, ((DiseaseDisorder_Type)jcasType).casFeatCode_relativeTemporalContext, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    