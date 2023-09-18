package org.codechallange.wctool;

import java.util.List;
import org.codechallenge.wctool.CountFactory;
import org.codechallenge.wctool.countstrategy.BytesCount;
import org.codechallenge.wctool.countstrategy.CharsCount;
import org.codechallenge.wctool.countstrategy.ICount;
import org.codechallenge.wctool.countstrategy.LinesCount;
import org.codechallenge.wctool.countstrategy.WordsCount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountFactoryTest {

    @Test
    public void testWordCountArgument() {
        List<ICount> countList = CountFactory.getCountFactory("-w");
        Assertions.assertEquals(countList.get(0).getClass(), WordsCount.class);
    }

    @Test
    public void testLineCountArgument() {
        List<ICount> countList = CountFactory.getCountFactory("-l");
        Assertions.assertEquals(countList.get(0).getClass(), LinesCount.class);
    }

    @Test
    public void testBytesCountArgument() {
        List<ICount> countList = CountFactory.getCountFactory("-c");
        Assertions.assertEquals(countList.get(0).getClass(), BytesCount.class);
    }

    @Test
    public void testCharsCountArgument() {
        List<ICount> countList = CountFactory.getCountFactory("-m");
        Assertions.assertEquals(countList.get(0).getClass(), CharsCount.class);
    }
}
