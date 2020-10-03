package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.history.CommandHistory;


public class HistoryCommandTest {

    private Model model;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("help");
    }

    @Test
    void execute() {
        HistoryCommand historyCommand = new HistoryCommand();
        CommandHistory.addUsedCommand("history");
        assertCommandSuccess(historyCommand, model, String.format(CommandHistory.STRING, "\n -\thelp"), model);
    }
}
