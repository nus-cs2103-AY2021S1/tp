package seedu.homerce.logic.commands;

import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.manager.HistoryManager;

public class UndoCommandTest {
    @Test
    public void execute_latestChange_throwsCommandException() {
        HistoryManager historyTest = HistoryManager.getInstance();
        Model modelTest = new ModelManager();

        UndoCommand undoTest = new UndoCommand();
        assertThrows(CommandException.class, () -> undoTest.execute(modelTest, historyTest));
    }
}
