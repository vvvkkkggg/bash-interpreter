package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CatCommand implements Command {

  @Override
  public void execute(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("Usage: cat <file_path>");
      return;
    }

    String filePath = args[1];
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
      }
    }
  }
}
