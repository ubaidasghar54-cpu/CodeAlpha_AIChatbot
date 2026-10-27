package com.codealpha.chatbot.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles text preprocessing, normalization, and basic NLP utility functions.
 */
public class NLPService {

    /**
     * Cleans and normalizes user input text (lowercase, removes special punctuation).
     */
    public String preprocessText(String text) {
        if (text == null || text.trim().isEmpty()) {
            return "";
        }
        // Convert to lowercase and remove common punctuation marks
        return text.toLowerCase()
                   .replaceAll("[^a-z0-9\\s]", "")
                   .trim();
    }

    /**
     * Tokenizes text into a list of individual words.
     */
    public List<String> tokenize(String text) {
        String cleanText = preprocessText(text);
        if (cleanText.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(cleanText.split("\\s+"))
                     .filter(token -> !token.isBlank())
                     .collect(Collectors.toList());
    }

    /**
     * Calculates a basic keyword match score between user tokens and intent keywords.
     */
    public int calculateMatchScore(List<String> userTokens, List<String> intentKeywords) {
        int score = 0;
        for (String token : userTokens) {
            for (String keyword : intentKeywords) {
                if (token.contains(keyword) || keyword.contains(token)) {
                    score++;
                }
            }
        }
        return score;
    }
}