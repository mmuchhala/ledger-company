package in.muchhala.ledgerproject.command.processor;

import in.muchhala.ledgerproject.data.Emi;
import in.muchhala.ledgerproject.data.LoanAccount;
import in.muchhala.ledgerproject.data.LumpSumPayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static in.muchhala.ledgerproject.TestHelper.createLoanAccountWithNoLastEmiNoLumpSumPayments;

public class PaymentTest {

    Payment payment;

    @BeforeEach
    public void testSetup() {
        payment = new Payment();
    }

    @Test
    public void processShouldAddTheLumpSumpPaymentToLoanAccount() {
        //Given
        LoanAccount loanAccount = createLoanAccountWithNoLastEmiNoLumpSumPayments();

        LoanAccount expectedLoanAccount = new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 5, 4, 2000, 12000, Emi.of(200), List.of(new LumpSumPayment(10, 1000)));

        //When
        payment.process(loanAccount, 1000, 10);

        //Then
        Assertions.assertEquals(expectedLoanAccount, loanAccount);
    }

    @Test
    public void processShouldAddTheLumpSumpPaymentToLoanAccountWhenPaidMultipleTimes() {
        //Given
        LoanAccount loanAccount = createLoanAccountWithNoLastEmiNoLumpSumPayments();

        LoanAccount expectedLoanAccount = new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 5, 4, 2000, 12000, Emi.of(200), List.of(new LumpSumPayment(10, 1000), new LumpSumPayment(20, 400)));

        //When
        payment.process(loanAccount, 1000, 10);
        payment.process(loanAccount, 400, 20);

        //Then
        Assertions.assertEquals(expectedLoanAccount, loanAccount);
    }

    @Test
    public void processShouldAddTheLumpSumpPaymentToLoanAccountAndRecomputeTheLastEmi() {
        //Given
        LoanAccount loanAccount = createLoanAccountWithNoLastEmiNoLumpSumPayments();

        LoanAccount expectedLoanAccount = new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 5, 4, 2000, 12000, Emi.of(200, 100), List.of(new LumpSumPayment(10, 500)));

        //When
        payment.process(loanAccount, 500, 10);

        //Then
        Assertions.assertEquals(expectedLoanAccount, loanAccount);
    }


}
