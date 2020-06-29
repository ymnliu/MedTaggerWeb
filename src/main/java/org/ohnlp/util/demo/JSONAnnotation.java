package org.ohnlp.util.demo;

import org.json.simple.JSONArray;

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

}
