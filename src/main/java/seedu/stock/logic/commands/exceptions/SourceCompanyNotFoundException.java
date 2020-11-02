package seedu.stock.logic.commands.exceptions;

import seedu.stock.logic.commands.SourceQuantityDistributionStatisticsCommand;

/**
 * Represents an error which occurs during execution of a {@link SourceQuantityDistributionStatisticsCommand}.
 */
public class SourceCompanyNotFoundException extends Exception {
    public SourceCompanyNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code SourceCompanyNotFoundException} with the specified detail
     * {@code message} and {@code cause}.
     */
    public SourceCompanyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
