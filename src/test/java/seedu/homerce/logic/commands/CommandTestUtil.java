package seedu.homerce.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.client.EditClientCommand;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.logic.commands.service.EditServiceCommand;
import seedu.homerce.model.Model;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.client.predicate.ClientNamePredicate;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.service.predicate.ServiceTitlePredicate;
import seedu.homerce.testutil.client.EditClientDescriptorBuilder;
import seedu.homerce.testutil.service.EditServiceDescriptorBuilder;

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

    public static final String VALID_TITLE_LASH_LIFT = "Lash Lift";
    public static final Double VALID_DURATION_LASH_LIFT = 0.5;
    public static final Double VALID_AMOUNT_LASH_LIFT = 17.9;
    public static final String VALID_SERVICE_CODE_LASH_LIFT = "SC000";

    public static final String VALID_TITLE_MANICURE = "Manicure";
    public static final Double VALID_DURATION_MANICURE = 1.5;
    public static final Double VALID_AMOUNT_MANICURE = 19.5;
    public static final String VALID_SERVICE_CODE_MANICURE = "SC001";

    public static final EditServiceCommand.EditServiceDescriptor DESC_MANICURE;
    public static final EditServiceCommand.EditServiceDescriptor DESC_LASH_LIFT;

    public static final EditClientCommand.EditClientDescriptor DESC_AMY;
    public static final EditClientCommand.EditClientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_MANICURE = new EditServiceDescriptorBuilder().withTitle(VALID_TITLE_MANICURE)
            .withDuration(VALID_DURATION_MANICURE)
            .withAmount(VALID_AMOUNT_MANICURE)
            .withServiceCode(VALID_SERVICE_CODE_MANICURE).build();
        DESC_LASH_LIFT = new EditServiceDescriptorBuilder().withTitle(VALID_TITLE_LASH_LIFT)
            .withDuration(VALID_DURATION_LASH_LIFT)
            .withAmount(VALID_AMOUNT_LASH_LIFT)
            .withServiceCode(VALID_SERVICE_CODE_LASH_LIFT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, HistoryManager.getInstance());
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
     * - the homerce, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ClientManager expectedClientManager = new ClientManager(actualModel.getClientManager());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel,
            HistoryManager.getInstance()));
        assertEquals(expectedClientManager, actualModel.getClientManager());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s homerce.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new ClientNamePredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the service at the given {@code targetIndex} in the
     * {@code model}'s homerce.
     */
    public static void showServiceAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredServiceList().size());

        Service service = model.getFilteredServiceList().get(targetIndex.getZeroBased());
        model.updateFilteredServiceList(new ServiceTitlePredicate(service.getTitle()));

        assertEquals(1, model.getFilteredServiceList().size());
    }

}
