package org.example.commands;

import java.io.IOException;
import java.util.List;

public class CdCommand implements Command {
    @Override
    public String execute(List<String> args, String pipeRes) throws IOException {
        if (args.size() > 2) {
            throw new IOException("Usage: ls");
        }

        if (args.size() == 1) {
            System.setProperty("user.dir", System.getProperty("user.home"));
        } else {
            Path newDirectoryPath = Paths.get(System.getProperty("user.dir"), args.get(1));
            if (!(Files.exists(newDirectoryPath) && Files.isDirectory(newDirectoryPath))) {
                throw new IOException("Usage: cd = no such file or directory: " + args.get(1))
            }
            System.setProperty("user.dir", newDirectoryPath.toString());
        }
        System.out.print(Paths.get(System.getProperty("user.dir")).toAbsolutePath().toString());
        return ""
    }
}