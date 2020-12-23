
/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.syntax;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.ohnlp.typesystem.type.relation.BinaryTextRelation_Type;

/** Stanford dependencies provide a representation of grammatical relations between words in a sentence. Stanford dependencies are triplets: name of the relation, governor and dependent.
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * @generated */
public class StanfordDependency_Type extends BinaryTextRelation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = StanfordDependency.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.ohnlp.typesystem.type.syntax.StanfordDependency");



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public StanfordDependency_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

  }
}



    