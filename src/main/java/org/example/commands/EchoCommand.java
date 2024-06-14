package org.example.commands;

import java.io.IOException;
import java.util.List;

public class EchoCommand implements Command {
    @Override
    public String execute(List<String> args, String pipeRes) throws IOException {
        if (args.size() < 2) {
            throw new IOException("Usage: echo <message>");
        }

        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.size(); i++) {
            message.append(args.get(i)).append(" ");
        }
        return message.toString().trim();
    }
}