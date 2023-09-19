package org.codechallenge.wctool;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.codechallenge.wctool.countstrategy.ICount;

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
        ICount countType = CountOptionEnum.getTypeByOption(countOption);
        if (countType != null) {
            return Arrays.asList(countType);
        } else {
            return Arrays.asList(CountOptionEnum.values()).stream().map(CountOptionEnum::getType).collect(Collectors.toList());
        }
    }

}
