package net.nextplane.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultFileService implements FileService {
    private final LinesCounterService linesCounter;

    public DefaultFileService(LinesCounterService linesCounter) {
        this.linesCounter = linesCounter;
    }

    /**
     * Creates line with line count info for file folder and files inside its
     *
     * @param level depth of file hierarchy
     * @param filePath path of file
     * @param fileInfo line count information about folder ant its files
     * @return lines count
     */
    @Override
    public long createFileInfoLine(int level, Path filePath, LinkedList<String> fileInfo) {
        if (Files.isDirectory(filePath)) {
            List<Path> filesList = Stream.of(Objects.requireNonNull(filePath.toFile().listFiles()))
                    .map(file -> Paths.get(file.getAbsolutePath()))
                    .collect(Collectors.toList());
            long size = 0;
            for (Path path : filesList) {
                long fileSize = createFileInfoLine(level + 1, path, fileInfo);
                StringBuilder levelPrefix = new StringBuilder();
                for (int i = 0; i < level; i++) {
                    levelPrefix.append(' ');
                }
                fileInfo.push(levelPrefix.toString() + path.toFile().getName() + " : " + fileSize);
                size += fileSize;
            }
            return size;
        } else if (Files.isRegularFile(filePath)) {
            try {
                return linesCounter.countLines(Files.readAllLines(filePath));
            } catch (IOException e) {
                System.err.println("Read file error: " + filePath + ", " + e.getMessage());
            }
        }
        return 0;
    }
}
