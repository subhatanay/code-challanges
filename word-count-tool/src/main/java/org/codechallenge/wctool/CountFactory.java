package org.codechallenge.wctool;

import java.util.Arrays;
import java.util.List;
import org.codechallenge.wctool.countstrategy.BytesCount;
import org.codechallenge.wctool.countstrategy.CharsCount;
import org.codechallenge.wctool.countstrategy.ICount;
import org.codechallenge.wctool.countstrategy.LinesCount;
import org.codechallenge.wctool.countstrategy.WordsCount;

public class CountFactory {

    /**
     *

     * @param countOption - options to fetch the count
     *                      -c -> available byte count
     *                      -w -> word count
     *                      -l -> line count
     *                      -m -> char count
     * @return - list of ICount objects
     */
    public static List<ICount> getCountFactory(String countOption) {

        switch (countOption) {
            case "-c":
                return Arrays.asList(new BytesCount());
            case "-l":
                return Arrays.asList(new LinesCount());
            case "-w":
                return Arrays.asList(new WordsCount());
            case "-m":
                return Arrays.asList(new CharsCount());
            default:
                return Arrays.asList(new BytesCount(), new LinesCount(), new WordsCount(), new CharsCount());
        }

    }

}
