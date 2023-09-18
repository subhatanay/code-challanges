package org.codechallenge.wctool.readers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Scanner;

public class InputLineReader implements IReader {
    private Scanner scanner;
    public InputLineReader() {
        scanner = new Scanner(new BufferedInputStream(System.in));
    }
    @Override
    public String read() throws IOException {
        StringBuilder dataString = new StringBuilder("");
        while (scanner.hasNextLine()) {
            String ff = scanner.nextLine();
            dataString.append(ff).append(System.lineSeparator());
        }
        return dataString.toString();
    }
}
