package UI;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Parser {

    public Parser(String command) {
        String[] commandParts = command.split("\\s+");

        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(commandParts);
        } catch (CmdLineException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Argument()
    private String transpose;

    @Option(name = "-a")
    private int wordSpace = 10;

    @Option(name = "-t")
    private boolean trimmingWord = false;

    @Option(name = "-r")
    private boolean rightSide = false;

    @Option(name = "-o")
    private String outputFileName = "";

    @Argument(index = 1)
    private String inputFileName = "";

    public int getWordSpace() {
        return wordSpace;
    }

    public boolean isTrimmingWord() {
        return trimmingWord;
    }

    public boolean isRightSide() {
        return rightSide;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public String getInputFileName() {
        return inputFileName;
    }
}
