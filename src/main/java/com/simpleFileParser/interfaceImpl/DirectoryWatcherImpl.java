package com.simpleFileParser.interfaceImpl;

import com.simpleFileParser.interfaces.DirectoryWatcher;
import com.simpleFileParser.interfaces.FIleProcessor;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcherImpl implements DirectoryWatcher {
    private Path pathToWatch;
    private Path processedFolder;
    private WatchKey key;
    private final String PROCESSED = "processed";
    private FIleProcessor textFileProcessor;


    public DirectoryWatcherImpl(Path pathToWatch, TextFileProcessor textFileProcessor) {
        this.pathToWatch = pathToWatch;
        this.textFileProcessor = textFileProcessor;
    }

    public void watchDirectory(){
        createProcessedFolder(pathToWatch);
        System.out.println("Directory is being monitored for new and existing files....please wait");
        initializeWatchService(pathToWatch);
    }

    private void initializeWatchService(Path watchDirectory) {
        try(WatchService watcher = FileSystems.getDefault().newWatchService()) {
            watchDirectory.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

            do {
                try {
                    if (Files.list(watchDirectory).findAny().isPresent()){
                        Files.newDirectoryStream(watchDirectory, "*.txt")
                                .forEach(textFile -> processFiles(textFile));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    key = watcher.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (WatchEvent<?> ev : key.pollEvents()) {
                    WatchEvent<Path> event = (WatchEvent<Path>) ev;
                    Path path2 = event.context();
                    processFiles(path2);
                }
            }
            while (key.reset());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void processFiles(Path textFile) {
        String content = textFileProcessor.readFileContent(textFile);
        textFileProcessor.printStatistics(content, textFile.getFileName().toString());
        textFileProcessor.moveToProcessedDirectory(textFile, processedFolder);

    }

    private void createProcessedFolder(Path path){
        processedFolder = path.resolve(PROCESSED);
        if (Files.exists(path) && Files.isDirectory(path)){
            try {
                if (!Files.exists(processedFolder)) {
                    processedFolder = Files.createDirectory(processedFolder);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
