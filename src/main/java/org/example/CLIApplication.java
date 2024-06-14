package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class CLIApplication {

    private static final String PROMPT = "> ";
    private static final String[] EXIT_COMMANDS = {"exit", "quit"};

    private static final HashMap<String, Command> COMMANDS;
    static class Result {
        String msg;
        Boolean isTerminal;
    }

    static {
        EXIT_COMMANDS = new HashSet<>();
        EXIT_COMMANDS.add("exit");
        EXIT_COMMANDS.add("quit");

        COMMANDS = new HashMap<>();
        COMMANDS.put("cat", new CatCommand());
        COMMANDS.put("exit", new ExitCommand());
        COMMANDS.put("quit", new ExitCommand());
        COMMANDS.put("wc", new WCCommand());
        COMMANDS.put("echo", new EchoCommand());
        COMMANDS.put("help", new HelpCommand());
        COMMANDS.put("ls", new LsCommand());
        COMMANDS.put("cd", new CdCommand());
    }
  
    public static String makeCommand(String command) {
        return command + "command";
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to CLI Application!");

        while (true) {
            System.out.print(PROMPT);
            String input = scanner.nextLine();
            String[] tokens = input.trim().split("\\s+");

            if (tokens.length == 0) {
                continue;
            }

            String commandName = tokens[0];
            if (shouldExit(commandName)) {
                break;
            }

            boolean commandFound = false;

            for (Command command : COMMANDS) {
                if (makeCommand(commandName).equals(command.getClass().getSimpleName().toLowerCase())) {
                    command.execute(tokens);
                    commandFound = true;
                    break;
                }
            }

            if (!commandFound) {
                System.out.println("Command not found. Type 'help' for available commands.");
            }
        }

        System.out.println("Exiting CLI Application. Goodbye!");
    }

    private static boolean shouldExit(String command) {
        for (String exitCommand : EXIT_COMMANDS) {
            if (command.equalsIgnoreCase(exitCommand)) {
                return true;
            }
        }
        return false;
    }
}