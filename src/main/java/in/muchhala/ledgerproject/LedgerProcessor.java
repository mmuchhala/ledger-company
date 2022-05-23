package in.muchhala.ledgerproject;

import in.muchhala.ledgerproject.command.Command;
import in.muchhala.ledgerproject.command.processor.Balance;
import in.muchhala.ledgerproject.command.processor.Loan;
import in.muchhala.ledgerproject.command.processor.Payment;
import in.muchhala.ledgerproject.data.LoanAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static in.muchhala.ledgerproject.util.Constants.SPACE;
import static in.muchhala.ledgerproject.util.Constants.UNDERSCORE;

public class LedgerProcessor {

    private final Map<String, LoanAccount> loans;

    private final Loan loan;
    private final Payment payment;
    private final Balance balance;

    LedgerProcessor(Loan loan, Payment payment, Balance balance) {
        this.loan = loan;
        this.payment = payment;
        this.balance = balance;
        loans = new HashMap<>();
    }


    public List<String> process(List<String> inputStatements) {

        List<String> outputStatements = new ArrayList<>();

        try {
            inputStatements.forEach(statement -> {
                        String[] input = statement.split(SPACE);
                        switch (Command.fromText(input[0])) {
                            case LOAN: {
                                LoanAccount processedLoan = loan.process(input[1], input[2], Double.parseDouble(input[3]), Integer.parseInt(input[4]), Double.parseDouble(input[5]));
                                loans.put(getKey(input[1], input[2]), processedLoan);
                            }

                            case PAYMENT: {
                                payment.process(loans.get(getKey(input[1], input[2])), Double.parseDouble(input[3]), Integer.parseInt(input[4]));
                            }

                            case BALANCE: {
                                outputStatements.add(balance.process(loans.get(getKey(input[1], input[2])), Integer.parseInt(input[3])));
                            }

                            default:
                                break;
                        }
                    }
            );
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e);
        }

        return outputStatements;
    }

    private String getKey(String bankName, String borrowerName) {
        return bankName + UNDERSCORE + borrowerName;
    }
}
