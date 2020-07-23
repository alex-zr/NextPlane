package net.nextplane.controller;

import net.nextplane.service.FileService;

import java.nio.file.Path;
import java.util.LinkedList;

public class DefaultConsoleController implements ConsoleController {
    private static final int INITIAL_PRINT_LEVEL = 1;
    private final FileService fileService;

    public DefaultConsoleController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Prints to console file information about all files in hierarchy, recursivelly
     *
     * @param fsRootFileName fsRootFileName of file system to print
     * @param fsRootFilePath
     */
    @Override
    public void printFilesHierarchy(String fsRootFileName, Path fsRootFilePath) {
        LinkedList<String> fileContentInfos = new LinkedList<>();
        long length = fileService.createFileInfoLine(INITIAL_PRINT_LEVEL, fsRootFilePath, fileContentInfos);
        System.out.println(fsRootFileName + " : " + length);

        for (String line : fileContentInfos) {
            System.out.println(line);
        }
    }
}
