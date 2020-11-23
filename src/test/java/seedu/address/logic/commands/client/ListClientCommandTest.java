package seedu.address.logic.commands.client;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientWithId;
import static seedu.address.testutil.TypicalClients.getTypicalHairStyleX;
import static seedu.address.testutil.TypicalIds.ID_FIRST_CLIENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListClientCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHairStyleX(), new UserPrefs());
        expectedModel = new ModelManager(model.getHairStyleX(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListClientCommand(), model,
                ListClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientWithId(model, ID_FIRST_CLIENT);
        assertCommandSuccess(new ListClientCommand(), model,
                ListClientCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
