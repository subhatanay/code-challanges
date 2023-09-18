package org.codechallenge.wctool.countstrategy;

import java.nio.charset.StandardCharsets;

public class BytesCount implements ICount {

    @Override
    public Integer count(String textString) {
        if (textString == null) throw new IllegalArgumentException("Text can't be null");
        return textString.getBytes(StandardCharsets.UTF_8).length;
    }
}
