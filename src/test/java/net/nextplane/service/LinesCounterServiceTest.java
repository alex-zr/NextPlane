package net.nextplane.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinesCounterServiceTest {
    public DefaultLinesCounterService linesCounter;

    @BeforeEach
    public void setUp() {
        linesCounter = new DefaultLinesCounterService();
    }

    @Test
    void countLinesBlockAfterBlock() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("BlockAfterBlock.java"));
        assertEquals(2, linesCount);
    }

    @Test
    void countLinesBlockComment() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("BlockComment.java"));
        assertEquals(3, linesCount);
    }

    @Test
    void countLinesCodeAfterBlocks() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("CodeAfterBlocks.java"));
        assertEquals(1, linesCount);
    }

    @Test
    void countLinesCodeBetweenComments() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("CodeBetweenComments.java"));
        assertEquals(1, linesCount);
    }

    @Test
    void countLinesEmptyFile() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("EmptyFile.java"));
        assertEquals(0, linesCount);
    }

    @Test
    void countLinesEmptyLine() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("EmptyLine.java"));
        assertEquals(3, linesCount);
    }

    @Test
    void countLinesInnerBlockComment() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("InnerBlockComment.java"));
        assertEquals(6, linesCount);
    }

    @Test
    void countLinesLineComment() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("LineComment.java"));
        assertEquals(3, linesCount);
    }

    @Test
    void countLinesLineWithComment() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("LineWithComment.java"));
        assertEquals(3, linesCount);
    }

    @Test
    void countLinesNestedBlockComment() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("NestedBlockComment.java"));
        assertEquals(3, linesCount);
    }

    @Test
    void countLinesOnlyComments() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("OnlyComments.java"));
        assertEquals(0, linesCount);
    }

    @Test
    void countLinesWhitespaces() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("Whitespaces.java"));
        assertEquals(0, linesCount);
    }

    @Test
    void blockCommentInsideString() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("BlockCommentInsideString.java"));
        assertEquals(9, linesCount);
    }

    @Test
    void countLinesEndMarkerInString() throws Exception {
        int linesCount = linesCounter.countLines(readFileContentByName("EndMarkerInString.java"));
        assertEquals(5, linesCount);
    }

    private List<String> readFileContentByName(String fileName) throws Exception {
        java.net.URL url = getClass().getClassLoader().getResource(fileName);
        java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
        return java.nio.file.Files.readAllLines(resPath);
    }

}