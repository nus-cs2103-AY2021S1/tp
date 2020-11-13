package seedu.bagel.logic.commands;

import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.logic.commands.CommandTestUtil.showFlashcardAtIndex;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;
import static seedu.bagel.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBagel(), new UserPrefs());
        expectedModel = new ModelManager(model.getBagel(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showFlashcardAtIndex(model, INDEX_FIRST_FLASHCARD);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
