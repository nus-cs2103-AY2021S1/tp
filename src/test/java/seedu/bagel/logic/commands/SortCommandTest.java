package seedu.bagel.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.bagel.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bagel.testutil.TypicalFlashcards.getTypicalBagel;

import org.junit.jupiter.api.Test;

import seedu.bagel.logic.commands.sort.SortByAscTitle;
import seedu.bagel.logic.commands.sort.SortByDescTitle;
import seedu.bagel.logic.commands.sort.SortByTag;
import seedu.bagel.logic.commands.sort.SortCommand;
import seedu.bagel.logic.commands.sort.SortCommand.SortRequirement;
import seedu.bagel.model.Model;
import seedu.bagel.model.ModelManager;
import seedu.bagel.model.UserPrefs;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalBagel(), new UserPrefs());
    private SortRequirement sortByAscendingTitle = new SortRequirement("atitle");
    private SortRequirement sortByDescendingTitle = new SortRequirement("dtitle");
    private SortRequirement sortByTag = new SortRequirement("tag");
    private SortRequirement invalidSort = new SortRequirement("invalid");

    // test sort with full list of flashcards
    @Test
    public void execute_sortUnfilteredListByAscendingTitle_success() {
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());

        SortCommand sortCommand = new SortCommand(sortByAscendingTitle);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS
                + SortCommand.MESSAGE_SUCCESS_ASCENDING_TITLE;

        expectedModel.sortFlashcardList(new SortByAscTitle());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortUnfilteredListByDescendingTitle_success() {
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());

        SortCommand sortCommand = new SortCommand(sortByDescendingTitle);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS
                + SortCommand.MESSAGE_SUCCESS_DESCENDING_TITLE;

        expectedModel.sortFlashcardList(new SortByDescTitle());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortUnfilteredListByTag_success() {
        ModelManager expectedModel = new ModelManager(model.getBagel(), new UserPrefs());

        SortCommand sortCommand = new SortCommand(sortByTag);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS
                + SortCommand.MESSAGE_SUCCESS_TAG;

        expectedModel.sortFlashcardList(new SortByTag());

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidRequirementUnfilteredList_throwsCommandException() {
        SortCommand sortCommand = new SortCommand(invalidSort);
        assertCommandFailure(sortCommand, model, SortCommand.MESSAGE_INVALID_REQUIREMENT);
    }

    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand(sortByAscendingTitle);
        SortCommand sortSecondCommand = new SortCommand(sortByDescendingTitle);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different flashcard -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

}
