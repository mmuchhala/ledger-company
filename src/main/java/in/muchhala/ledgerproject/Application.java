package in.muchhala.ledgerproject;

import in.muchhala.ledgerproject.command.processor.Balance;
import in.muchhala.ledgerproject.command.processor.Loan;
import in.muchhala.ledgerproject.command.processor.Payment;
import in.muchhala.ledgerproject.inputreader.FileInputReader;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        //Initialize the Processors
        Loan loan = new Loan();
        Payment payment = new Payment();
        Balance balance = new Balance();

        LedgerProcessor ledgerProcessor = new LedgerProcessor(loan, payment, balance);

        //The first argument will be the file location from where input will be read
        String filePath = args[0];
        List<String> commands = FileInputReader.readFileContent(filePath);

        //Read contents of the file line by line and process
        List<String> output = ledgerProcessor.process(commands);

        //Show output
        output.forEach(System.out::println);
    }
}
