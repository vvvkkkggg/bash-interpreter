package org.example.commands;

import java.io.IOException;
import java.util.List;

public interface Command {
    String execute(List<String> args, String pipeRes) throws IOException;
}
