package seedu.bagel.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_REQ;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.bagel.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bagel.commons.core.index.Index;
import seedu.bagel.logic.commands.exceptions.CommandException;
import seedu.bagel.model.Bagel;
import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.flashcard.TitleContainsKeywordsPredicate;
import seedu.bagel.testutil.EditFlashcardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_AMY = "Amy Bee";
    public static final String VALID_TITLE_BOB = "Bob Choo";
    public static final String VALID_DESC_AMY = "11111111";
    public static final String VALID_DESC_BOB = "22222222";
    public static final String VALID_SET_AMY = "2";
    public static final String VALID_SET_BOB = "1";
    public static final String VALID_LINK_BOB = "https://www.google.com/";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_REQUIREMENT_ASCENDING_TITLE = "atitle";

    public static final String TITLE_DESC_AMY = " " + PREFIX_TITLE + VALID_TITLE_AMY;
    public static final String TITLE_DESC_BOB = " " + PREFIX_TITLE + VALID_TITLE_BOB;
    public static final String DESC_DESC_AMY = " " + PREFIX_DESC + VALID_DESC_AMY;
    public static final String DESC_DESC_BOB = " " + PREFIX_DESC + VALID_DESC_BOB;
    public static final String LINK_DESC_BOB = " " + PREFIX_LINK + VALID_LINK_BOB;
    public static final String REQUIREMENT = " " + PREFIX_REQ + VALID_REQUIREMENT_ASCENDING_TITLE;

    public static final String SET_DESC_AMY = " " + PREFIX_SET + VALID_SET_AMY;
    public static final String SET_DESC_BOB = " " + PREFIX_SET + VALID_SET_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "  ";
    public static final String INVALID_DESC_DESC = " " + PREFIX_DESC + "   ";
    public static final String INVALID_SET_DESC = " " + PREFIX_SET + "abc*";
    public static final String INVALID_LINK_DESC = " " + PREFIX_LINK + "h";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFlashcardDescriptor DESC_AMY;
    public static final EditCommand.EditFlashcardDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditFlashcardDescriptorBuilder().withTitle(VALID_TITLE_AMY)
                .withDescription(VALID_DESC_AMY).withSet(VALID_SET_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditFlashcardDescriptorBuilder().withTitle(VALID_TITLE_BOB)
                .withDescription(VALID_DESC_BOB).withSet(VALID_SET_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .build();
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
     * - the bagel, filtered flashcard list and selected flashcard in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Bagel expectedBagel = new Bagel(actualModel.getBagel());
        List<Flashcard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashcardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedBagel, actualModel.getBagel());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashcardList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s bagel.
     */
    public static void showFlashcardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashcardList().size());

        Flashcard flashcard = model.getFilteredFlashcardList().get(targetIndex.getZeroBased());
        final String[] splitName = flashcard.getTitle().fullTitle.split("\\s+");
        model.updateFilteredFlashcardList(new TitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFlashcardList().size());
    }

}
