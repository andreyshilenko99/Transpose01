import UI.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    @Test
    void parserTest() {
        String[] commands = new String[]{
                "transpose -a 5 -t -r -o outFileName fileName.txt",
                "transpose -t -r fileName.txt",
                "transpose fileName.txt",
                "transpose -a 8 fileName.txt",
                "transpose -a 1 -t -o outFileName fileName.txt"
        };
        Parser parser1 = new Parser(commands[0]);
        Parser parser2 = new Parser(commands[1]);
        Parser parser3 = new Parser(commands[2]);
        Parser parser4 = new Parser(commands[3]);
        Parser parser5 = new Parser(commands[4]);

        assertEquals(5, parser1.getWordSpace());
        assertEquals("outFileName", parser1.getOutputFileName());
        assertEquals("fileName.txt", parser1.getInputFileName());
        assertTrue(parser1.isRightSide());
        assertTrue(parser1.isTrimmingWord());

        assertEquals("fileName.txt", parser2.getInputFileName());
        assertTrue(parser2.isRightSide());
        assertTrue(parser2.isTrimmingWord());

        assertEquals("fileName.txt", parser3.getInputFileName());
        assertEquals(10, parser3.getWordSpace());

        assertEquals(8, parser4.getWordSpace());
        assertEquals("fileName.txt", parser4.getInputFileName());

        assertEquals(1, parser5.getWordSpace());
        assertEquals("outFileName", parser5.getOutputFileName());
        assertEquals("fileName.txt", parser5.getInputFileName());
        assertFalse(parser5.isRightSide());
        assertTrue(parser5.isTrimmingWord());
    }

}
