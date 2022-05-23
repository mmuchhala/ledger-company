package in.muchhala.ledgerproject.command.processor;

import in.muchhala.ledgerproject.data.Emi;
import in.muchhala.ledgerproject.data.LoanAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoanTest {

    Loan loan;

    @BeforeEach
    public void testSetup() {
        loan = new Loan();
    }

    @Test
    public void processShouldCalculateAllParametersAndCreateNewLoanAccount() {
        //Given
        String bankName = "SOME_BANK";
        String borrowerName = "SOME_BORROWER";
        double principal = 10000;
        int tenureInYears = 5;
        double rateOfInterest = 4;

        LoanAccount expectedLoanAccount = new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 5, 4, 2000, 12000, Emi.of(200));

        //When
        LoanAccount outputLoanAccount = loan.process(bankName, borrowerName, principal, tenureInYears, rateOfInterest);

        //Then
        Assertions.assertEquals(expectedLoanAccount, outputLoanAccount);
    }

    @Test
    public void processShouldCalculateAllParametersAndCreateNewLoanAccountWithLastEmiWhenRoundingUpwardsIsInvolved() {
        //Given
        String bankName = "SOME_BANK";
        String borrowerName = "SOME_BORROWER";
        double principal = 10000;
        int tenureInYears = 10;
        double rateOfInterest = 4;

        LoanAccount expectedLoanAccount = new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 10, 4, 4000, 14000, Emi.of(117, 77));

        //When
        LoanAccount outputLoanAccount = loan.process(bankName, borrowerName, principal, tenureInYears, rateOfInterest);

        //Then
        Assertions.assertEquals(expectedLoanAccount, outputLoanAccount);
    }
}
