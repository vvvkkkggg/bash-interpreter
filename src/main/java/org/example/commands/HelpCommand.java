package org.example.commands;

import java.util.List;

public class HelpCommand implements Command {

    @Override
    public String execute(List<String> args, String pipeRes) {
        return "Available commands:" +
                "cat <file_path> - prints the file content" +
                "wc <file_path> - prints lines count" +
                "help - prints available commands";
    }
}
