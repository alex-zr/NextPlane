package net.nextplane.service;

import java.util.List;

public class DefaultLinesCounterService implements LinesCounterService {
    private static final String LINE_COMMENT_MARKER = "//";
    private static final String START_BLOCK_COMMENT_MARKER = "/*";
    private static final String END_BLOCK_COMMENT_MARKER = "*/";

    private boolean insideBlockComment = false;
    private String currentLine;

    /**
     * Counts code line numbers for line candidates
     *
     * @param lines code candidate lines
     * @return number of code lines
     */
    @Override
    public int countLines(List<String> lines) {
        int linesCount = 0;

        for (String line : lines) {
            currentLine = line.trim();

            while (!currentLine.isEmpty()) {
                if (!insideBlockComment) {
                    if (!isCommented()) {
                        linesCount++;
                        break;
                    }

                    if (isBlockCommentsStarts()) {
                        insideBlockComment = true;
                        currentLine = getBlockCommentsAfterStarts(currentLine).trim();
                    }

                    if (commentedByLineComment()) {
                        break;
                    }

                } else {
                    if (isBlockCommentsEnds(currentLine)) {
                        insideBlockComment = false;
                        currentLine = getBlockCommentsAfterEnds(currentLine).trim();
                    } else {
                        break;
                    }
                }
            }
        }
        return linesCount;
    }

    private boolean isCommented() {
        return commentedByBlockComment() || commentedByLineComment();
    }

    private boolean commentedByLineComment() {
        if (currentLine.length() < LINE_COMMENT_MARKER.length()) {
            return false;
        }

        return currentLine.startsWith(LINE_COMMENT_MARKER);
    }

    private boolean commentedByBlockComment() {
        if (currentLine.length() < START_BLOCK_COMMENT_MARKER.length()) {
            return false;
        }

        if (!currentLine.startsWith(START_BLOCK_COMMENT_MARKER)) {
            return false;
        }

        int endBlockCommentsPosition = currentLine.indexOf(END_BLOCK_COMMENT_MARKER);

        if (endBlockCommentsPosition == -1) {
            return true;
        }

        currentLine = currentLine.substring(endBlockCommentsPosition + END_BLOCK_COMMENT_MARKER.length()).trim();

        if (currentLine.isEmpty()) {
            return true;
        }

        return commentedByBlockComment();
    }

    private boolean isBlockCommentsStarts() {
        return firstMarkerIsAfterSecond(currentLine, START_BLOCK_COMMENT_MARKER, END_BLOCK_COMMENT_MARKER);
    }

    private boolean isBlockCommentsEnds(String line) {
        return firstMarkerIsAfterSecond(line, END_BLOCK_COMMENT_MARKER, START_BLOCK_COMMENT_MARKER);
    }

    private String getBlockCommentsAfterEnds(String line) {
        return line.substring(line.indexOf(END_BLOCK_COMMENT_MARKER) + END_BLOCK_COMMENT_MARKER.length());
    }

    private String getBlockCommentsAfterStarts(String line) {
        return line.substring(line.indexOf(START_BLOCK_COMMENT_MARKER) + END_BLOCK_COMMENT_MARKER.length());
    }

    private boolean firstMarkerIsAfterSecond(String line, String firstMarker, String secondMarker) {
        return line.lastIndexOf(firstMarker) > line.lastIndexOf(secondMarker);
    }
}
