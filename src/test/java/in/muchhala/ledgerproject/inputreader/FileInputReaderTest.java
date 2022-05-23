package in.muchhala.ledgerproject.inputreader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class FileInputReaderTest {

    @Test
    public void readFileContentShouldReadTheContentsOfTheFileAndReturnListWithAllLines() throws IOException {
        //Given
        String filePath = "src/test/resources/test-data-1.txt";
        List<String> expectedList = List.of("LOAN IDIDI Dale 10000 5 4", "LOAN MBI Harry 2000 2 2", "BALANCE IDIDI Dale 5", "BALANCE IDIDI Dale 40", "BALANCE MBI Harry 12", "BALANCE MBI Harry 0");

        //When
        List<String> actualList = FileInputReader.readFileContent(filePath);

        //Then
        Assertions.assertLinesMatch(expectedList, actualList);
    }
}
