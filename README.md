# SIMPLE FILE PARSER

**Instructions**
The application can be run on your IDE terminal, simply:
1) unzip the folder
2) open the application in your IDE(Preferably IntelliJ Idea)
3) edit configuration by putting a valid folder path (path can be a string) into your environment variable, the path will
   be the folder that the application will monitor for new and existing files as it runs.
4) run the application from your IDE

**Packages**

The Simple file parser application has a package called **interfaces** that house all interfaces used.
The application has a **interfaceImpl** package that contains all interface implementation classes.
The application has a **models** interface which is where the statistical model resides. 
The statistical model has the following fields; _name_, _data_ and _fileName_. 

**DirectoryWatcher Interface**
This interface is implemented by the DirectoryWatcherImpl class.
The DirectoryWatcherImpl class is responsible for watching a directory and ensuring that whenever a
create event is triggered on the directory, the application is notified and the file or files added are further processed.
The DirectoryWatcherImpl has a method that starts watching the directory(the one provided in the environment variable) when invoked and checks 
for supported files. If a supported file is found, the **processFile** method is called to process the file.

**FileProcessor Interface**
This interface is implemented by the TextFileProcessor class. Other file processors can implement this interface later in the future.
The TextFileProcessor class is responsible for reading the contents of the file into a string value which will is processed by 
the **printStatistics** method to display statistics of the file content.
The TextFileProcessor also implement methods for moving files to the _processed_ directory.

**Statistics Interface**
This interface is implemented by classes that perform statistic checks on the files that are added to the folder being watched by the DirectoryWatcher.
The **NumberOfDotsStats** class checks for number of dots in a file. 
The **NumberOfWordsStats** class checks for number of words in a file.
The **MostUsedWordStats** check for the most used word in a file.