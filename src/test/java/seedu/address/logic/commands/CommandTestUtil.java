package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCKROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_GROUP;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.DINNER;
import static seedu.address.testutil.TypicalEvents.LUNCH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditEventDescriptorBuilder;
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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_STUDENT_GROUP_DANCE = "dance";
    public static final String VALID_STUDENT_GROUP_BASKETBALL = "basketball";
    public static final String VALID_BLOCK_AMY = "A";
    public static final String VALID_BLOCK_BOB = "B";
    public static final String VALID_ROOM_AMY = "420";
    public static final String VALID_ROOM_BOB = "301";
    public static final String VALID_FLOOR_AMY = "4";
    public static final String VALID_FLOOR_BOB = "3";
    public static final String VALID_ROOM_NUMBER_AMY = "20";
    public static final String VALID_ROOM_NUMBER_BOB = "01";
    public static final String VALID_MATRICULATION_NUMBER_AMY = "A0123456K";
    public static final String VALID_MATRICULATION_NUMBER_BOB = "A0123456L";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String STUDENT_GROUP_DESC_BASKETBALL = " " + PREFIX_STUDENT_GROUP
            + VALID_STUDENT_GROUP_BASKETBALL;
    public static final String STUDENT_GROUP_DESC_DANCE = " " + PREFIX_STUDENT_GROUP + VALID_STUDENT_GROUP_DANCE;
    public static final String BLOCKROOM_DESC_AMY = " " + PREFIX_BLOCKROOM + VALID_BLOCK_AMY + VALID_ROOM_AMY;
    public static final String BLOCKROOM_DESC_BOB = " " + PREFIX_BLOCKROOM + VALID_BLOCK_BOB + VALID_ROOM_BOB;
    public static final String MATRICULATION_NUMBER_DESC_AMY = " " + PREFIX_MATRICULATION_NUMBER
            + VALID_MATRICULATION_NUMBER_AMY;
    public static final String MATRICULATION_NUMBER_DESC_BOB = " " + PREFIX_MATRICULATION_NUMBER
            + VALID_MATRICULATION_NUMBER_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GENDER = " " + PREFIX_GENDER + "A"; // '*' not allowed in gender
    // '*' not allowed in student group
    public static final String INVALID_STUDENT_GROUP_DESC = " " + PREFIX_STUDENT_GROUP + "soccer*";
    public static final String INVALID_MATRICULATION_NUMBER_DESC = " " + PREFIX_MATRICULATION_NUMBER
            + "C0123456B"; // should start with 'A'
    public static final String INVALID_BLOCK = " " + PREFIX_BLOCK + "A2"; // number not allowed in block

    public static final String VALID_NAME_LUNCH = "Hall Lunch at Eusoff";
    public static final String VALID_DESC_LUNCH = "Some description text for lunch";
    public static final String VALID_DATE_LUNCH = "01/02/2013 01:56";
    public static final String VALID_LOCATION_LUNCH = "Dining Hall at Eusoff or UTown";

    public static final String EVENT_NAME_DESC_LUNCH = " " + PREFIX_EVENT_NAME + VALID_NAME_LUNCH;
    public static final String EVENT_DESC_DESC_LUNCH = " " + PREFIX_EVENT_DESC + VALID_DESC_LUNCH;
    public static final String EVENT_DATE_DESC_LUNCH = " " + PREFIX_EVENT_DATE + VALID_DATE_LUNCH;
    public static final String EVENT_LOCATION_DESC_LUNCH = " " + PREFIX_EVENT_LOCATION + VALID_LOCATION_LUNCH;

    public static final String INVALID_EVENT_NAME = " " + PREFIX_EVENT_NAME; // empty string not allowed
    public static final String INVALID_EVENT_DESC = " " + PREFIX_EVENT_DESC; // empty string not allowed
    public static final String INVALID_EVENT_LOCATION = " " + PREFIX_EVENT_LOCATION; // empty string not allowed
    public static final String INVALID_EVENT_DATE_FORMAT = " " + PREFIX_EVENT_DATE + "2020/01/01 15:00";
    public static final String INVALID_EVENT_DATE_RANGE = " " + PREFIX_EVENT_DATE + "32/01/2020 15:00";
    public static final String INVALID_EVENT_DATE = " " + PREFIX_EVENT_DATE;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditEventCommand.EditEventDescriptor DESC_LUNCH;
    public static final EditEventCommand.EditEventDescriptor DESC_DINNER;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withMatriculationNumber(VALID_MATRICULATION_NUMBER_AMY)
                .withStudentGroups(VALID_STUDENT_GROUP_BASKETBALL).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withMatriculationNumber(VALID_MATRICULATION_NUMBER_BOB)
                .withStudentGroups(VALID_STUDENT_GROUP_DANCE, VALID_STUDENT_GROUP_BASKETBALL).build();

        DESC_LUNCH = new EditEventDescriptorBuilder(LUNCH).withDescription("I'm the changed description!").build();
        DESC_DINNER = new EditEventDescriptorBuilder(DINNER).withDate("01/01/1999 15:00").build();
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
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
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
