package seedu.stock.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_COMMAND_SERIAL_NUMBER_FORMAT =
            "Invalid command/serial number format! \n";
    public static final String MESSAGE_DUPLICATE_HEADER_FIELD = "Invalid command format! "
            + "Duplicate header field found in command! \n%1$s";
    public static final String MESSAGE_SERIAL_NUMBER_NOT_FOUND =
            "No stocks are deleted. The stock serial number(s) provided are not found: " + "%1$s";
    public static final String MESSAGE_SOME_SERIAL_NUMBER_NOT_FOUND =
            "The stock serial number(s) provided are not found: " + "%1$s";
    public static final String MESSAGE_NO_SERIAL_NUMBERS_GIVEN =
            "No serial numbers are provided";
    public static final String MESSAGE_STOCKS_LISTED_OVERVIEW = "%1$d stock listed!";
    public static final String MESSAGE_SOURCE_COMPANY_NOT_FOUND = "Source company not found!";

}
