package in.muchhala.ledgerproject.data;

import java.util.Objects;

public final class LumpSumPayment {
    private final int emiNumber;
    private final double payment;

    public LumpSumPayment(
            int emiNumber,
            double payment
    ) {
        this.emiNumber = emiNumber;
        this.payment = payment;
    }

    public int emiNumber() {
        return emiNumber;
    }

    public double payment() {
        return payment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LumpSumPayment) obj;
        return this.emiNumber == that.emiNumber &&
                Double.doubleToLongBits(this.payment) == Double.doubleToLongBits(that.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emiNumber, payment);
    }

    @Override
    public String toString() {
        return "LumpSumPayment[" +
                "emiNumber=" + emiNumber + ", " +
                "payment=" + payment + ']';
    }

}
