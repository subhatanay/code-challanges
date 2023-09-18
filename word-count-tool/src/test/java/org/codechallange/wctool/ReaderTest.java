package org.codechallange.wctool;

import java.io.IOException;
import org.codechallenge.wctool.readers.FileReader;
import org.codechallenge.wctool.readers.IReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReaderTest {

    @Test
    public void testFileReader() throws IOException {
        IReader reader = new FileReader(ReaderTest.class.getClassLoader().getResource("test.txt").getPath());
        Assertions.assertEquals("This is test", reader.read());
    }

}
