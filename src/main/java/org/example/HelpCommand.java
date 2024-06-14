package org.example;

public class HelpCommand implements Command {

    @Override
    public void execute(String[] args) {
        System.out.println("Available commands:");
        System.out.println("cat <file_path> - prints the file content");
        System.out.println("wc <file_path> - prints lines count");
        System.out.println("grep \"pattern\" <file_path> - will perform a normal search using the pattern \"pattern\".");
        System.out.println("grep -i \"pattern\" <file_path> - will perform a case-insensitive search");
        System.out.println("grep -w \"pattern\" <file_path> - will search only for entire words");
        System.out.println("grep -A n \"pattern\" <file_path> - will search and print n lines after each match");
        System.out.println("help - prints available commands");
    }
}
