package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WCCommand implements Command {

    @Override
    public void execute(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Usage: wc <file_path>");
            return;
        }

        String filePath = args[1];
        long lineCount = 0;
        long wordCount = 0;
        long byteCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                byteCount += line.getBytes().length;
                wordCount += line.split("\\s+").length;
            }
        }

        System.out.println("Lines: " + lineCount);
        System.out.println("Words: " + wordCount);
        System.out.println("Bytes: " + byteCount);
    }
}

