package net.nextplane.service;

import java.nio.file.Path;
import java.util.LinkedList;

public interface FileService {
    long createFileInfoLine(int printLevel, Path fsRootFilePath, LinkedList<String> fileContentInfos);
}
