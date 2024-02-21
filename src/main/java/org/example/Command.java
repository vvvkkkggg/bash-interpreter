package org.example;

import java.io.IOException;

interface Command {

  void execute(String[] args) throws IOException;
}
