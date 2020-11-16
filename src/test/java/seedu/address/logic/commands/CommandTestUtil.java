package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Tasker;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TELEGRAM_AMY = "amy123";
    public static final String VALID_TELEGRAM_BOB = "_bob_";
    public static final String VALID_MATRIC_NUMBER_AMY = "A0123456B";
    public static final String VALID_MATRIC_NUMBER_BOB = "A9876543C";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_ATTENDANCE_AMY = "27/03/1998";
    public static final String VALID_ATTENDANCE_BOB = "27/05/2020";
    public static final String VALID_DAY_AMY = "20/03/2019";
    public static final String VALID_DAY_BOB = "27/05/2020";
    public static final String VALID_TIME_AMY = "12:00";
    public static final String VALID_TIME_BOB = "11:00";
    public static final String VALID_LOCATION_AMY = "NUS Utown";
    public static final String VALID_LOCATION_BOB = "SOC Basement";
    public static final String VALID_TYPE_AMY = "personal";
    public static final String VALID_TYPE_BOB = "group";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String MATRIC_NUMBER_DESC_AMY = " " + PREFIX_MATRIC_NUMBER + VALID_MATRIC_NUMBER_AMY;
    public static final String MATRIC_NUMBER_DESC_BOB = " " + PREFIX_MATRIC_NUMBER + VALID_MATRIC_NUMBER_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_DATE + VALID_ATTENDANCE_AMY;
    public static final String ATTENDANCE_DESC_BOB = " " + PREFIX_DATE + VALID_ATTENDANCE_BOB;
    public static final String DAY_DESC_AMY = " " + PREFIX_DATE + VALID_DAY_AMY;
    public static final String DAY_DESC_BOB = " " + PREFIX_DATE + VALID_DAY_BOB;
    public static final String TIME_DESC_AMY = " " + PREFIX_TIME + VALID_TIME_AMY;
    public static final String TIME_DESC_BOB = " " + PREFIX_TIME + VALID_TIME_BOB;
    public static final String LOCATION_DESC_AMY = " " + PREFIX_ADDRESS + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_ADDRESS + VALID_LOCATION_BOB;
    public static final String TYPE_DESC_AMY = " " + PREFIX_TYPE + VALID_TYPE_AMY;
    public static final String TYPE_DESC_BOB = " " + PREFIX_TYPE + VALID_TYPE_BOB;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&";
    // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a";
    // missing '@' symbol
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo";
    // '.' not allowed in telegram usernames
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "alex.yeoh";
    // missing digits in matric number
    public static final String INVALID_MATRIC_NUMBER_DESC = " " + PREFIX_MATRIC_NUMBER + "A111B";
    // empty string not allowed for addresses
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS;
    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*";
    // invalid attendance date
    public static final String INVALID_ATTENDANCE_DESC = " " + PREFIX_DATE + "mar 27";
    // invalid time
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "25:00";
    // invalid type
    public static final String INVALID_TYPE_DESC = " " + PREFIX_TYPE + "one-to-one";
    // invalid consult day
    public static final String INVALID_DAY_DESC = " " + PREFIX_DATE + "apr 27";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
                .withMatricNumber(VALID_MATRIC_NUMBER_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withMatricNumber(VALID_MATRIC_NUMBER_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Tasker expectedAddressBook = new Tasker(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
