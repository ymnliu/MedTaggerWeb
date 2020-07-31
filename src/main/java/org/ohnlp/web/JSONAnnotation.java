package org.ohnlp.web;

import org.json.simple.JSONArray;
import org.ohnlp.medtagger.type.ConceptMention;

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

    /**
     * Wrap list of concept mentions into json list to feed into the "entity" js
     * field.
     *
     * @param cms List of Annotation from UIMA
     * @return list of JSON array for Brat to display in the html template
     */
    static JSONAnnotation generateConceptMentionBratJson(final Collection<ConceptMention> cms) {
        JSONArray cmList = new JSONArray();
        JSONArray attribList = new JSONArray();

        int entityIdInt = 1;
        int attribIdInt = 1;

        if (cms == null) {
            // Return empty lists
            return new JSONAnnotation(cmList, attribList);
        }

        for (ConceptMention cm: cms) {

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

}
