package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("help");
    }

    @Test
    void execute() {
        HistoryCommand historyCommand = new HistoryCommand();
        CommandHistory.addUsedCommand("history");
        assertCommandSuccess(historyCommand, model, String.format(CommandHistory.str, "\n -\thelp"), model);
    }
}
