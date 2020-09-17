package org.ohnlp.web;

import org.apache.uima.jcas.tcas.Annotation;
import org.json.simple.JSONArray;
import org.ohnlp.medtagger.type.ConceptMention;
import org.ohnlp.medtime.type.MedTimex3;
import java.util.Collection;

public class JSONAnnotation {
    public JSONArray getCmList() {
        return cmList;
    }

    public JSONArray getAttribList() {
        return attribList;
    }

    private JSONArray cmList;
    private JSONArray attribList;

    public JSONAnnotation(JSONArray cmList, JSONArray attribList) {
        this.cmList = cmList;
        this.attribList = attribList;
    }

    public void add(JSONAnnotation toAdd) {
        this.cmList.addAll(toAdd.getCmList());
        this.attribList.addAll(toAdd.getAttribList());
    }

    public int getAnnotMentionSize(){
        return this.cmList.size();
    }

    public int getAnnotAttribSize(){
        return this.attribList.size();
    }


    /**
     * Wrap list of concept mentions into json list to feed into the "entity" js
     * field.
     *
     * @param cms List of Annotation from UIMA
     * @return list of JSON array for Brat to display in the html template
     */
    public static JSONAnnotation generateConceptMentionBratJson(final Collection<Annotation> cms) {
        JSONArray cmList = new JSONArray();
        JSONArray attribList = new JSONArray();

        int entityIdInt = 1;
        int attribIdInt = 1;

        if (cms == null) {
            // Return empty lists
            return new JSONAnnotation(cmList, attribList);
        }

        for (Annotation annot: cms) {
            ConceptMention cm = (ConceptMention) annot;
            // Format: [${ID}, ${TYPE}, [[${START}, ${END}]]]
            // note that range of the offsets are [${START},${END})
            // ref:
            // https://github.com/arne-cl/brat-embedded-visualization-examples/blob/master/minimal-brat-embedded.htm

            JSONArray entityProperties = new JSONArray();
            String bratEntityId = String.format("T%d", entityIdInt++);

            entityProperties.add(bratEntityId);

            // detectionMethod: "DictionaryLookup"
            // detectionMethod: "Matched"
            if (cm.getDetectionMethod().equals("Matched")) {
                entityProperties.add("Regex");
            } else if (cm.getDetectionMethod().equals("DictionaryLookup")) {
                entityProperties.add("Dict");
            } else {
                entityProperties.add("Other");
            }
            JSONArray spans = new JSONArray();
            JSONArray tokenBeginEnd = new JSONArray();
            tokenBeginEnd.add(cm.getBegin());
            tokenBeginEnd.add(cm.getEnd());
            spans.add(tokenBeginEnd);
            entityProperties.add(spans);
            cmList.add(entityProperties);

            /**
             * ConceptMention begin: 9 end: 18 detectionMethod: "Matched" normTarget:
             * "DRY_COUGH" Certainty: "Positive" semGroup: <null> status: "Present"
             * sentence: Sentence experiencer: "Patient"
             */

            JSONArray attribProperties = new JSONArray();
            attribProperties.add(String.format("A%d", attribIdInt++));
            attribProperties.add("norm");
            attribProperties.add(bratEntityId);
            attribProperties.add(cm.getNormTarget());
            attribList.add(attribProperties);

            JSONArray certaintyProperties = new JSONArray();
            certaintyProperties.add(String.format("A%d", attribIdInt++));
            certaintyProperties.add("certainty");
            certaintyProperties.add(bratEntityId);
            certaintyProperties.add(cm.getCertainty());
            attribList.add(certaintyProperties);

            JSONArray statusProperties = new JSONArray();
            statusProperties.add(String.format("A%d", attribIdInt++));
            statusProperties.add("status");
            statusProperties.add(bratEntityId);
            statusProperties.add(cm.getStatus());
            attribList.add(statusProperties);

            JSONArray experiencerProperties = new JSONArray();
            experiencerProperties.add(String.format("A%d", attribIdInt++));
            experiencerProperties.add("experiencer");
            experiencerProperties.add(bratEntityId);
            experiencerProperties.add(cm.getExperiencer());
            attribList.add(experiencerProperties);
        }



        return new JSONAnnotation(cmList, attribList);
    }


    public static JSONAnnotation generateTimex3BratJson(final Collection<org.apache.uima.jcas.tcas.Annotation> timex3s) {
        return generateTimex3BratJson(timex3s, 1, 1);
    }

    /**
     * Wrap list of concept mentions into json list to feed into the "entity" js
     * field.
     *
     * @param timex3s List of Annotation from UIMA
     * @return list of JSON array for Brat to display in the html template
     */
    public static JSONAnnotation generateTimex3BratJson(final Collection<org.apache.uima.jcas.tcas.Annotation> timex3s,
                                                        int entityIdInt, int attribIdInt) {
        JSONArray timex3List = new JSONArray();
        JSONArray attribList = new JSONArray();

        if (timex3s == null) {
            // Return empty lists
            return new JSONAnnotation(timex3List, attribList);
        }

        for (Annotation annot: timex3s) {

            // Format: [${ID}, ${TYPE}, [[${START}, ${END}]]]
            // note that range of the offsets are [${START},${END})
            // ref:
            // https://github.com/arne-cl/brat-embedded-visualization-examples/blob/master/minimal-brat-embedded.htm

            MedTimex3 timex3 = (MedTimex3) annot;
            JSONArray entityProperties = new JSONArray();
            String bratEntityId = String.format("T%d", entityIdInt++);

            entityProperties.add(bratEntityId);

            // detectionMethod: "DictionaryLookup"
            // detectionMethod: "Matched"
            entityProperties.add("Timex3");
            JSONArray spans = new JSONArray();
            JSONArray tokenBeginEnd = new JSONArray();
            tokenBeginEnd.add(timex3.getBegin());
            tokenBeginEnd.add(timex3.getEnd());
            spans.add(tokenBeginEnd);
            entityProperties.add(spans);
            timex3List.add(entityProperties);

            JSONArray typeProperties = new JSONArray();
            typeProperties.add(String.format("A%d", attribIdInt++));
            typeProperties.add("TimexType");
            typeProperties.add(bratEntityId);
            typeProperties.add(timex3.getTimexType());
            attribList.add(typeProperties);

            JSONArray valProperties = new JSONArray();
            valProperties.add(String.format("A%d", attribIdInt++));
            valProperties.add("val");
            valProperties.add(bratEntityId);
            valProperties.add(timex3.getTimexValue());
            attribList.add(valProperties);

        }

        return new JSONAnnotation(timex3List, attribList);
    }


}
