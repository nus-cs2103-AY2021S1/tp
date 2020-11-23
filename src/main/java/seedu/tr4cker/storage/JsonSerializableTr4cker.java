package seedu.tr4cker.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Task;

/**
 * An Immutable Tr4cker that is serializable to JSON format.
 */
@JsonRootName(value = "tr4cker")
class JsonSerializableTr4cker {

    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_EVENT = "Events list contains duplicate event(s).";
    public static final String MESSAGE_DUPLICATE_MODULE = "Module list contains duplicate module(s).";
    public static final String MESSAGE_DUPLICATE_TODO = "Daily todo list contains duplicate todo(s).";
    public static final String MESSAGE_INVALID_MODULE = "Given module does not exist in TR4CKER.";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();
    private final List<JsonAdaptedModule> modules = new ArrayList<>();
    private final List<JsonAdaptedDaily> todos = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTr4cker} with the given tasks & modules.
     */
    @JsonCreator
    public JsonSerializableTr4cker(@JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                   @JsonProperty("events") List<JsonAdaptedEvent> events,
                                   @JsonProperty("modules") List<JsonAdaptedModule> modules,
                                   @JsonProperty("todos") List<JsonAdaptedDaily> todos) {
        this.tasks.addAll(tasks);
        this.events.addAll(events);
        this.modules.addAll(modules);
        this.todos.addAll(todos);
    }

    /**
     * Converts a given {@code ReadOnlyTr4cker} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTr4cker}.
     */
    public JsonSerializableTr4cker(ReadOnlyTr4cker source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
        events.addAll(source.getEventList().stream().map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        modules.addAll(source.getModuleList().stream().map(JsonAdaptedModule::new).collect(Collectors.toList()));
        todos.addAll(source.getTodoList().stream().map(JsonAdaptedDaily::new).collect(Collectors.toList()));
    }

    /**
     * Converts tasks into the model's {@code Tr4cker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    private void tasksToModelType(Tr4cker tr4cker) throws IllegalValueException {
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (tr4cker.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            if (!task.isCompleted() && !tr4cker.hasValidModuleField(task)) {
                throw new IllegalValueException(MESSAGE_INVALID_MODULE);
            }
            tr4cker.addTask(task);
        }
    }
    public void modulesToModelType(Tr4cker tr4cker) throws IllegalValueException {
        for (JsonAdaptedModule jsonAdaptedModule : modules) { // Must add modules first.
            Module module = jsonAdaptedModule.toModelType();
            if (tr4cker.hasModule(module)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MODULE);
            }
            tr4cker.addModule(module);
        }
    }

    /**
     * Converts events into the model's {@code Tr4cker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    private void eventsToModelType(Tr4cker tr4cker) throws IllegalValueException {
        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (tr4cker.hasEvent(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT);
            }
            tr4cker.addEvent(event);
        }
    }

    /**
     * Converts todos into the model's {@code Tr4cker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    private void todosToModelType(Tr4cker tr4cker) throws IllegalValueException {
        for (JsonAdaptedDaily jsonAdaptedDaily : todos) {
            Todo todo = jsonAdaptedDaily.toModelType();
            if (tr4cker.hasTodo(todo)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TODO);
            }
            tr4cker.addTodo(todo);
        }
    }

    /**
     * Converts this Tr4cker into the model's {@code Tr4cker} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Tr4cker toModelType() throws IllegalValueException {
        Tr4cker tr4cker = new Tr4cker();
        modulesToModelType(tr4cker);
        tasksToModelType(tr4cker);
        eventsToModelType(tr4cker);
        todosToModelType(tr4cker);
        return tr4cker;
    }

}
