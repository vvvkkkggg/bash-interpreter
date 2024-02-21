package org.example;

public class HelpCommand implements Command {

  @Override
  public void execute(String[] args) {
    System.out.println("Available commands:");
    System.out.println("cat <file_path> - prints the file content");
    System.out.println("lc <file_path> - prints lines count");
    System.out.println("help - prints available commands");
  }
}
