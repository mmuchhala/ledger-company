package in.muchhala.ledgerproject;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ApplicationTest {

    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void mainShouldRunTheApplication() throws IOException {
        //Given
        String filePath = "src/test/resources/test-data-2.txt";
        String expectedOutput = """
                IDIDI Dale 1326 9
                IDIDI Dale 3652 4
                UON Shelly 15856 3
                MBI Harry 9044 10
                """.trim();

        //When
        Application.main(new String[]{filePath});

        //Then
        Assertions.assertEquals(expectedOutput, outContent.toString().trim());
    }
}
