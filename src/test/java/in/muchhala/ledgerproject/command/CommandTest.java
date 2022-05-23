package in.muchhala.ledgerproject.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static in.muchhala.ledgerproject.command.Command.*;

public class CommandTest {

    static Stream<Arguments> fromTextShouldReturnCorrespondingEnum() {
        return Stream.of(
                Arguments.of("LOAN", LOAN),
                Arguments.of("loan", LOAN),
                Arguments.of("Loan", LOAN),

                Arguments.of("PAYMENT", PAYMENT),
                Arguments.of("payment", PAYMENT),
                Arguments.of("Payment", PAYMENT),

                Arguments.of("BALANCE", BALANCE),
                Arguments.of("balance", BALANCE),
                Arguments.of("Balance", BALANCE)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void fromTextShouldReturnCorrespondingEnum(String inputText, Command expectedCommand) {
        //when
        Command actualCommand = Command.fromText(inputText);

        //then
        Assertions.assertEquals(expectedCommand, actualCommand);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", " ", "Random", "Payments"})
    public void fromTextShouldReturnUnknownWhenNoCorrespondingEnumIsFound(String inputText) {
        //when
        Command actualCommand = Command.fromText(inputText);

        //then
        Assertions.assertEquals(UNKNOWN, actualCommand);
    }
}
