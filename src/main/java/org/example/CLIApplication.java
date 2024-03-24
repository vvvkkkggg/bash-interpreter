package org.example;

import org.example.commands.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class CLIApplication {

    private static final String PROMPT = "> ";
    private static final Set<String> EXIT_COMMANDS;

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
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to CLI Application!");

        while (true) {
            System.out.print(PROMPT);
            String input = scanner.nextLine();

            Result res = handleLine(input);

            if (!res.msg.isEmpty()){
                System.out.println(res.msg);
            }
            if (res.isTerminal) {
                break;
            }
        }
    }

    static Result handleLine(String line) {
        Result res = new Result();
        for (String segment : line.split("\\|")) {
            try {
                segment = interpolate(segment);
            } catch (IOException e) {
                res.msg = e.getMessage();
                break;
            }

            List<String> tokens = List.of(segment.trim().split("\\s+"));
            res.isTerminal = EXIT_COMMANDS.contains(tokens.get(0).toLowerCase());
            try {
                res.msg = executeCmd(tokens, res.msg);
            } catch (IOException e) {
                res.msg = e.getMessage();
                break;
            }
        }
        return res;
    }

    private static String executeCmd(List<String> tokens, String pipeRes) throws IOException {
        String commandName = tokens.get(0).toLowerCase();

        if (!COMMANDS.containsKey(commandName)) {
            throw new IOException("Command not found. Type 'help' for available commands.");
        }

        return COMMANDS.get(commandName).execute(tokens, pipeRes);
    }

    private static String interpolate(String initial) throws IOException {
        String patternCommand = "\\$\\(.*?\\)";
        Pattern rCmd = Pattern.compile(patternCommand);
        Matcher matcherCmd = rCmd.matcher(initial);
        StringBuilder result = new StringBuilder();
        boolean found = false;
        while (matcherCmd.find()) {
            found = true;
            String match = matcherCmd.group();
            matcherCmd.appendReplacement(result, executeCmd(List.of(match
                            .substring(2, match.length() - 1)
                            .trim()
                            .split("\\s+")),
                    ""));
        }
        if (!found) {
            return initial;
        }
        // todo аналогично с переменными, у нас просто сейчас переменных нет
        return result.toString();
    }
}
