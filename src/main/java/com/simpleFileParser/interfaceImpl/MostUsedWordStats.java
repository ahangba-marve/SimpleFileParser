package com.simpleFileParser.interfaceImpl;

import com.simpleFileParser.models.Statistic;
import com.simpleFileParser.interfaces.Statistics;

import java.util.HashMap;
import java.util.Map;

public class MostUsedWordStats implements Statistics {
    private String NAME;
    private String fileName;

    public MostUsedWordStats(String fileName) {
        NAME  = "Most Used words";
        this.fileName = fileName;
    }

    @Override
    public Statistic getStatistics(String text) {
        String[] words = text.split(" ");
        Map<String, Integer> map = new HashMap<>();
        for (String word: words){
            if (map.containsKey(word)){
                map.put(word, map.get(word)+1);
            }
            else map.put(word, 1);
        }

        String wordWithMaxOccurence = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry: map.entrySet()){
            String word = entry.getKey();
            Integer count = entry.getValue();
            if (count>max){
                max = count;
                wordWithMaxOccurence = word;
            }
            else if (count == max){
                if(word.length() < wordWithMaxOccurence.length()){
                    wordWithMaxOccurence = word;
                }
            }

        }

        return new Statistic(NAME, wordWithMaxOccurence, fileName);
    }
}
