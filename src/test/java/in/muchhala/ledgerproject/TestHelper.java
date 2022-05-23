package in.muchhala.ledgerproject;

import in.muchhala.ledgerproject.data.Emi;
import in.muchhala.ledgerproject.data.LoanAccount;

public class TestHelper {

    public static LoanAccount createLoanAccountWithNoLastEmiNoLumpSumPayments() {
        return new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 5, 4, 2000, 12000, Emi.of(200));
    }

    public static LoanAccount createLoanAccountWithLastEmiNoLumpSumPayments() {
        return new LoanAccount("SOME_BANK", "SOME_BORROWER", 10000, 10, 4, 4000, 14000, Emi.of(117, 77));
    }
}
