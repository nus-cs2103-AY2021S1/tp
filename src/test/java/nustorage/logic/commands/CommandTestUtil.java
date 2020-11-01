package nustorage.logic.commands;

import static nustorage.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final double VALID_AMOUNT = 0.10;
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";

    /*
     * Adapted for NUStorage:
     */
    public static final String ITEM_NAME_1 = "ITEM_NAME_1";
    public static final String ITEM_NAME_2 = "ITEM_NAME_2";
    public static final String ITEM_NAME_3 = "ITEM_NAME_3";

    public static final double COST_1 = 0;
    public static final double COST_2 = 13.7;
    public static final double COST_3 = 27.5;

    public static final int QUANTITY_1 = 10;
    public static final int QUANTITY_2 = 0;
    public static final int QUANTITY_3 = 17;

    public static final int ID_A = 12345789;
    public static final int ID_B = 92502649;
    public static final int ID_C = 57396892;
    public static final int ID_D = 29730103;

    public static final double AMOUNT_A = 0.10;
    public static final double AMOUNT_B = 17.17;
    public static final double AMOUNT_C = 128.99;
    public static final double AMOUNT_D = 9999999;

    public static final boolean HAS_INVENTORY_A = false;
    public static final boolean HAS_INVENTORY_B = false;
    public static final boolean HAS_INVENTORY_C = false;
    public static final boolean HAS_INVENTORY_D = false;

    public static final String DATE_STRING_A = "2020-10-01";
    public static final String DATE_STRING_B = "2019-01-01";
    public static final String DATE_STRING_C = "2018-12-30";
    public static final String DATE_STRING_D = "2005-08-23";
    public static final String TIME_STRING_A = "23:59";
    public static final String TIME_STRING_B = "00:01";
    public static final String TIME_STRING_C = "18:00";
    public static final String TIME_STRING_D = "08:35";
    public static final LocalDate DATE_A = LocalDate.parse(DATE_STRING_A);
    public static final LocalDate DATE_B = LocalDate.parse(DATE_STRING_B);
    public static final LocalDate DATE_C = LocalDate.parse(DATE_STRING_C);
    public static final LocalDate DATE_D = LocalDate.parse(DATE_STRING_D);
    public static final LocalTime TIME_A = LocalTime.parse(TIME_STRING_A);
    public static final LocalTime TIME_B = LocalTime.parse(TIME_STRING_B);
    public static final LocalTime TIME_C = LocalTime.parse(TIME_STRING_C);
    public static final LocalTime TIME_D = LocalTime.parse(TIME_STRING_D);

    public static final LocalDateTime DATE_TIME_A = LocalDateTime.of(DATE_A, TIME_A);
    public static final LocalDateTime DATE_TIME_B = LocalDateTime.of(DATE_B, TIME_B);
    public static final LocalDateTime DATE_TIME_C = LocalDateTime.of(DATE_C, TIME_C);
    public static final LocalDateTime DATE_TIME_D = LocalDateTime.of(DATE_D, TIME_D);

    public static final String AMOUNT_DESC_A = " " + PREFIX_AMOUNT + AMOUNT_A;
    public static final String AMOUNT_DESC_B = " " + PREFIX_AMOUNT + AMOUNT_B;
    public static final String AMOUNT_DESC_C = " " + PREFIX_AMOUNT + AMOUNT_C;
    public static final String AMOUNT_DESC_D = " " + PREFIX_AMOUNT + AMOUNT_D;
    public static final String DATE_DESC_A = " " + PREFIX_DATETIME + DATE_STRING_A;
    public static final String DATE_DESC_B = " " + PREFIX_DATETIME + DATE_STRING_B;
    public static final String DATE_DESC_C = " " + PREFIX_DATETIME + DATE_STRING_C;
    public static final String DATE_DESC_D = " " + PREFIX_DATETIME + DATE_STRING_D;
    public static final String TIME_DESC_A = " " + PREFIX_DATETIME + TIME_STRING_A;
    public static final String TIME_DESC_B = " " + PREFIX_DATETIME + TIME_STRING_B;
    public static final String TIME_DESC_C = " " + PREFIX_DATETIME + TIME_STRING_C;
    public static final String TIME_DESC_D = " " + PREFIX_DATETIME + TIME_STRING_D;
    public static final String DATE_TIME_DESC_A = " " + PREFIX_DATETIME + DATE_STRING_A + " " + TIME_STRING_A;
    public static final String DATE_TIME_DESC_B = " " + PREFIX_DATETIME + DATE_STRING_B + " " + TIME_STRING_B;
    public static final String DATE_TIME_DESC_C = " " + PREFIX_DATETIME + DATE_STRING_C + " " + TIME_STRING_C;
    public static final String DATE_TIME_DESC_D = " " + PREFIX_DATETIME + DATE_STRING_D + " " + TIME_STRING_D;

    public static final String INVALID_AMOUNT_DESC_A = " " + PREFIX_AMOUNT + "0.10a"; // 'a' is not allowed

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }


    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }


    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
    }


}
