package in.muchhala.ledgerproject.command.processor;

import in.muchhala.ledgerproject.TestHelper;
import in.muchhala.ledgerproject.data.LoanAccount;
import in.muchhala.ledgerproject.data.LumpSumPayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class BalanceTest {

    Balance balance;

    static Stream<Arguments> processShouldShowCurrentBalanceAtEmiNumber() {
        return Stream.of(
                Arguments.of(0, "SOME_BANK SOME_BORROWER 0 120"),
                Arguments.of(5, "SOME_BANK SOME_BORROWER 585 115"),
                Arguments.of(119, "SOME_BANK SOME_BORROWER 13923 1"),
                Arguments.of(120, "SOME_BANK SOME_BORROWER 14000 0")
        );
    }

    static Stream<Arguments> processShouldShowBalanceConsideringLumSumpPaymentAtEmiNumber() {
        return Stream.of(
                Arguments.of(2, "SOME_BANK SOME_BORROWER 234 118"),
                Arguments.of(3, "SOME_BANK SOME_BORROWER 1351 109"),
                Arguments.of(4, "SOME_BANK SOME_BORROWER 1468 108"),
                Arguments.of(9, "SOME_BANK SOME_BORROWER 2053 103"),
                Arguments.of(10, "SOME_BANK SOME_BORROWER 4670 80"),
                Arguments.of(11, "SOME_BANK SOME_BORROWER 4787 79")
        );
    }

    @BeforeEach
    public void testSetup() {
        balance = new Balance();
    }

    @ParameterizedTest
    @MethodSource
    public void processShouldShowCurrentBalanceAtEmiNumber(int emiNumber, String expectedOutput) {
        //Given
        LoanAccount loanAccount = TestHelper.createLoanAccountWithLastEmiNoLumpSumPayments();

        //When
        String actualOutput = balance.process(loanAccount, emiNumber);

        //Then
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @ParameterizedTest
    @MethodSource
    public void processShouldShowBalanceConsideringLumSumpPaymentAtEmiNumber(int emiNumber, String expectedOutput) {
        //Given
        LoanAccount loanAccount = TestHelper.createLoanAccountWithLastEmiNoLumpSumPayments();

        //Prepay 1000 at emi number 3
        loanAccount.lumpSumPayments().add(new LumpSumPayment(3, 1000));

        //Prepay 2500 at emi number 10
        loanAccount.lumpSumPayments().add(new LumpSumPayment(10, 2500));

        //When
        String actualOutput = balance.process(loanAccount, emiNumber);

        //Then
        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}
