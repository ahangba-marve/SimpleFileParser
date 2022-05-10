package com.simpleFileParser.interfaces;

import java.nio.file.Path;

public interface FIleProcessor {
    String readFileContent(Path file);
    void moveToProcessedDirectory(Path directory, Path destination);
    void printStatistics(String content, String fileName);
}
