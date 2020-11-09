package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());
    }

    @Test
    public void execute_sortDescending_success() {
        Model expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
        expectedModel.sortFilteredFlashcardList("desc");
        assertCommandSuccess(new SortCommand("desc"), model, SortCommand.MESSAGE_SUCCESS
                        + "descending order.", expectedModel);
    }

    @Test
    public void execute_sortAscending_success() {
        Model expectedModel = new ModelManager(model.getFlashcardBook(), new UserPrefs());
        expectedModel.sortFilteredFlashcardList("asc");
        assertCommandSuccess(new SortCommand("asc"), model, SortCommand.MESSAGE_SUCCESS
                        + "ascending order.", expectedModel);
    }
}
