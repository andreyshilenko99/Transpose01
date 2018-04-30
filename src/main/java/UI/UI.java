package UI;

import logic.Transpose;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI {

    private String command = "";
    private static Pattern pattern =
            Pattern.compile("transpose\\s*(-a\\s+\\d+)?\\s*(-t)?\\s*(-r)?\\s*(-o\\s+.+)?\\s*.*");

    public void start() {
        if (command.isEmpty()) {
            command = new Scanner(System.in).nextLine();
        }

        if (!pattern.matcher(command).matches()) throw new IllegalArgumentException("Invalid command");
        Parser parser = new Parser(command);

        List<String> transposeLines =
                new Transpose(getLines(parser.getInputFileName()),
                        parser.getWordSpace(),
                        parser.isRightSide(),
                        parser.isTrimmingWord()).getTranspose();

        showResult(transposeLines, parser.getOutputFileName());
    }

    private List<String> getLines(String fileName) {
        List<String> allLines = new ArrayList<>();
        if (!fileName.isEmpty() && new File(fileName).exists()) {
            try {
                allLines = Files.readAllLines(Paths.get(fileName));
            } catch (IOException e) {
                throw new IllegalArgumentException("Error read file");
            }
        } else {
            String line = "";
            do {
                line = new Scanner(System.in).nextLine();
                allLines.add(line);
            } while (!line.isEmpty());
        }
        return allLines;
    }

    private void showResult(List<String> transposeLines, String outputFileName) {
        if (!outputFileName.isEmpty()) {
            if (new File(outputFileName).exists()) {
                throw new IllegalArgumentException("File is exists");
            } else {
                try {
                    Files.write(Paths.get(outputFileName), transposeLines);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Error create file");
                }
            }
        } else {
            transposeLines.forEach(System.out::println);
        }
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
