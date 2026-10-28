package com.codealpha.chatbot.service;

//import java.util.Locale;

/**
 * Handles programming problem solving and code generation for C++ and Java.
 */
public class CodeSolverService {

    /**
     * Checks if the user query is asking for a coding problem solution.
     */
    public boolean isCodingQuery(String query) {
        String lower = query.toLowerCase();
        return lower.contains("code") || lower.contains("solve") || lower.contains("program") 
            || lower.contains("function") || lower.contains("factorial") || lower.contains("palindrome")
            || lower.contains("prime") || lower.contains("fibonacci");
    }

    /**
     * Generates a solution in C++ or Java based on the user's prompt.
     */
    public String solveProblem(String query) {
        String lower = query.toLowerCase();
        boolean isCpp = lower.contains("c++") || lower.contains("cpp");
        boolean isJava = lower.contains("java");

        // Default to Java if language isn't explicitly specified, or match the user's choice
        if (!isCpp && !isJava) {
            isJava = true; // Default fallback
        }

        // 1. Factorial Problem
        if (lower.contains("factorial")) {
            if (isCpp) {
                return "Here is the C++ solution for calculating Factorial:\n\n" +
                       "```cpp\n" +
                       "#iostream>\n" +
                       "using namespace std;\n\n" +
                       "long long factorial(int n) {\n" +
                       "    if (n <= 1) return 1;\n" +
                       "    return n * factorial(n - 1);\n" +
                       "}\n\n" +
                       "int main() {\n" +
                       "    int n = 5;\n" +
                       "    cout << \"Factorial of \" << n << \" is \" << factorial(n) << endl;\n" +
                       "    return 0;\n" +
                       "}\n" +
                       "```";
            } else {
                return "Here is the Java solution for calculating Factorial:\n\n" +
                       "```java\n" +
                       "public class FactorialSolver {\n" +
                       "    public static long calculate(int n) {\n" +
                       "        if (n <= 1) return 1;\n" +
                       "        return n * calculate(n - 1);\n" +
                       "    }\n" +
                       "    public static void main(String[] args) {\n" +
                       "        int n = 5;\n" +
                       "        System.out.println(\"Factorial of \" + n + \" is \" + calculate(n));\n" +
                       "    }\n" +
                       "}\n" +
                       "```";
            }
        }

        // 2. Prime Number Problem
        if (lower.contains("prime")) {
            if (isCpp) {
                return "Here is the C++ solution to check for Prime Numbers:\n\n" +
                       "```cpp\n" +
                       "#include <iostream>\n" +
                       "using namespace std;\n\n" +
                       "bool isPrime(int n) {\n" +
                       "    if (n <= 1) return false;\n" +
                       "    for (int i = 2; i * i <= n; i++) {\n" +
                       "        if (n % i == 0) return false;\n" +
                       "    }\n" +
                       "    return true;\n" +
                       "}\n\n" +
                       "int main() {\n" +
                       "    int num = 29;\n" +
                       "    cout << num << (isPrime(num) ? \" is Prime\" : \" is not Prime\") << endl;\n" +
                       "    return 0;\n" +
                       "}\n" +
                       "```";
            } else {
                return "Here is the Java solution to check for Prime Numbers:\n\n" +
                       "```java\n" +
                       "public class PrimeChecker {\n" +
                       "    public static boolean isPrime(int n) {\n" +
                       "        if (n <= 1) return false;\n" +
                       "        for (int i = 2; i <= Math.sqrt(n); i++) {\n" +
                       "            if (n % i == 0) return false;\n" +
                       "        }\n" +
                       "        return true;\n" +
                       "    }\n" +
                       "    public static void main(String[] args) {\n" +
                       "        int num = 29;\n" +
                       "        System.out.println(num + (isPrime(num) ? \" is Prime\" : \" is not Prime\"));\n" +
                       "    }\n" +
                       "}\n" +
                       "```";
            }
        }

        // 3. Palindrome Problem
        if (lower.contains("palindrome")) {
            if (isCpp) {
                return "Here is the C++ solution to check for a Palindrome string:\n\n" +
                       "```cpp\n" +
                       "#include <iostream>\n" +
                       "#include <string>\n" +
                       "#include <algorithm>\n" +
                       "using namespace std;\n\n" +
                       "bool isPalindrome(string s) {\n" +
                       "    string rev = s;\n" +
                       "    reverse(rev.begin(), rev.end());\n" +
                       "    return s == rev;\n" +
                       "}\n\n" +
                       "int main() {\n" +
                       "    string word = \"radar\";\n" +
                       "    cout << word << (isPalindrome(word) ? \" is Palindrome\" : \" is not Palindrome\") << endl;\n" +
                       "    return 0;\n" +
                       "}\n" +
                       "```";
            } else {
                return "Here is the Java solution to check for a Palindrome string:\n\n" +
                       "```java\n" +
                       "public class PalindromeChecker {\n" +
                       "    public static boolean isPalindrome(String s) {\n" +
                       "        String clean = s.replaceAll(\"[^a-zA-Z0-9]\", \"\").toLowerCase();\n" +
                       "        String reversed = new StringBuilder(clean).reverse().toString();\n" +
                       "        return clean.equals(reversed);\n" +
                       "    }\n" +
                       "    public static void main(String[] args) {\n" +
                       "        String word = \"radar\";\n" +
                       "        System.out.println(word + (isPalindrome(word) ? \" is Palindrome\" : \" is not Palindrome\"));\n" +
                       "    }\n" +
                       "}\n" +
                       "```";
            }
        }

        // Generic fallback if problem isn't recognized yet
        return "I can solve programming problems for you! Try asking: \n" +
               "• 'Write a factorial program in C++'\n" +
               "• 'Check if a number is prime in Java'\n" +
               "• 'Palindrome solution in C++'";
    }
}