package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.tagcommands.TagSortCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagNameComparator;

class TagSortCommandTest {
    private Model model = new ModelManager(getTypicalProjact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProjact(), new UserPrefs());

    @Test
    public void equals() {
        TagSortCommand commandOne = new TagSortCommand();
        TagSortCommand commandTwo = new TagSortCommand();

        // same object -> returns true
        assertTrue(commandOne.equals(commandOne));

        // different types -> returns false
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different tagsort commands -> returns false
        assertFalse(commandOne.equals(commandTwo));
    }


    @Test
    public void execute_sortTagCommand_success() {
        String expectedMessage = MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(
                expectedMessage, false, false, true, false, true, false);
        TagNameComparator comparator = new TagNameComparator();
        TagSortCommand command = new TagSortCommand();
        expectedModel.updateSortedTagList(comparator);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(expectedModel.getSortedTagList(), model.getSortedTagList());
    }
}
