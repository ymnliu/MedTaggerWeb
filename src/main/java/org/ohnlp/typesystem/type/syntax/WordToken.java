

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.syntax;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Differentiates a token as being a word rather than a punctuation, symbol, newline, contraction, or number.
Equivalent to Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.WordToken
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class WordToken extends BaseToken {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(WordToken.class);
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
  protected WordToken() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public WordToken(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public WordToken(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public WordToken(JCas jcas, int begin, int end) {
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
  //* Feature: capitalization

  /** getter for capitalization - gets 
   * @generated
   * @return value of the feature 
   */
  public int getCapitalization() {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_capitalization == null)
      jcasType.jcas.throwFeatMissing("capitalization", "org.ohnlp.typesystem.type.syntax.WordToken");
    return jcasType.ll_cas.ll_getIntValue(addr, ((WordToken_Type)jcasType).casFeatCode_capitalization);}
    
  /** setter for capitalization - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCapitalization(int v) {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_capitalization == null)
      jcasType.jcas.throwFeatMissing("capitalization", "org.ohnlp.typesystem.type.syntax.WordToken");
    jcasType.ll_cas.ll_setIntValue(addr, ((WordToken_Type)jcasType).casFeatCode_capitalization, v);}    
   
    
  //*--------------*
  //* Feature: numPosition

  /** getter for numPosition - gets 
   * @generated
   * @return value of the feature 
   */
  public int getNumPosition() {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_numPosition == null)
      jcasType.jcas.throwFeatMissing("numPosition", "org.ohnlp.typesystem.type.syntax.WordToken");
    return jcasType.ll_cas.ll_getIntValue(addr, ((WordToken_Type)jcasType).casFeatCode_numPosition);}
    
  /** setter for numPosition - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumPosition(int v) {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_numPosition == null)
      jcasType.jcas.throwFeatMissing("numPosition", "org.ohnlp.typesystem.type.syntax.WordToken");
    jcasType.ll_cas.ll_setIntValue(addr, ((WordToken_Type)jcasType).casFeatCode_numPosition, v);}    
   
    
  //*--------------*
  //* Feature: suggestion

  /** getter for suggestion - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSuggestion() {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_suggestion == null)
      jcasType.jcas.throwFeatMissing("suggestion", "org.ohnlp.typesystem.type.syntax.WordToken");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WordToken_Type)jcasType).casFeatCode_suggestion);}
    
  /** setter for suggestion - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSuggestion(String v) {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_suggestion == null)
      jcasType.jcas.throwFeatMissing("suggestion", "org.ohnlp.typesystem.type.syntax.WordToken");
    jcasType.ll_cas.ll_setStringValue(addr, ((WordToken_Type)jcasType).casFeatCode_suggestion, v);}    
   
    
  //*--------------*
  //* Feature: canonicalForm

  /** getter for canonicalForm - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCanonicalForm() {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_canonicalForm == null)
      jcasType.jcas.throwFeatMissing("canonicalForm", "org.ohnlp.typesystem.type.syntax.WordToken");
    return jcasType.ll_cas.ll_getStringValue(addr, ((WordToken_Type)jcasType).casFeatCode_canonicalForm);}
    
  /** setter for canonicalForm - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCanonicalForm(String v) {
    if (WordToken_Type.featOkTst && ((WordToken_Type)jcasType).casFeat_canonicalForm == null)
      jcasType.jcas.throwFeatMissing("canonicalForm", "org.ohnlp.typesystem.type.syntax.WordToken");
    jcasType.ll_cas.ll_setStringValue(addr, ((WordToken_Type)jcasType).casFeatCode_canonicalForm, v);}    
  }

    