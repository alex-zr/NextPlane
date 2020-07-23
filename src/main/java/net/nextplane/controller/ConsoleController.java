package net.nextplane.controller;

import java.nio.file.Path;

public interface ConsoleController {
    void printFilesHierarchy(String fsRootFileName, Path fsRootFilePath);
}
