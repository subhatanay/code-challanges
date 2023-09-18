package org.codechallenge.wctool.countstrategy;

public class CharsCount implements ICount {
    @Override
    public Integer count(String textString) {
        if (textString == null) throw new IllegalArgumentException("Text can't be null");
        return textString.length();
    }
}
