package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalAssignments.getTypicalProductiveNus;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class PrioritizeCommandTest {
    private Model model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
}
