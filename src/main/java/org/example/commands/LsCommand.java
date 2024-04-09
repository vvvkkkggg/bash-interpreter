package org.example.commands;

import java.io.IOException;
import java.util.List;

import org.example.CLIApplication;

public class LsCommand implements Command {
    @Override
    public String execute(List<String> args, String pipeRes) throws IOException {
        if (args.size() > 2) {
            throw new IOException("Usage: ls");
        }

        StringBuilder result = new StringBuilder();
        File currentDir;

        if (args.size() == 2) {
            currentDir = new File(args.get(1));
        } else {
            currentDir = new File(System.getProperty(CLIApplication.currentDir));
        }

        File[] files = currentDir.listFiles();

        if (files != null) {
            for (File file : files) {
                result.append(file.getName()).append("\n");
            }
        }

        return result.toString();
    }
}