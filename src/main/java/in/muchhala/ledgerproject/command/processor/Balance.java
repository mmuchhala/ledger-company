package in.muchhala.ledgerproject.command.processor;

import in.muchhala.ledgerproject.data.Emi;
import in.muchhala.ledgerproject.data.LoanAccount;
import in.muchhala.ledgerproject.data.LumpSumPayment;

import java.util.List;

import static in.muchhala.ledgerproject.util.Constants.NO_DECIMAL;
import static in.muchhala.ledgerproject.util.Constants.SPACE;

public class Balance {

    public String process(LoanAccount loanAccount, int emiNumber) {
        double lumpSumPayment = calculateLumpSumPaymentsTillGivenEmiNumber(loanAccount.lumpSumPayments(), emiNumber);
        double paid = calculateAmountPaid(loanAccount.amount(), lumpSumPayment, loanAccount.emi(), emiNumber);

        double remaining = loanAccount.amount() - paid;

        double remainingEmi = calculateRemainingEmi(remaining, loanAccount.emi());

        return loanAccount.bankName() + SPACE + loanAccount.borrowerName() + SPACE + NO_DECIMAL.format(paid) + SPACE + NO_DECIMAL.format(remainingEmi);
    }

    private double calculateLumpSumPaymentsTillGivenEmiNumber(List<LumpSumPayment> lumpSumPayments, int emiNumber) {
        return lumpSumPayments.stream().filter(lumpSumPayment -> lumpSumPayment.emiNumber() <= emiNumber)
                .map(LumpSumPayment::payment)
                .mapToDouble(Double::doubleValue).sum();
    }

    private double calculateAmountPaid(double amount, double lumpSumPayment, Emi emi, int emiNumber) {
        double computedAmountPaid = lumpSumPayment + (emi.emi() * emiNumber);
        return Math.min(computedAmountPaid, amount);
    }

    private int calculateRemainingEmi(double remaining, Emi emi) {
        if (remaining <= 0) {
            return 0;
        } else if (remaining <= emi.emi()) {
            return 1;
        } else {
            return (int) Math.ceil(remaining / emi.emi());
        }
    }
}
