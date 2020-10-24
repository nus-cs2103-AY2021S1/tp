package seedu.flashcard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_DIAGRAM;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.flashcard.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Statistics;
import seedu.flashcard.testutil.EditFlashcardDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_QUESTION_1 = "What does SDLC stand for?";
    public static final String VALID_QUESTION_2 = "What is a revision control software?";
    public static final String VALID_QUESTION_3 = "It is recommended that assertions to be used liberally in the "
            + "code. True or False?";
    public static final String VALID_QUESTION_4 = "Multiple models of the same entity may be needed to capture it "
            + "fully. True or False?";
    public static final String VALID_QUESTION_5 = "The middle part of the user story (i.e., the function) can be "
            + "omitted if it is obvious. True or False?";

    public static final String VALID_ANSWER_1 = "Software development life cycle";
    public static final String VALID_ANSWER_2 = "It is the software tool that automate the process of Revision Control";
    public static final String VALID_ANSWER_3 = "True";
    public static final String VALID_ANSWER_4 = "True";
    public static final String VALID_ANSWER_5 = "False";
    public static final String VALID_CATEGORY_1 = "SDLC";
    public static final String VALID_CATEGORY_2 = "Revision history";
    public static final String VALID_CATEGORY_4 = "Models";
    public static final String VALID_CATEGORY_5 = "User Stories";
    public static final String VALID_NOTE_1 = "";
    public static final String VALID_NOTE_2 = "Note";
    public static final String VALID_RATING_1 = "";
    public static final String VALID_RATING_2 = "2";
    public static final String VALID_TAG_1 = "test";
    public static final String VALID_TAG_2 = "revise";
    public static final String VALID_TAG_3 = "important";
    public static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "ImageTest");
    public static final Statistics VALID_STATISTICS_1 = new Statistics(3, 1);

    public static final Path VALID_FILE_TYPE = TEST_DATA_FOLDER.resolve("valid_image.jpg");
    public static final String VALID_DIAGRAM_1 = "";
    public static final String VALID_DIAGRAM_2 = VALID_FILE_TYPE.toString();
    public static final String INVALID_QUESTION_1 = " ";
    public static final String INVALID_ANSWER_1 = " ";
    public static final String INVALID_CATEGORY_1 = "%";
    public static final String INVALID_RATING_1 = "6";
    public static final String INVALID_DIAGRAM_1 = "]";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final String VALID_RATING_2_DESC = " " + PREFIX_RATING + VALID_RATING_2;
    public static final String VALID_NOTE_2_DESC = " " + PREFIX_NOTE + VALID_NOTE_2;
    public static final String VALID_TAG_2_DESC = " " + PREFIX_TAG + VALID_TAG_2;
    public static final String VALID_TAG_3_DESC = " " + PREFIX_TAG + VALID_TAG_3;
    public static final String VALID_QUESTION_4_DESC = " " + PREFIX_QUESTION + VALID_QUESTION_4;
    public static final String VALID_ANSWER_4_DESC = " " + PREFIX_ANSWER + VALID_ANSWER_4;
    public static final String VALID_CATEGORY_4_DESC = " " + PREFIX_CATEGORY + VALID_CATEGORY_4;
    public static final String VALID_QUESTION_5_DESC = " " + PREFIX_QUESTION + VALID_QUESTION_5;
    public static final String VALID_ANSWER_5_DESC = " " + PREFIX_ANSWER + VALID_ANSWER_5;
    public static final String VALID_CATEGORY_5_DESC = " " + PREFIX_CATEGORY + VALID_CATEGORY_5;
    public static final String VALID_DIAGRAM_2_DESC = " " + PREFIX_DIAGRAM + VALID_DIAGRAM_2;

    public static final String INVALID_QUESTION_DESC = " " + PREFIX_QUESTION + INVALID_QUESTION_1;
    public static final String INVALID_ANSWER_DESC = " " + PREFIX_ANSWER + INVALID_ANSWER_1;
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + INVALID_CATEGORY_1;
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING + INVALID_RATING_1;

    public static final EditCommand.EditFlashcardDescriptor DESC_FLASHCARD_1;
    public static final EditCommand.EditFlashcardDescriptor DESC_FLASHCARD_2;

    static {
        DESC_FLASHCARD_1 = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_1)
                .withAnswer(VALID_ANSWER_1).withCategory(VALID_CATEGORY_1).build();
        DESC_FLASHCARD_2 = new EditFlashcardDescriptorBuilder().withQuestion(VALID_QUESTION_2)
                .withAnswer(VALID_ANSWER_2).withCategory(VALID_CATEGORY_2).build();
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
     * - the flashcard deck, filtered flashcard list and selected flashcard in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FlashcardDeck expectedFlashcardDeck = new FlashcardDeck(actualModel.getFlashcardDeck());
        List<Flashcard> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFlashcardList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFlashcardDeck, actualModel.getFlashcardDeck());
        assertEquals(expectedFilteredList, actualModel.getFilteredFlashcardList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the flashcard at the given {@code targetIndex} in the
     * {@code model}'s flashcard deck.
     */
    public static void showFlashcardAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFlashcardList().size());

        Flashcard filteredFlashcard = model.getFilteredFlashcardList().get(targetIndex.getZeroBased());
        model.updateFilteredFlashcardList(flashcard -> flashcard.isSameQuestion(filteredFlashcard));

        assertEquals(1, model.getFilteredFlashcardList().size());
    }

}
