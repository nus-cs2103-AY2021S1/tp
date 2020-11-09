package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTodos.getTypicalTodosTaskList;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactTaskTagCommand.EditPersonTags;
import seedu.address.logic.commands.ContactTaskTagCommand.EditTaskTags;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;
import seedu.address.testutil.EditPersonTagsBuilder;
import seedu.address.testutil.EditTaskTagsBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TodoBuilder;

public class ContactTaskTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTodosTaskList());

    @Test
    public void execute_filteredList_success() throws ParseException {
        Index personIndex = ParserUtil.parseIndex("3");
        Index taskIndex = ParserUtil.parseIndex("1");

        Person personInFilteredList = model.getFilteredPersonList().get(personIndex.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withTags("test").build();
        Task taskInFilteredList = model.getFilteredTaskList().get(taskIndex.getZeroBased());
        Task editedTask = new TodoBuilder((Todo) taskInFilteredList).withTags("test").build();

        ContactTaskTagCommand contactTaskTagCommand = new ContactTaskTagCommand(personIndex, taskIndex,
                new EditPersonTagsBuilder(personInFilteredList).withTags("test").build(),
                new EditTaskTagsBuilder(taskInFilteredList).withTags("test").build());

        String expectedMessage = String.format(ContactTaskTagCommand.MESSAGE_CONTACT_TASK_TAG_SUCCESS,
                editedPerson, editedTask);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalTodosTaskList());
        expectedModel.setPerson(model.getFilteredPersonList().get(personIndex.getZeroBased()), editedPerson);
        expectedModel.setTask(model.getFilteredTaskList().get(taskIndex.getZeroBased()), editedTask);

        assertCommandSuccess(contactTaskTagCommand, model, expectedMessage, "CONTACT", expectedModel);
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundIndexPerson = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Index taskIndex = Index.fromOneBased(model.getFilteredTaskList().size());
        EditPersonTags editPersonTags = new EditPersonTagsBuilder().withTags("test").build();
        EditTaskTags editTaskTags = new EditTaskTagsBuilder().withTags("test").build();
        ContactTaskTagCommand contactTaskTagCommand = new ContactTaskTagCommand(outOfBoundIndexPerson,
                taskIndex, editPersonTags, editTaskTags);

        assertCommandFailure(contactTaskTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTaskIndex_failure() {
        Index personIndex = Index.fromOneBased(model.getFilteredPersonList().size());
        Index outOfBoundIndexTask = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditPersonTags editPersonTags = new EditPersonTagsBuilder().withTags("test").build();
        EditTaskTags editTaskTags = new EditTaskTagsBuilder().withTags("test").build();
        ContactTaskTagCommand contactTaskTagCommand = new ContactTaskTagCommand(personIndex,
                outOfBoundIndexTask, editPersonTags, editTaskTags);

        assertCommandFailure(contactTaskTagCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() throws ParseException {
        List<String> tag = Arrays.asList("test");
        EditPersonTags editPersonTags = new EditPersonTags();
        editPersonTags.setTags(ParserUtil.parseTags(tag));

        EditTaskTags editTaskTags = new EditTaskTags();
        editTaskTags.setTags(ParserUtil.parseTags(tag));

        final ContactTaskTagCommand standardCommand = new ContactTaskTagCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, editPersonTags, editTaskTags);

        // same values -> returns true
        EditPersonTags editPersonTagsCopy = new EditPersonTagsBuilder().withTags("test").build();
        EditTaskTags editTaskTagsCopy = new EditTaskTagsBuilder().withTags("test").build();
        ContactTaskTagCommand commandWithSameValues = new ContactTaskTagCommand(INDEX_FIRST_PERSON,
                INDEX_FIRST_PERSON, editPersonTagsCopy, editTaskTagsCopy);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different indexes -> returns false
        assertFalse(standardCommand.equals(new ContactTaskTagCommand(INDEX_SECOND_PERSON, INDEX_SECOND_PERSON,
                editPersonTags, editTaskTags)));
    }

}
