

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.refsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Concepts in the Unified Medical Language System (UMLS) Metathesaurus have a concept unique identifier (CUI) and a type unique identifier (TUI, i.e., semantic type) which are curated, normalized codes. For example, "pain" would have a cui=C0030193 and tui=T184.
Equivalent to Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.UmlsConcept
 * Updated by JCasGen Fri Oct 23 17:11:17 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class UmlsConcept extends OntologyConcept {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(UmlsConcept.class);
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
  protected UmlsConcept() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public UmlsConcept(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public UmlsConcept(JCas jcas) {
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
  //* Feature: cui

  /** getter for cui - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCui() {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_cui == null)
      jcasType.jcas.throwFeatMissing("cui", "org.ohnlp.typesystem.type.refsem.UmlsConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_cui);}
    
  /** setter for cui - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCui(String v) {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_cui == null)
      jcasType.jcas.throwFeatMissing("cui", "org.ohnlp.typesystem.type.refsem.UmlsConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_cui, v);}    
   
    
  //*--------------*
  //* Feature: tui

  /** getter for tui - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTui() {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_tui == null)
      jcasType.jcas.throwFeatMissing("tui", "org.ohnlp.typesystem.type.refsem.UmlsConcept");
    return jcasType.ll_cas.ll_getStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_tui);}
    
  /** setter for tui - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTui(String v) {
    if (UmlsConcept_Type.featOkTst && ((UmlsConcept_Type)jcasType).casFeat_tui == null)
      jcasType.jcas.throwFeatMissing("tui", "org.ohnlp.typesystem.type.refsem.UmlsConcept");
    jcasType.ll_cas.ll_setStringValue(addr, ((UmlsConcept_Type)jcasType).casFeatCode_tui, v);}    
  }

    