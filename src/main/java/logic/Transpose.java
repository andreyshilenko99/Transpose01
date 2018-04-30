package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Transpose {

    private String[][] wordMatrix;

    public Transpose(List<String> lines, int wordSpace, boolean rightSide, boolean trimmingWord) {
        int height = lines.size();
        int width = widthMatrix(lines);
        wordMatrix = new String[width][height];

        for (int i = 0; i < height; ++i) {
            String[] words = lines.get(i).split("\\s+");
            for (int j = 0; j < width; ++j) {
                String word = j < words.length ? words[j] : "";
                word = alignment(word, wordSpace, rightSide, trimmingWord);
                wordMatrix[j][i] = word;
            }
        }
    }

    private int widthMatrix(List<String> lines) {
        int width = -1;
        for (String line : lines) {
            width = Math.max(line.split("\\s+").length, width);
        }
        return width;
    }

    private String alignment(String word, int wordSpace, boolean rightSide, boolean trimmingWord) {
        String alignmentWord = word;
        if (trimmingWord && word.length() > wordSpace) {
            alignmentWord = word.substring(0, wordSpace);
        } else {

            String spaces = IntStream
                    .range(0, Math.abs(word.length() - wordSpace) + 1)
                    .mapToObj(i -> " ")
                    .collect(Collectors.joining());
            if (word.length() < wordSpace) {
                alignmentWord = rightSide ? spaces.concat(word) : word.concat(spaces);
            }
        }
        return alignmentWord;
    }

    public List<String> getTranspose() {
        List<String> result = new ArrayList<>();
        for (String[] words : wordMatrix) {
            result.add(Arrays.toString(words).replaceAll("[\\[\\],]+", ""));
        }
        return result;
    }
}
