package org.ohnlp.web;

/**
 * An almost dummy class for the view
 */
public class WebInputText {
    public String getDocText() {
        return docText;
    }

    private final String docText;

    public WebInputText(String docText) {
        this.docText = docText;
    }
}
