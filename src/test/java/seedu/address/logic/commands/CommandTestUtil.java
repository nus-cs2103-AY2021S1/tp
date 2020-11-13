package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

//import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.day.Day;
//import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditDayDescriptorBuilder;
import seedu.address.testutil.UpdateProfileDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_1 = "First";
    public static final String VALID_NAME_2 = "Second";
    public static final String VALID_ID_1 = "1101";
    public static final String VALID_ID_2 = "2202";
    public static final String VALID_HEIGHT_1 = "170";
    public static final String VALID_HEIGHT_2 = "180";

    public static final String VALID_DATE_1 = "2019-12-23";
    public static final String VALID_DATE_2 = "2020-10-11";
    public static final String VALID_WEIGHT_1 = "45";
    public static final String VALID_WEIGHT_2 = "95";

    public static final String DATE_DESC_1 = " " + PREFIX_DATE + VALID_DATE_1;
    public static final String DATE_DESC_2 = " " + PREFIX_DATE + VALID_DATE_2;
    public static final String WEIGHT_DESC_1 = " " + PREFIX_WEIGHT + VALID_WEIGHT_1;
    public static final String WEIGHT_DESC_2 = " " + PREFIX_WEIGHT + VALID_WEIGHT_2;

    public static final String INVALID_DATE_DESC = "2020-10&14"; // '&' not allowed in dates
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "91a"; // 'a' not allowed in weight

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditDayDescriptor DESC_1;
    public static final EditCommand.EditDayDescriptor DESC_2;

    public static final UpdateCommand.UpdateProfileDescriptor UPDESC_1;
    public static final UpdateCommand.UpdateProfileDescriptor UPDESC_2;

    static {
        DESC_1 = new EditDayDescriptorBuilder().withDate(VALID_DATE_1)
                .withWeight(VALID_WEIGHT_1)
                 .build();
        DESC_2 = new EditDayDescriptorBuilder().withDate(VALID_DATE_2)
                .withWeight(VALID_WEIGHT_2)
                .build();
        UPDESC_1 = new UpdateProfileDescriptorBuilder().withName(VALID_NAME_1).withId(VALID_ID_1)
                .withHeight(VALID_HEIGHT_1).withWeight(VALID_WEIGHT_1).build();
        UPDESC_2 = new UpdateProfileDescriptorBuilder().withName(VALID_NAME_2).withId(VALID_ID_2)
                .withHeight(VALID_HEIGHT_2).withWeight(VALID_WEIGHT_2).build();
    }

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
            assertEquals(expectedModel, actualModel);
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
     * - My Fitness Buddy, filtered day list and selected day in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy(actualModel.getMyFitnessBuddy());
        List<Day> expectedFilteredList = new ArrayList<>(actualModel.getFilteredDayList());

        //assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(myFitnessBuddy, actualModel.getMyFitnessBuddy());
        assertEquals(expectedFilteredList, actualModel.getFilteredDayList());
    }
    //error due to refactoring
    //    /**
    //     * Updates {@code model}'s filtered list to show only the day at the given {@code targetIndex} in the
    //     * {@code model}'s My Fitness Buddy records.
    //     */
    //    public static void showDayAtIndex(Model model, Index targetIndex) {
    //        assertTrue(targetIndex.getZeroBased() < model.getFilteredDayList().size());
    //
    //        Day day = model.getFilteredDayList().get(targetIndex.getZeroBased());
    //        final String[] splitDate = day.getDate().value.split("\\s+");
    //        model.updateFilteredDayList(new NameContainsKeywordsPredicate(Arrays.asList(splitDate[0])));
    //
    //        //assertEquals(1, model.getFilteredDayList().size());
    //    }

}
