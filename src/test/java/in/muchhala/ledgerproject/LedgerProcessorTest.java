package in.muchhala.ledgerproject;

import in.muchhala.ledgerproject.command.processor.Balance;
import in.muchhala.ledgerproject.command.processor.Loan;
import in.muchhala.ledgerproject.command.processor.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class LedgerProcessorTest {

    LedgerProcessor ledgerProcessor;

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of("Sample 1",
                        /* Input     */List.of("LOAN IDIDI Dale 10000 5 4", "LOAN MBI Harry 2000 2 2", "BALANCE IDIDI Dale 5", "BALANCE IDIDI Dale 40", "BALANCE MBI Harry 12", "BALANCE MBI Harry 0"),
                        /* Expected  */List.of("IDIDI Dale 1000 55", "IDIDI Dale 8000 20", "MBI Harry 1044 12", "MBI Harry 0 24")),
                Arguments.of("Sample 2",
                        /* Input     */List.of("LOAN IDIDI Dale 5000 1 6", "LOAN MBI Harry 10000 3 7", "LOAN UON Shelly 15000 2 9", "PAYMENT IDIDI Dale 1000 5", "PAYMENT MBI Harry 5000 10", "PAYMENT UON Shelly 7000 12", "BALANCE IDIDI Dale 3", "BALANCE IDIDI Dale 6", "BALANCE UON Shelly 12", "BALANCE MBI Harry 12"),
                        /* Expected  */List.of("IDIDI Dale 1326 9", "IDIDI Dale 3652 4", "UON Shelly 15856 3", "MBI Harry 9044 10"))
        );
    }

    @BeforeEach
    public void testSetup() {
        ledgerProcessor = new LedgerProcessor(new Loan(), new Payment(), new Balance());
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void processShouldParseCommands(String testName, List<String> givenInput, List<String> expectedOutput) {
        //Given - Just printing test name to gain visibility in logs
        System.out.printf("Test name: " + testName);

        //When
        List<String> actualOutput = ledgerProcessor.process(givenInput);

        //Then
        Assertions.assertLinesMatch(expectedOutput, actualOutput);
    }

    @Test
    public void processShouldContinueOnEncounteringBadCommands() {
        //Given
        List<String> givenInput = List.of(
                "LOAN IDIDI Dale 10000 5 4",
                "LOAN MBI Harry 2000 2 2",
                "LUMPSUM IDIDI Dale 1000 5",
                "BALANCE IDIDI Dale 5",
                "BALANCE IDIDI Dale 40",
                "BALANCE MBI Harry 12",
                "BALANCE MBI Harry 0"
        );

        List<String> expectedOutput = List.of(
                "IDIDI Dale 1000 55",
                "IDIDI Dale 8000 20",
                "MBI Harry 1044 12",
                "MBI Harry 0 24"
        );

        //When
        List<String> actualOutput = ledgerProcessor.process(givenInput);

        //Then
        Assertions.assertLinesMatch(expectedOutput, actualOutput);
    }

    @Test
    public void processOnExceptionShouldReturnSuccessfullyParsedCommands() {
        //Given
        List<String> givenInput = List.of(
                "LOAN IDIDI Dale 10000 5 4",
                "LOAN MBI Harry 2000 2 2",
                "BALANCE IDIDI Dale 5",
                "BALANCE IDIDI Dale Ten",
                "BALANCE MBI Harry 12",
                "BALANCE MBI Harry 0"
        );

        List<String> expectedOutput = List.of("IDIDI Dale 1000 55");

        //When
        List<String> actualOutput = ledgerProcessor.process(givenInput);

        //Then
        Assertions.assertLinesMatch(expectedOutput, actualOutput);
    }
}
