package org.codechallange.wctool;

import org.codechallenge.wctool.countstrategy.BytesCount;
import org.codechallenge.wctool.countstrategy.CharsCount;
import org.codechallenge.wctool.countstrategy.ICount;
import org.codechallenge.wctool.countstrategy.LinesCount;
import org.codechallenge.wctool.countstrategy.WordsCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountStrategyTest {

    @Test
    public void testByteCount() {
        ICount iCount = new BytesCount();
        Assertions.assertEquals(11, iCount.count("Hello World") );
    }
    @Test
    public void testLineCount() {
        ICount iCount = new LinesCount();
        Assertions.assertEquals(2,iCount.count("Hello World" + System.lineSeparator() + "Test file content") );
    }

    @Test
    public void testCharCount() {
        ICount iCount = new CharsCount();
        Assertions.assertEquals(11, iCount.count("Hello World" ));
    }

    @Test
    public void testWordCount() {
        ICount iCount = new WordsCount();
        Assertions.assertEquals(2, iCount.count("Hello World" ));
    }

}
