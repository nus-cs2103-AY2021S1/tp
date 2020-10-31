package seedu.cc.logic.commands.entrylevel;

import static seedu.cc.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.cc.testutil.TypicalEntries.getTypicalCommonCents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;

public class ListCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCommonCents(), new UserPrefs());
        expectedModel = new ModelManager(model.getCommonCents(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
