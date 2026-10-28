# CodeAlpha AI Chatbot (Task 3)

A robust, object-oriented desktop AI Chatbot built in **Java** utilizing **Java Swing** for a modern graphical user interface, a custom **MVC (Model-View-Controller)** architecture, local file-based persistent knowledge management via CSV, rule-based NLP intent matching, and a built-in **C++ / Java Code Solver** assistant.

---

## 🚀 Features

- **Modern GUI (Java Swing):** Sleek desktop chat window featuring dynamic message bubbles, auto-scrolling, a dark header banner, and responsive layout designs.
- **Rule-Based NLP Engine:** Text preprocessing, punctuation removal, tokenization, and multi-keyword matching scores to evaluate user queries.
- **Persistent Knowledge Base (`faqs.csv`):** Dynamic FAQ loading to answer questions regarding company details, services, programming concepts, and greetings.
- **AI Code Solver Service:** Automatically recognizes programming queries and generates formatted code solutions for popular algorithms (Factorial, Prime Number, Palindrome) in **C++** and **Java**.
- **Clean MVC Architecture:** Modular code separated into `model`, `service`, and `ui` packages for clean scalability.

---

## 📂 Project Directory Structure

```text
CodeAlpha_AIChatbot/
│
├── data/
│   └── faqs.csv                  # Knowledge base for intents, keywords, and responses
├── src/
│   └── com/
│       └── codealpha/
│           └── chatbot/
│               ├── Main.java     # Application entry point
│               ├── model/        # Data models (Intent.java, Message.java)
│               ├── service/      # Business logic (ChatService.java, NLPService.java, CodeSolverService.java)
│               └── ui/           # GUI interface (ChatbotGUI.java)
└── README.md