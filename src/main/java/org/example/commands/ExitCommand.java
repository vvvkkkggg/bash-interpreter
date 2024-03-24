package org.example.commands;

import java.util.List;

public class ExitCommand implements Command {

    @Override
    public String execute(List<String> args, String pipeRes) {
        return "Exiting CLI Application. Goodbye!";
    }
}