package in.muchhala.ledgerproject.command;

import java.util.Arrays;

public enum Command {
    LOAN,
    PAYMENT,
    BALANCE,
    UNKNOWN;

    public static Command fromText(String text) {
        if (null == text || text.isBlank()) {
            return UNKNOWN;
        }

        return Arrays.stream(Command.values())
                .filter(c -> c.name().equalsIgnoreCase(text.trim()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
