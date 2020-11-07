package seedu.address.logic.commands.contactlistcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

public class ListContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ModuleList(), new ModuleList(),
                getTypicalContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel = new ModelManager(new ModuleList(), new ModuleList(),
                model.getContactList(), new TodoList(), new EventList(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsAllContacts() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        assertCommandSuccess(new ListContactCommand(), model, ListContactCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
