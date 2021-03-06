

/* First created by JCasGen Tue Sep 24 19:27:48 CDT 2013 */
package org.ohnlp.typesystem.type.textsem;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.ohnlp.typesystem.type.refsem.Event;


/** A text string (IdentifiedAnnotation) that refers to an Event.
 * Updated by JCasGen Fri Oct 23 17:11:18 CDT 2020
 * XML source: C:/Users/Sijia Liu/git/MedTaggerWeb/src/main/resources/org/ohnlp/n3c/types/N3cTypes.xml
 * @generated */
public class EventMention extends IdentifiedAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(EventMention.class);
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
  protected EventMention() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public EventMention(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public EventMention(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public EventMention(JCas jcas, int begin, int end) {
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
  //* Feature: event

  /** getter for event - gets 
   * @generated
   * @return value of the feature 
   */
  public Event getEvent() {
    if (EventMention_Type.featOkTst && ((EventMention_Type)jcasType).casFeat_event == null)
      jcasType.jcas.throwFeatMissing("event", "org.ohnlp.typesystem.type.textsem.EventMention");
    return (Event)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((EventMention_Type)jcasType).casFeatCode_event)));}
    
  /** setter for event - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEvent(Event v) {
    if (EventMention_Type.featOkTst && ((EventMention_Type)jcasType).casFeat_event == null)
      jcasType.jcas.throwFeatMissing("event", "org.ohnlp.typesystem.type.textsem.EventMention");
    jcasType.ll_cas.ll_setRefValue(addr, ((EventMention_Type)jcasType).casFeatCode_event, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    