package com.simpleFileParser.interfaceImpl;

import com.simpleFileParser.interfaceImpl.MostUsedWordStats;
import com.simpleFileParser.interfaceImpl.NumberOfDotsStats;
import com.simpleFileParser.interfaceImpl.NumberOfWordsStats;
import com.simpleFileParser.interfaces.FIleProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileProcessor implements FIleProcessor {
    Path file;

    public TextFileProcessor(Path textFile) {
        file = textFile;
    }

    public String readFileContent(Path textFile) {
        String str = "";
        try {
            str = Files.readString(textFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public void moveToProcessedDirectory(Path textFile, Path destination) {
        try {
            Files.move(textFile, destination.resolve(textFile.getFileName().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStatistics(String content, String fileName) {
        NumberOfWordsStats numOfWords = new NumberOfWordsStats(fileName);
        NumberOfDotsStats numOfDots = new NumberOfDotsStats(fileName);
        MostUsedWordStats mostUsed = new MostUsedWordStats(fileName);
        numOfWords.getStatistics(content).printStats();
        numOfDots.getStatistics(content).printStats();
        mostUsed.getStatistics(content).printStats();
        System.out.println(content);
    }
}
