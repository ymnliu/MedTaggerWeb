
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.syntax;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;

/** Differentiates a token as being punctuation rather than a contraction, symbol, newline, word, or number. 
Equivalent to Mayo cTAKES version 2.5: edu.mayo.bmi.uima.core.type.PunctuationToken
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * @generated */
public class PunctuationToken_Type extends BaseToken_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = PunctuationToken.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.syntax.PunctuationToken");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public PunctuationToken_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    