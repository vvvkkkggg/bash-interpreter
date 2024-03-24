package org.example.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class WCCommand implements Command {

    @Override
    public String execute(List<String> args, String pipeRes) throws IOException {
        long lineCount = 0;
        long wordCount = 0;
        long byteCount = 0;

        if (args.size() < 2) {
            for (String line : pipeRes.split("\n")) {
                lineCount++;
                byteCount += line.getBytes().length;
                wordCount += line.split("\\s+").length;
            }
        } else {
            String filePath = args.get(1);
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    byteCount += line.getBytes().length;
                    wordCount += line.split("\\s+").length;
                }
            }
        }

        return "Lines: " + lineCount + "\nWords: " + wordCount + "\nBytes: " + byteCount + "\n";
    }
}

