

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.textsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.ohnlp.typesystem.type.refsem.Entity;


/** A text string (IdentifiedAnnotation) that refers to an Entity.
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class EntityMention extends IdentifiedAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(EntityMention.class);
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
  protected EntityMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public EntityMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public EntityMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public EntityMention(JCas jcas, int begin, int end) {
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
  //* Feature: entity

  /** getter for entity - gets 
   * @generated
   * @return value of the feature 
   */
  public Entity getEntity() {
    if (EntityMention_Type.featOkTst && ((EntityMention_Type)jcasType).casFeat_entity == null)
      jcasType.jcas.throwFeatMissing("entity", "org.ohnlp.typesystem.type.textsem.EntityMention");
    return (Entity)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((EntityMention_Type)jcasType).casFeatCode_entity)));}
    
  /** setter for entity - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntity(Entity v) {
    if (EntityMention_Type.featOkTst && ((EntityMention_Type)jcasType).casFeat_entity == null)
      jcasType.jcas.throwFeatMissing("entity", "org.ohnlp.typesystem.type.textsem.EntityMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((EntityMention_Type)jcasType).casFeatCode_entity, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    