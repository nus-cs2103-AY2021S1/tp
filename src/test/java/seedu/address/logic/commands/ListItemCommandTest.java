package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.*;
import seedu.address.testutil.TypicalItems;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;

public class ListItemCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalItems.getTypicalItemList(), getTypicalLocationsList(),
                new RecipeList(), new UserPrefs());
    }

    @Test
    public void execute() {
        ListItemCommand listItemCommand = new ListItemCommand();
        String expectedMessage = ListItemCommand.MESSAGE_SUCCESS;
        // model should remain unchanged
        System.out.println(expectedMessage);
        CommandResult expectedCommandResult = new CommandResult("Listed ");
        assertCommandSuccess(listItemCommand, model, expectedCommandResult, model);
    }
}