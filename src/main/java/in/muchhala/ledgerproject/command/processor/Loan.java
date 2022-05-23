package in.muchhala.ledgerproject.command.processor;

import in.muchhala.ledgerproject.data.Emi;
import in.muchhala.ledgerproject.data.LoanAccount;

public class Loan {

    public LoanAccount process(String bankName, String borrowerName, double principal, int tenureInYears, double rateOfInterest) {
        double interest = principal * tenureInYears * rateOfInterest / 100;
        double amount = principal + interest;

        Emi emi = calculateEmi(amount, tenureInYears);

        return new LoanAccount(bankName, borrowerName, principal, tenureInYears, rateOfInterest, interest, amount, emi);
    }

    private Emi calculateEmi(double amount, int tenureInYears) {
        int tenureInMonths = tenureInYears * 12;

        double roundedUpEmi = Math.ceil(amount / tenureInMonths);
        if (roundedUpEmi * tenureInMonths > amount) {
            double lastEmi = amount - (roundedUpEmi * (tenureInMonths - 1));
            return Emi.of(roundedUpEmi, lastEmi);
        } else {
            return Emi.of(roundedUpEmi);
        }
    }
}
