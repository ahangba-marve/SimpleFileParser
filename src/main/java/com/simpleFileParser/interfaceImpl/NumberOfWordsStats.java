package com.simpleFileParser.interfaceImpl;

import com.simpleFileParser.models.Statistic;
import com.simpleFileParser.interfaces.Statistics;

public class NumberOfWordsStats implements Statistics {
    private final String NAME;
    private String fileName;

    public NumberOfWordsStats(String fileName) {
         NAME = "Number of Words";
         this.fileName = fileName;
    }

    @Override
    public Statistic getStatistics(String text) {
        String[] words = text.split("\\n");

        int count = words.length;
        return new Statistic(NAME, String.valueOf(count), fileName);
    }
}
