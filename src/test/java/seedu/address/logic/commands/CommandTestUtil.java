package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENTSOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_NAME;
import static seedu.address.testutil.TypicalPolicies.SAVINGS_DESCRIPTION;
import static seedu.address.testutil.TypicalPolicies.SAVINGS_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ClientList;
import seedu.address.model.Model;
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
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_CLIENTSOURCE_HUSBAND = "husband of Amy";
    public static final String VALID_CLIENTSOURCE_FRIEND = "friend from NUS";
    public static final String VALID_NOTE_DOG = "lovesdogs";
    public static final String VALID_NOTE_CAT = "lovescats";
    public static final String VALID_PRIORITY_HIGH = "h";
    public static final String VALID_PRIORITY_LOW = "l";
    public static final String VALID_PRIORITY_UNDEFINED = "u";
    public static final String VALID_POLICY_NAME_AMY = LIFE_TIME_NAME;
    public static final String VALID_POLICY_NAME_BOB = SAVINGS_NAME;
    public static final String VALID_POLICY_DESCRIPTION_AMY = LIFE_TIME_DESCRIPTION;
    public static final String VALID_POLICY_DESCRIPTION_BOB = SAVINGS_DESCRIPTION;

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String CLIENTSOURCE_DESC_FRIEND = " " + PREFIX_CLIENTSOURCE + VALID_CLIENTSOURCE_FRIEND;
    public static final String CLIENTSOURCE_DESC_HUSBAND = " " + PREFIX_CLIENTSOURCE + VALID_CLIENTSOURCE_HUSBAND;
    public static final String NOTE_DESC_DOG = " " + PREFIX_NOTE + VALID_NOTE_DOG;
    public static final String NOTE_DESC_CAT = " " + PREFIX_NOTE + VALID_NOTE_CAT;
    public static final String PRIORITY_DESC_AMY = " " + PREFIX_PRIORITY + VALID_PRIORITY_HIGH;
    public static final String PRIORITY_DESC_BOB = " " + PREFIX_PRIORITY + VALID_PRIORITY_LOW;
    public static final String POLICY_NAME_DESC_AMY = " " + PREFIX_POLICY_NAME + VALID_POLICY_NAME_AMY;
    public static final String POLICY_NAME_DESC_BOB = " " + PREFIX_POLICY_NAME + VALID_POLICY_NAME_BOB;
    public static final String POLICY_DESCRIPTION_DESC_AMY =
            " " + PREFIX_POLICY_DESCRIPTION + VALID_POLICY_DESCRIPTION_AMY;
    public static final String POLICY_DESCRIPTION_DESC_BOB =
            " " + PREFIX_POLICY_DESCRIPTION + VALID_POLICY_DESCRIPTION_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_CLIENTSOURCE_DESC =
            " "
                    + PREFIX_CLIENTSOURCE
                    + "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"; // clientsource limited to 50 characters.
    public static final String INVALID_NOTE_DESC = " " + PREFIX_NOTE; // empty string not allowed for notes.
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "xdz";
    public static final String INVALID_POLICY_NAME_DESC = " " + PREFIX_POLICY_NAME + "@!";
    public static final String INVALID_POLICY_DESCRIPTION_DESC = " " + PREFIX_POLICY_DESCRIPTION + " ";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withClientSources(VALID_CLIENTSOURCE_FRIEND).withNote(VALID_NOTE_CAT)
                .withPriority(VALID_PRIORITY_HIGH).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND, VALID_CLIENTSOURCE_FRIEND)
                .withNote(VALID_NOTE_DOG)
                .withPriority(VALID_PRIORITY_LOW).build();
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
     * - the client list, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ClientList expectedClientList = new ClientList(actualModel.getClientList());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedClientList, actualModel.getClientList());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s client list.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
