package seedu.stock.logic.commands;

import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_APPLE = "Apple Juice";
    public static final String VALID_NAME_BANANA = "Banana Bun";
    public static final String VALID_SERIAL_NUMBER_APPLE = "Ntuc1";
    public static final String VALID_SERIAL_NUMBER_BANANA = "Fairprice1";
    public static final String VALID_SOURCE_APPLE = "Ntuc";
    public static final String VALID_SOURCE_BANANA = "Fairprice";
    public static final String VALID_QUANTITY_APPLE = "2000";
    public static final String VALID_QUANTITY_BANANA = "1000";
    public static final String VALID_LOCATION_APPLE = "Fruit Section, Subsection C";
    public static final String VALID_LOCATION_BANANA = "Fruits section, Subsection B";

    public static final String NAME_DESC_APPLE = " " + PREFIX_NAME + VALID_NAME_APPLE;
    public static final String NAME_DESC_BANANA = " " + PREFIX_NAME + VALID_NAME_BANANA;
    public static final String SERIAL_NUMBER_DESC_APPLE = " " + PREFIX_SERIAL_NUMBER + VALID_SERIAL_NUMBER_APPLE;
    public static final String SERIAL_NUMBER_DESC_BANANA = " " + PREFIX_SERIAL_NUMBER + VALID_SERIAL_NUMBER_BANANA;
    public static final String SOURCE_DESC_APPLE = " " + PREFIX_SOURCE + VALID_SOURCE_APPLE;
    public static final String SOURCE_DESC_BANANA = " " + PREFIX_SOURCE + VALID_SOURCE_BANANA;
    public static final String QUANTITY_DESC_APPLE = " " + PREFIX_QUANTITY + VALID_QUANTITY_APPLE;
    public static final String QUANTITY_DESC_BANANA = " " + PREFIX_QUANTITY + VALID_QUANTITY_BANANA;
    public static final String LOCATION_DESC_APPLE = " " + PREFIX_LOCATION + VALID_LOCATION_APPLE;
    public static final String LOCATION_DESC_BANANA = " " + PREFIX_LOCATION + VALID_LOCATION_BANANA;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "donje#y"; // '#' not allowed in names
    public static final String INVALID_SERIAL_NUMBER_DESC = " "
            + PREFIX_SERIAL_NUMBER; // empty serial number not allowed
    public static final String INVALID_SOURCE_DESC = " " + PREFIX_SOURCE; // missing source
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "-100"; // negative quantity not allowed
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final String DEFAULT_SERIAL_NUMBER = "0";

}
