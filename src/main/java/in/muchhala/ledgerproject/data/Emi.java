package in.muchhala.ledgerproject.data;

import java.util.Objects;
import java.util.Optional;

public final class Emi {
    private final Double emi;
    private Double lastEmi;  /* For cases where the last emi would be less than emi due to lump sum payment */

    private Emi(
            Double emi,
            Double lastEmi
    ) {
        this.emi = emi;
        this.lastEmi = lastEmi;
    }

    public static Emi of(double emi) {
        return new Emi(emi, null);
    }

    public static Emi of(double emi, double lastEmi) {
        return new Emi(emi, lastEmi);
    }

    public Double emi() {
        return emi;
    }

    public Optional<Double> lastEmi() {
        return Optional.ofNullable(lastEmi);
    }

    public void lastEmi(Double lastEmi) {
        this.lastEmi = lastEmi;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Emi) obj;
        return Objects.equals(this.emi, that.emi) &&
                Objects.equals(this.lastEmi, that.lastEmi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emi, lastEmi);
    }

    @Override
    public String toString() {
        return "Emi[" +
                "emi=" + emi + ", " +
                "lastEmi=" + lastEmi + ']';
    }

}
