package in.muchhala.ledgerproject.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class LoanAccount {
    private final String bankName;
    private final String borrowerName;
    private final double principal;
    private final int tenureInYears;
    private final double rateOfInterest;
    private final double interest;
    private final double amount;
    private final Emi emi;
    private final List<LumpSumPayment> lumpSumPayments;

    public LoanAccount(
            String bankName,
            String borrowerName,
            double principal,
            int tenureInYears,
            double rateOfInterest,
            double interest,
            double amount,
            Emi emi,
            List<LumpSumPayment> lumpSumPayments) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.tenureInYears = tenureInYears;
        this.rateOfInterest = rateOfInterest;
        this.interest = interest;
        this.amount = amount;
        this.emi = emi;
        this.lumpSumPayments = lumpSumPayments;
    }

    public LoanAccount(String bankName, String borrowerName, double principal, int tenureInYears, double rateOfInterest, double interest, double amount, Emi emi) {
        this(bankName, borrowerName, principal, tenureInYears, rateOfInterest, interest, amount, emi, new ArrayList<>());
    }

    public String bankName() {
        return bankName;
    }

    public String borrowerName() {
        return borrowerName;
    }

    public double principal() {
        return principal;
    }

    public int tenureInYears() {
        return tenureInYears;
    }

    public double rateOfInterest() {
        return rateOfInterest;
    }

    public double interest() {
        return interest;
    }

    public double amount() {
        return amount;
    }

    public Emi emi() {
        return emi;
    }

    public List<LumpSumPayment> lumpSumPayments() {
        return lumpSumPayments;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LoanAccount) obj;
        return Objects.equals(this.bankName, that.bankName) &&
                Objects.equals(this.borrowerName, that.borrowerName) &&
                Double.doubleToLongBits(this.principal) == Double.doubleToLongBits(that.principal) &&
                this.tenureInYears == that.tenureInYears &&
                Double.doubleToLongBits(this.rateOfInterest) == Double.doubleToLongBits(that.rateOfInterest) &&
                Double.doubleToLongBits(this.interest) == Double.doubleToLongBits(that.interest) &&
                Double.doubleToLongBits(this.amount) == Double.doubleToLongBits(that.amount) &&
                Objects.equals(this.emi, that.emi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrowerName, principal, tenureInYears, rateOfInterest, interest, amount, emi);
    }

    @Override
    public String toString() {
        return "LoanAccount[" +
                "bankName=" + bankName + ", " +
                "borrowerName=" + borrowerName + ", " +
                "principal=" + principal + ", " +
                "tenureInYears=" + tenureInYears + ", " +
                "rateOfInterest=" + rateOfInterest + ", " +
                "interest=" + interest + ", " +
                "amount=" + amount + ", " +
                "emi=" + emi + ']';
    }
}
