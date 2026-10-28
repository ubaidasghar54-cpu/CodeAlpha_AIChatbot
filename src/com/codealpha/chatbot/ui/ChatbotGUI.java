package com.codealpha.chatbot.ui;

import com.codealpha.chatbot.model.Message;
import com.codealpha.chatbot.service.ChatService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatbotGUI extends JFrame {
    private ChatService chatService;
    private JPanel chatPanel;
    private JScrollPane scrollPane;
    private JTextField inputField;
    private JButton sendButton;

    public ChatbotGUI(ChatService chatService) {
        this.chatService = chatService;
        
        setTitle("CodeAlpha AI Assistant");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        appendWelcomeMessage();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(30, 41, 59));
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("CodeAlpha AI Assistant & Code Solver");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JLabel statusLabel = new JLabel("● Online");
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        statusLabel.setForeground(new Color(74, 222, 128));
        headerPanel.add(statusLabel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Chat Container
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
        chatPanel.setBackground(Color.WHITE);
        chatPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        scrollPane = new JScrollPane(chatPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Input Panel
        JPanel inputContainer = new JPanel(new BorderLayout(10, 10));
        inputContainer.setBackground(new Color(245, 247, 250));
        inputContainer.setBorder(new EmptyBorder(12, 15, 15, 15));

        inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(203, 213, 225), 1, true),
            BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));
        inputField.addActionListener((ActionEvent e) -> handleSendMessage());

        sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(37, 99, 235));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sendButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        sendButton.addActionListener((ActionEvent e) -> handleSendMessage());

        inputContainer.add(inputField, BorderLayout.CENTER);
        inputContainer.add(sendButton, BorderLayout.EAST);

        mainPanel.add(inputContainer, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void appendWelcomeMessage() {
        addMessageBubble("Hello! I am your CodeAlpha AI Assistant. Ask about our programs, or ask me to write code solutions in C++ or Java (e.g., 'factorial in C++').", Message.Sender.BOT);
    }

    private void handleSendMessage() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) {
            return;
        }

        addMessageBubble(text, Message.Sender.USER);
        inputField.setText("");

        Message botMsg = chatService.processUserMessage(text);
        addMessageBubble(botMsg.getText(), Message.Sender.BOT);
    }

    private void addMessageBubble(String text, Message.Sender sender) {
        JPanel bubbleWrapper = new JPanel(new FlowLayout(sender == Message.Sender.USER ? FlowLayout.RIGHT : FlowLayout.LEFT, 10, 5));
        bubbleWrapper.setBackground(new Color(255, 255, 255));

        JTextArea bubble = new JTextArea(text);
        bubble.setFont(new Font(sender == Message.Sender.BOT && text.contains("public") ? "Consolas" : "Segoe UI", Font.PLAIN, 13));
        bubble.setLineWrap(true);
        bubble.setWrapStyleWord(true);
        bubble.setEditable(false);
        bubble.setFocusable(false);
        
        int maxWidth = 380;
        bubble.setSize(new Dimension(maxWidth, Short.MAX_VALUE));
        Dimension preferredSize = bubble.getPreferredSize();
        if (preferredSize.width > maxWidth) {
            bubble.setPreferredSize(new Dimension(maxWidth, preferredSize.height + 15));
        } else {
            bubble.setPreferredSize(new Dimension(preferredSize.width + 10, preferredSize.height + 10));
        }

        if (sender == Message.Sender.USER) {
            bubble.setBackground(new Color(37, 99, 235));
            bubble.setForeground(Color.WHITE);
            bubble.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        } else {
            bubble.setBackground(new Color(241, 245, 249));
            bubble.setForeground(new Color(15, 23, 42));
            bubble.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        }

        bubbleWrapper.add(bubble);
        chatPanel.add(bubbleWrapper);
        chatPanel.revalidate();
        chatPanel.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
        });
    }
}