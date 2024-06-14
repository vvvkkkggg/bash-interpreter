package org.example.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CatCommand implements Command {

    @Override
    public String execute(List<String> args, String pipeRes) throws IOException {
        if (args.size() < 2) {
            throw new IOException("Usage: cat <file_path>");
        }

        String filePath = args.get(1);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }
}
