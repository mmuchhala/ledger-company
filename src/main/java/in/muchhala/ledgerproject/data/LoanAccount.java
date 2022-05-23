package in.muchhala.ledgerproject.data;

import java.util.ArrayList;
import java.util.List;

public record LoanAccount(
        String bankName,
        String borrowerName,
        double principal,
        int tenureInYears,
        double rateOfInterest,
        double interest,
        double amount,
        Emi emi,
        List<LumpSumPayment> lumpSumPayments) {

    public LoanAccount(String bankName, String borrowerName, double principal, int tenureInYears, double rateOfInterest, double interest, double amount, Emi emi) {
        this(bankName, borrowerName, principal, tenureInYears, rateOfInterest, interest, amount, emi, new ArrayList<>());
    }
}
