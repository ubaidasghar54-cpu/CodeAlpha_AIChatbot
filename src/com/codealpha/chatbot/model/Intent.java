package com.codealpha.chatbot.model;

import java.util.List;

/**
 * Represents an recognized intent (category of user query) with keywords and responses.
 */
public class Intent {
    private String intentName;
    private List<String> keywords;
    private String response;

    public Intent(String intentName, List<String> keywords, String response) {
        this.intentName = intentName;
        this.keywords = keywords;
        this.response = response;
    }

    public String getIntentName() {
        return intentName;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getResponse() {
        return response;
    }

    /**
     * Checks if a given text matches any of this intent's keywords.
     */
    public boolean matches(String text) {
        if (text == null) return false;
        String lowerText = text.toLowerCase();
        for (String keyword : keywords) {
            if (lowerText.contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}