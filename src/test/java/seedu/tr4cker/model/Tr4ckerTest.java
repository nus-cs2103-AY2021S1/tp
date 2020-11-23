package seedu.tr4cker.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_EVENT_DATE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_EVENT_DATE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_EVENT_NAME_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_EVENT_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_CODE_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_NAME_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalTasks.EVENT1;
import static seedu.tr4cker.testutil.TypicalTasks.TASK1;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.countdown.exceptions.DuplicateEventException;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.module.exceptions.DuplicateModuleException;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.exceptions.DuplicateTaskException;
import seedu.tr4cker.testutil.TaskBuilder;

public class Tr4ckerTest {

    private final Tr4cker tr4cker = new Tr4cker();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), tr4cker.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tr4cker.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTr4cker_replacesData() {
        Tr4cker newData = getTypicalTr4cker();
        tr4cker.resetData(newData);
        assertEquals(newData, tr4cker);
    }

    @Test
    public void resetData_withDuplicates_throwsDuplicateException() {
        // Two tasks with the same identity fields
        Task editedAlice = new TaskBuilder(TASK1).withTaskDescription(VALID_DESCRIPTION_2).withTags(VALID_TAG_URGENT)
                .build();
        List<Task> newTasks = Arrays.asList(TASK1, editedAlice);
        Tr4ckerStub newData = new Tr4ckerStub(newTasks, new ArrayList<>(), new ArrayList<>());

        assertThrows(DuplicateTaskException.class, () -> tr4cker.resetData(newData));

        // Two modules with same code
        Module module = new Module(VALID_MODULE_NAME_1, new ModuleCode(VALID_MODULE_CODE_1));
        Module sameCodeModule = new Module(VALID_MODULE_NAME_2, new ModuleCode(VALID_MODULE_CODE_1));
        List<Module> newModules = Arrays.asList(module, sameCodeModule);
        Tr4ckerStub newModuleData = new Tr4ckerStub(new ArrayList<>(), newModules, new ArrayList<>());

        assertThrows(DuplicateModuleException.class, () -> tr4cker.resetData(newModuleData));

        // Two Events with same name
        Event event = new Event(new EventName(VALID_EVENT_NAME_1), new EventDate(VALID_EVENT_DATE_1, false));
        Event sameEventName = new Event(new EventName(VALID_EVENT_NAME_1), new EventDate(VALID_EVENT_DATE_2, false));
        List<Event> newEvents = Arrays.asList(event, sameEventName);
        Tr4ckerStub newEventData = new Tr4ckerStub(new ArrayList<>(), new ArrayList<>(), newEvents);

        assertThrows(DuplicateEventException.class, () -> tr4cker.resetData(newEventData));
    }

    @Test
    public void hasRelatedTasks_taskExists_returnsTrue() {
        Task task = new TaskBuilder(TASK1).withModule(VALID_MODULE_CODE_1).build();
        Module module = new Module(VALID_MODULE_NAME_1, new ModuleCode(VALID_MODULE_CODE_1));
        tr4cker.addTask(task);

        assertTrue(tr4cker.hasRelatedIncompleteTasks(module));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tr4cker.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTr4cker_returnsFalse() {
        assertFalse(tr4cker.hasTask(TASK1));
    }

    @Test
    public void hasTask_taskInTr4cker_returnsTrue() {
        tr4cker.addTask(TASK1);
        assertTrue(tr4cker.hasTask(TASK1));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTr4cker_returnsTrue() {
        tr4cker.addTask(TASK1);
        Task editedAlice = new TaskBuilder(TASK1).withTaskDescription(VALID_DESCRIPTION_2).withTags(VALID_TAG_URGENT)
                .build();
        assertTrue(tr4cker.hasTask(editedAlice));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tr4cker.getTaskList().remove(0));
    }

    @Test
    public void removeModule_moduleAddedAndRemoved_changesData() {
        Module module = new Module(VALID_MODULE_NAME_1, new ModuleCode(VALID_MODULE_CODE_1));
        tr4cker.addModule(module);

        assertTrue(tr4cker.hasModule(module));

        tr4cker.removeModule(module);

        assertFalse(tr4cker.hasModule(module));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tr4cker.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInTr4cker_returnsFalse() {
        assertFalse(tr4cker.hasEvent(EVENT1));
    }

    @Test
    public void hasEvent_eventInTr4cker_returnsTrue() {
        tr4cker.addEvent(EVENT1);
        assertTrue(tr4cker.hasEvent(EVENT1));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInTr4cker_returnsTrue() {
        tr4cker.addEvent(EVENT1);
        Event sameEvent = new Event(new EventName("Event1 Name"),
                new EventDate("01-10-2021", false));
        assertTrue(tr4cker.hasEvent(sameEvent));
    }

    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> tr4cker.getEventList().remove(0));
    }

    @Test
    public void removeEvent_eventAddedAndRemoved_changesData() {
        Event event = new Event(new EventName(VALID_EVENT_NAME_2), new EventDate(VALID_EVENT_DATE_2, false));
        tr4cker.addEvent(event);

        assertTrue(tr4cker.hasEvent(event));

        tr4cker.removeEvent(event);

        assertFalse(tr4cker.hasEvent(event));
    }

    /**
     * A stub ReadOnlyTr4cker whose tasks list can violate interface constraints.
     */
    private static class Tr4ckerStub implements ReadOnlyTr4cker {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();
        private final ObservableList<Todo> todos = FXCollections.observableArrayList();

        Tr4ckerStub(Collection<Task> tasks, Collection<Module> modules, Collection<Event> events) {
            this.tasks.setAll(tasks);
            this.modules.setAll(modules);
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Todo> getTodoList() {
            return todos;
        }
    }

}
