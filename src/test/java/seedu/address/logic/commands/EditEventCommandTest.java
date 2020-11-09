package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENT1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_EVENT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_IMPORTANT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.getTypicalEventsTaskList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_TASK;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.edit.EditEventCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.testutil.EventBuilder;

public class EditEventCommandTest {

    private Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalEventsTaskList());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Event editedEvent = new EventBuilder().build();

        EditEventCommand.EditEventDescriptor descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setDescription("meeting");
        descriptor.setStartDate("12-12-2020");
        descriptor.setStartTime("1000");
        descriptor.setEndDate("12-12-2020");
        descriptor.setEndTime("1130");

        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIFTH_TASK, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);
        Model expectedModel = new ModelManager(new AddressBook(),
                new UserPrefs(), new TaskList(model.getTaskList()));
        expectedModel.setTask(model.getFilteredTaskList().get(4), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastEvent = Index.fromOneBased(model.getFilteredTaskList().size());
        Event lastEvent = (Event) model.getFilteredTaskList().get(indexLastEvent.getZeroBased());

        EventBuilder eventInList = new EventBuilder(lastEvent);
        Event editedEvent = eventInList.withDescription(VALID_DESCRIPTION)
                .withStartDateTime(VALID_DATE_TIME_TUTOR_MEETING)
                .withTags(VALID_TAG_IMPORTANT).build();

        EditEventCommand.EditEventDescriptor descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setDescription(VALID_DESCRIPTION);
        descriptor.setStartDate("10-12-2017");
        descriptor.setStartTime("1445");
        descriptor.setTags(Stream.of(VALID_TAG_IMPORTANT).map(Tag::new).collect(Collectors.toSet()));

        EditEventCommand editEventCommand = new EditEventCommand(indexLastEvent, descriptor);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_EDIT_EVENT_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(new AddressBook(),
                new UserPrefs(), new TaskList(model.getTaskList()));
        expectedModel.setTask(lastEvent, editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, "TASK", expectedModel);
    }

    @Test
    public void execute_duplicateTodoUnfilteredList_failure() {
        Event firstEvent = (Event) model.getFilteredTaskList().get(INDEX_FIFTH_TASK.getZeroBased());
        EditEventCommand.EditEventDescriptor descriptor = new EditEventCommand.EditEventDescriptor();
        descriptor.setDescription(firstEvent.getDescription());
        descriptor.setTags(firstEvent.getTags());
        descriptor.setStartDate(firstEvent.getStartDateTime().split(" ", 2)[0]);
        descriptor.setStartTime(firstEvent.getStartDateTime().split(" ", 2)[1]);
        descriptor.setEndDate(firstEvent.getEndDateTime().split(" ", 2)[0]);
        descriptor.setEndTime(firstEvent.getEndTime().split(" ", 2)[1]);
        descriptor.setTags(firstEvent.getTags());

        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SIXTH_TASK, descriptor);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_DUPLICATE_EVENT);
    }

    @Test
    public void equals() {
        final EditEventCommand standardCommand = new EditEventCommand(INDEX_FIRST_TASK, DESC_EVENT1);

        // same values -> returns true
        EditEventCommand.EditEventDescriptor copyDescriptor = new EditEventCommand.EditEventDescriptor(DESC_EVENT1);
        EditEventCommand commandWithSameValues = new EditEventCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_SECOND_TASK, DESC_EVENT1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditEventCommand(INDEX_FIRST_TASK, DESC_EVENT2)));
    }
}
