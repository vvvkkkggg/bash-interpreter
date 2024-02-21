package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LCCommand implements Command {

  @Override
  public void execute(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("Usage: lc <file_path>");
      return;
    }

    String filePath = args[1];
    long lineCount = Files.lines(Paths.get(filePath)).count();
    System.out.println(lineCount);
  }
}
