package com.simpleFileParser;

import com.simpleFileParser.interfaceImpl.DirectoryWatcherImpl;
import com.simpleFileParser.interfaceImpl.TextFileProcessor;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final String examplePath = "c://users/username/userfolder/targetfolder";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("No command line argument detected.\nApplication requires a folder path passed as a command line argument");
        } else {
            Path folder = Path.of(args[0]);
            if (Files.exists(folder)) {
                if (Files.isDirectory(folder)) {
                    System.out.println("Valid folder detected ===> "+folder);
                    TextFileProcessor processor = new TextFileProcessor(folder);
                    DirectoryWatcherImpl watcher = new DirectoryWatcherImpl(folder, processor);
                    watcher.watchDirectory();
                } else {
                    System.out.println(folder + " is either a file or is not a folder directory. Please enter a valid folder path, example: " + examplePath);
                }

            } else {
                System.out.println(folder + " does not exist in the parent directory");
            }
        }
    }
}