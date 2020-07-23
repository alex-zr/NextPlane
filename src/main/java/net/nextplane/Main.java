package net.nextplane;

import net.nextplane.controller.ConsoleController;
import net.nextplane.controller.DefaultConsoleController;
import net.nextplane.service.DefaultFileService;
import net.nextplane.service.DefaultLinesCounterService;
import net.nextplane.service.FileService;
import net.nextplane.service.LinesCounterService;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Missed file or folder name param");
            return;
        }
        LinesCounterService linesCounterService = new DefaultLinesCounterService();
        FileService fileService = new DefaultFileService(linesCounterService);
        ConsoleController consoleController = new DefaultConsoleController(fileService);

        String fsRootFileName = args[0];
        Path fsRootFilePath = Paths.get(fsRootFileName);
        consoleController.printFilesHierarchy(fsRootFileName, fsRootFilePath);
    }
}