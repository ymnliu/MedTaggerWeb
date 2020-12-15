

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.syntax;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Differentiates a token as being a number rather than a punctuation, symbol, newline, word, or contraction. 
Equivalent to Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.NumToken
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class NumToken extends BaseToken {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NumToken.class);
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
  protected NumToken() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NumToken(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NumToken(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NumToken(JCas jcas, int begin, int end) {
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
  //* Feature: numType

  /** getter for numType - gets 
   * @generated
   * @return value of the feature 
   */
  public int getNumType() {
    if (NumToken_Type.featOkTst && ((NumToken_Type)jcasType).casFeat_numType == null)
      jcasType.jcas.throwFeatMissing("numType", "org.ohnlp.typesystem.type.syntax.NumToken");
    return jcasType.ll_cas.ll_getIntValue(addr, ((NumToken_Type)jcasType).casFeatCode_numType);}
    
  /** setter for numType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNumType(int v) {
    if (NumToken_Type.featOkTst && ((NumToken_Type)jcasType).casFeat_numType == null)
      jcasType.jcas.throwFeatMissing("numType", "org.ohnlp.typesystem.type.syntax.NumToken");
    jcasType.ll_cas.ll_setIntValue(addr, ((NumToken_Type)jcasType).casFeatCode_numType, v);}    
  }

    