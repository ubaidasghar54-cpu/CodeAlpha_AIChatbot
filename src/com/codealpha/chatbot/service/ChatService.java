package com.codealpha.chatbot.service;

import com.codealpha.chatbot.model.Intent;
import com.codealpha.chatbot.model.Message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatService {
    private List<Intent> intents;
    private NLPService nlpService;
    private CodeSolverService codeSolverService;
    private List<Message> conversationHistory;

    public ChatService() {
        this.intents = new ArrayList<>();
        this.nlpService = new NLPService();
        this.codeSolverService = new CodeSolverService();
        this.conversationHistory = new ArrayList<>();
        loadFAQs("data/faqs.csv");
    }

    public void loadFAQs(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file = new File("CodeAlpha_AIChatbot/" + filePath);
        }
        if (!file.exists()) {
            file = new File("../data/faqs.csv");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    String intentName = parts[0].trim();
                    List<String> keywords = Arrays.asList(parts[1].split("\\|"));
                    String response = parts[2].trim();

                    intents.add(new Intent(intentName, keywords, response));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading FAQ dataset: " + e.getMessage());
        }
    }

    public Message processUserMessage(String userText) {
        Message userMessage = new Message(Message.Sender.USER, userText);
        conversationHistory.add(userMessage);

        String botReplyText = findBestResponse(userText);

        Message botMessage = new Message(Message.Sender.BOT, botReplyText);
        conversationHistory.add(botMessage);

        return botMessage;
    }

    private String findBestResponse(String userText) {
        if (codeSolverService.isCodingQuery(userText)) {
            return codeSolverService.solveProblem(userText);
        }

        List<String> userTokens = nlpService.tokenize(userText);
        
        Intent bestMatch = null;
        int highestScore = 0;

        for (Intent intent : intents) {
            int score = nlpService.calculateMatchScore(userTokens, intent.getKeywords());
            if (score > highestScore) {
                highestScore = score;
                bestMatch = intent;
            }
        }

        if (bestMatch != null && highestScore > 0) {
            return bestMatch.getResponse();
        }

        return "I'm not quite sure about that. Try asking for a coding solution (e.g., 'factorial in C++') or type 'help'!";
    }

    public List<Message> getConversationHistory() {
        return conversationHistory;
    }
}