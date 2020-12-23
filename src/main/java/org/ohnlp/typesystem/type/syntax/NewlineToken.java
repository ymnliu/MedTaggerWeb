

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.syntax;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;



/** Differentiates a token as being a newline rather than a punctuation, symbol, contraction, word, or number. 
Equivalent to Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.NewlineToken
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class NewlineToken extends BaseToken {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NewlineToken.class);
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
  protected NewlineToken() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NewlineToken(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NewlineToken(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public NewlineToken(JCas jcas, int begin, int end) {
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
     
}

    