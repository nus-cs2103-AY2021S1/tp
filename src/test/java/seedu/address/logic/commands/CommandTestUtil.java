package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.TagPersonDescriptorBuilder;

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
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final String VALID_PARTICIPANT_AMY = VALID_NAME_AMY;
    public static final String VALID_PARTICIPANT_BOB = VALID_NAME_BOB;

    public static final String PARTICIPANT_DESC_AMY =
            " " + PREFIX_PARTICIPANT + VALID_PARTICIPANT_AMY;
    public static final String PARTICIPANT_DESC_BOB =
            " " + PREFIX_PARTICIPANT + VALID_PARTICIPANT_BOB;

    public static final String VALID_MODULE_NAME_CS1000 = "CS1000";
    public static final String VALID_MODULE_NAME_CS1001 = "CS1001";

    public static final String MODULE_DESC_CS1000 = " " + PREFIX_MODULE + VALID_MODULE_NAME_CS1000;
    public static final String MODULE_DESC_CS1001 = " " + PREFIX_MODULE + VALID_MODULE_NAME_CS1001;

    public static final String ADD_MODULE_DESC_CS1000 = " " + PREFIX_NAME + VALID_MODULE_NAME_CS1000;
    public static final String ADD_MODULE_DESC_CS1001 = " " + PREFIX_NAME + VALID_MODULE_NAME_CS1001;

    public static final String VALID_MEETING_NAME = "New Meeting Name";
    public static final String VALID_DATE = "2021-01-12";
    public static final String VALID_TIME = "18:00";

    public static final String VALID_MODULE_NAME_CM1111_MEETING = "CM1111";
    public static final String VALID_MODULE_NAME_CM1112_MEETING = "CM1112";
    public static final String VALID_MEETING_NAME_CM1111_MEETING = "CM1111 Meeting";
    public static final String VALID_MEETING_NAME_CM1112_MEETING = "CM1112 Meeting";
    public static final String VALID_DATE_CM1111_MEETING = "2021-10-10";
    public static final String VALID_DATE_CM1112_MEETING = "2022-04-07";
    public static final String VALID_TIME_CM1111_MEETING = "10:00";
    public static final String VALID_TIME_CM1112_MEETING = "11:00";

    public static final String MODULE_DESC_CM1111_MEETING = " " + PREFIX_MODULE + VALID_MODULE_NAME_CM1111_MEETING;
    public static final String MODULE_DESC_CM1112_MEETING = " " + PREFIX_MODULE + VALID_MODULE_NAME_CM1112_MEETING;
    public static final String MEETING_NAME_DESC_CM1111_MEETING = " " + PREFIX_NAME + VALID_MEETING_NAME_CM1111_MEETING;
    public static final String MEETING_NAME_DESC_CM1112_MEETING = " " + PREFIX_NAME + VALID_MEETING_NAME_CM1112_MEETING;
    public static final String DATE_DESC_CM1111_MEETING = " " + PREFIX_DATE + VALID_DATE_CM1111_MEETING;
    public static final String DATE_DESC_CM1112_MEETING = " " + PREFIX_DATE + VALID_DATE_CM1112_MEETING;
    public static final String TIME_DESC_CM1111_MEETING = " " + PREFIX_TIME + VALID_TIME_CM1111_MEETING;
    public static final String TIME_DESC_CM1112_MEETING = " " + PREFIX_TIME + VALID_TIME_CM1112_MEETING;

    public static final String INVALID_MODULE_NAME = " " + PREFIX_MODULE + "James&"; // '&' not allowed in module names
    public static final String INVALID_MEETING_NAME = " " + PREFIX_NAME + "James&"; // '&' not allowed in meeting names
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "2020/01/12"; // '/' not allowed in dates
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "1000"; // missing ':' symbol
    public static final String INVALID_PARTICIPANT_DESC = " " + PREFIX_PARTICIPANT + "a*"; // '*' not allowed in names

    public static final EditModuleCommand.EditModuleDescriptor DESC_CS1000;
    public static final EditModuleCommand.EditModuleDescriptor DESC_CS1001;

    public static final EditMeetingCommand.EditMeetingDescriptor DESC_CM1111;
    public static final EditMeetingCommand.EditMeetingDescriptor DESC_CM1112;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    static {
        DESC_CS1000 = new EditModuleDescriptorBuilder().withModuleName(VALID_MODULE_NAME_CS1000)
                .withMembers(VALID_PARTICIPANT_AMY).build();
        DESC_CS1001 = new EditModuleDescriptorBuilder().withModuleName(VALID_MODULE_NAME_CS1001)
                .withMembers(VALID_PARTICIPANT_BOB).build();
    }

    static {
        DESC_CM1111 = new EditMeetingDescriptorBuilder().withMeetingName(VALID_MEETING_NAME_CM1111_MEETING)
                .withDate(VALID_DATE_CM1111_MEETING).withTime(VALID_TIME_CM1111_MEETING)
                .withMembers(VALID_PARTICIPANT_AMY).build();
        DESC_CM1112 = new EditMeetingDescriptorBuilder().withMeetingName(VALID_MEETING_NAME_CM1112_MEETING)
                .withDate(VALID_DATE_CM1112_MEETING).withTime(VALID_TIME_CM1112_MEETING)
                .withMembers(VALID_PARTICIPANT_BOB).build();
    }

    public static final AddTagCommand.TagPersonDescriptor LABEL_DESC_AMY;
    public static final AddTagCommand.TagPersonDescriptor LABEL_DESC_BOB;

    static {
        LABEL_DESC_AMY = new TagPersonDescriptorBuilder()
                .withTags(VALID_TAG_FRIEND).build();
        LABEL_DESC_BOB = new TagPersonDescriptorBuilder()
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
