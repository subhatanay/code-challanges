package org.codechallenge.wctool.readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader implements IReader {

    private String filePath;

    public FileReader(String filePath) {
        if (filePath == null || filePath.length() <=0 ) throw new IllegalArgumentException("filePath can't be null or empty");
        this.filePath = filePath;
    }

    @Override
    public String read() throws IOException {
        if (!Files.exists(Paths.get(filePath))) {
            throw new IOException("File is not exists or not readable");
        }
        return Files.readString(Paths.get(filePath));
    }
}
