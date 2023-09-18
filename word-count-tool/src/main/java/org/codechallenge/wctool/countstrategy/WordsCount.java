package org.codechallenge.wctool.countstrategy;

public class WordsCount implements ICount {

    @Override
    public Integer count(String textString) {
        if (textString == null) throw new IllegalArgumentException("Text can't be null");
        String[] words =  textString.split("[\\s|\\t|\\n]");
        int count = 0;
        for (String word : words) {
            if (word.equals("")) continue;
            count++;
        }
        return count;
    }
}
