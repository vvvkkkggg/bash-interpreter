package org.example;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepCommand implements Command {

    @Override
    public void execute(String[] args) throws IOException {
        Options options = new Options();
        options.addOption("w", true, "Search only whole words");
        options.addOption("i", false, "Perform case-insensitive search");
        options.addOption("A", true, "Print lines after match");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Error parsing command: " + e.getMessage());
            return;
        }

        String[] remainingArgs = cmd.getArgs();
        String filePath;
        String patternString;
        if (remainingArgs.length < 3) {
            System.out.println("Usage: grep [options] <pattern> <file>");
            return;
        }
        else {
            patternString = remainingArgs[1];
            filePath = remainingArgs[2];
        }

        boolean wholeWord = false;
        if (cmd.hasOption("w")) {
            wholeWord = true;
        }

        boolean caseInsensitive = cmd.hasOption("i");
        int linesAfterMatch = 0;

        if (cmd.hasOption("A")) {
            linesAfterMatch = Integer.parseInt(cmd.getOptionValue("A"));
        }

        Pattern pattern;
        if (caseInsensitive) {
            pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(patternString);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean printNextLines = false;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    System.out.println(line);
                    printNextLines = true; // Устанавливаем флаг, чтобы начать выводить следующие строки
                    linesAfterMatch = cmd.hasOption("A") ? Integer.parseInt(cmd.getOptionValue("A")) : 0;
                } else if (printNextLines && linesAfterMatch > 0) {
                    System.out.println(line);
                    linesAfterMatch--;
                }

            }
        }
    }
}