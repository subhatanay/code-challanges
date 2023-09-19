package org.codechallenge.wctool;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.codechallenge.wctool.countstrategy.BytesCount;
import org.codechallenge.wctool.countstrategy.CharsCount;
import org.codechallenge.wctool.countstrategy.ICount;
import org.codechallenge.wctool.countstrategy.LinesCount;
import org.codechallenge.wctool.countstrategy.WordsCount;

public enum CountOptionEnum {
    BYTE("-c", new BytesCount()),
    LINE("-l", new LinesCount()),
    WORD("-w", new WordsCount()),
    CHAR("-m", new CharsCount());

    private String option;
    private ICount optionType;
    CountOptionEnum(String option, ICount type) {
        this.option = option;
        this.optionType = type;

    }

    public String getOption() {
        return option;
    }

    public ICount getType() {
        return optionType;
    }

    public static ICount getTypeByOption(String opt) {
        for (CountOptionEnum countOptionEnum : CountOptionEnum.values()) {
            if (countOptionEnum.getOption().equals(opt)) {
                return countOptionEnum.getType();
            }
        }
        return null;
    }

    public static List<String> getOptions() {
        return Arrays.asList(CountOptionEnum.values()).stream().map(CountOptionEnum::getOption).collect(Collectors.toList());
    }


}
