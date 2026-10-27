package CodeAlpha_AIChatbot;

import com.codealpha.chatbot.service.ChatService;
import com.codealpha.chatbot.ui.ChatbotGUI;

import javax.swing.*;

/**
 * Entry point for the CodeAlpha AI Chatbot application.
 */
public class Main {
    public static void main(String[] args) {
        // Set native OS look and feel for modern UI design
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // Run GUI safely on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ChatService chatService = new ChatService();
            ChatbotGUI gui = new ChatbotGUI(chatService);
            gui.setVisible(true);
        });
    }
}