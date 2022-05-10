package com.simpleFileParser.interfaceImpl;

import com.simpleFileParser.interfaces.Statistics;
import com.simpleFileParser.models.Statistic;

public class NumberOfDotsStats implements Statistics {
    private final String NAME;
    private String fileName;

    public NumberOfDotsStats(String fileName) {
        NAME  = "Number of Dots";
        this.fileName = fileName;
    }

    @Override
    public Statistic getStatistics(String text) {
        text = text.replaceAll("[^\\.]", "");
        int count = text.length();
        return new Statistic(NAME, String.valueOf(count), fileName);
    }
}
