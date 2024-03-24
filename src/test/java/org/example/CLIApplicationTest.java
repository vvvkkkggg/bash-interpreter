package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import static org.junit.jupiter.api.Assertions.*;
import org.example.CLIApplication;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class CLIApplicationTest {

    private File createTempFile(String filename, String content) throws IOException {
        File tempFile = File.createTempFile(filename, null);
        System.out.println(tempFile.toPath());
        Files.write(tempFile.toPath(), content.getBytes(), StandardOpenOption.WRITE);
        return tempFile;
    }

    @Test
    void testPipe() throws IOException {
        // Arrange
        String filename = "testfile.txt";
        String fileContents = "Line1\nLine2\nLine3";
        File tempFile = createTempFile(filename, fileContents);
        String line = "cat " + tempFile.toPath() + " | wc";

        // Act
        CLIApplication.Result result = CLIApplication.handleLine(line);

        // Assert
        assertFalse(result.isTerminal);
        assertEquals("Lines: 3\n" +
                "Words: 3\n" +
                "Bytes: 15\n", result.msg);

        tempFile.delete();
    }

    @Test
    void testInterpolation() throws IOException {
        String filename = "testfile.txt";
        String fileContents = "Line1\nLine2\nLine3";
        File tempFile = createTempFile(filename, fileContents);
        String line = "cat $(echo " + tempFile.toPath() + ") | wc";

        // Act
        CLIApplication.Result result = CLIApplication.handleLine(line);

        // Assert
        assertFalse(result.isTerminal);
        assertEquals("Lines: 3\n" +
                "Words: 3\n" +
                "Bytes: 15\n", result.msg);

        tempFile.delete();
    }
}