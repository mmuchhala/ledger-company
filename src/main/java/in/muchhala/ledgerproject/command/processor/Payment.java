package in.muchhala.ledgerproject.command.processor;

import in.muchhala.ledgerproject.data.Emi;
import in.muchhala.ledgerproject.data.LoanAccount;
import in.muchhala.ledgerproject.data.LumpSumPayment;

import java.util.List;
import java.util.OptionalDouble;

public class Payment {

    public void process(LoanAccount loanAccount, double lumpSumPayment, int emiNumber) {
        loanAccount.lumpSumPayments().add(new LumpSumPayment(emiNumber, lumpSumPayment));

        recomputeLastEmi(loanAccount.amount(), loanAccount.lumpSumPayments(), loanAccount.emi())
                .ifPresent(lastEmi -> loanAccount.emi().lastEmi(lastEmi));
    }

    private OptionalDouble recomputeLastEmi(double amount, List<LumpSumPayment> lumpSumPayments, Emi emi) {
        double lastEmi = (amount - lumpSumPayments.stream().mapToDouble(LumpSumPayment::payment).sum()) % emi.emi();
        return lastEmi == 0 ? OptionalDouble.empty() : OptionalDouble.of(lastEmi);
    }
}
