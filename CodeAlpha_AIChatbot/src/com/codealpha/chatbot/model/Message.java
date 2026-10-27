package com.codealpha.chatbot.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an individual chat message in the conversation.
 */
public class Message {
    public enum Sender { USER, BOT }

    private Sender sender;
    private String text;
    private LocalDateTime timestamp;

    public Message(Sender sender, String text) {
        this.sender = sender;
        this.text = text.trim();
        this.timestamp = LocalDateTime.now();
    }

    public Sender getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return timestamp.format(formatter);
    }

    @Override
    public String toString() {
        String senderName = (sender == Sender.USER) ? "You" : "AI Bot";
        return String.format("[%s] %s: %s", getFormattedTimestamp(), senderName, text);
    }
}