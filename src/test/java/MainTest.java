import UI.UI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void justTest() throws IOException {

        List<String> result = Arrays.asList(
                "A      D     ",
                "B      E     ",
                "C            ");
        UI ui = new UI();
        // For windows, change the right slash to double left (\\) in the path (and delete this comment)
        ui.setCommand("transpose -a 5 -o outputFileName.txt src/test/java/fileName.txt");
        ui.start();
        assertEquals(result, Files.readAllLines(Paths.get("outputFileName.txt")));
        new File("outputFileName.txt").delete();

        List<String> result2 = Arrays.asList(
                "     A      D",
                "     B      E",
                "     C       ");

        ui.setCommand("transpose -a 5 -r -o outputFileName.txt src/test/java/fileName.txt");
        ui.start();
        assertEquals(result2, Files.readAllLines(Paths.get("outputFileName.txt")));
        new File("outputFileName.txt").delete();

        List<String> result3 = Arrays.asList(
                "Встре Русск И      Друго Здрас",
                "как-т еврей говор        ну    ",
                "       и      один          приве",
                "       немец                     ");
        ui.setCommand("transpose -a 5 -t -o outputFileName.txt src/test/java/fileName2.txt");
        ui.start();
        assertEquals(result3, Files.readAllLines(Paths.get("outputFileName.txt")));
        new File("outputFileName.txt").delete();
    }

}
