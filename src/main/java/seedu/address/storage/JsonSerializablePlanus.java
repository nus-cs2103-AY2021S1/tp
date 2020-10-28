package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.task.Task;
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.event.Event;

/**
 * An Immutable Planus that is serializable to JSON format.
 */
@JsonRootName(value = "planus")
class JsonSerializablePlanus {

    public static final String MESSAGE_DUPLICATE_TASK = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedDeadline> deadlines = new ArrayList<>();
    private final List<JsonAdaptedEvent> events = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();
    private final List<JsonAdaptedEvent> calendar = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializablePlanus} with the given tasks.
     */
    @JsonCreator
    public JsonSerializablePlanus(@JsonProperty("deadlines") List<JsonAdaptedDeadline> deadlines,
                                  @JsonProperty("events") List<JsonAdaptedEvent> events) {
        this.deadlines.addAll(deadlines);
        this.events.addAll(events);
    }

    /**
     * Converts a given {@code ReadOnlyPlanus} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePlanus}.
     */
    public JsonSerializablePlanus(ReadOnlyPlanus source) {
        deadlines.addAll(source.getTaskList().stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .map(JsonAdaptedDeadline::new).collect(Collectors.toList()));
        events.addAll(source.getTaskList().stream().filter(task -> task instanceof Event).map(task -> (Event) task)
                .filter(event -> !event.isLesson()).map(JsonAdaptedEvent::new).collect(Collectors.toList()));
        lessons.addAll(source.getLessonList().stream().map(JsonAdaptedLesson::new).collect(Collectors.toList()));
        calendar.addAll(source.getCalendarList().stream().map(task -> (Event) task)
                .map(JsonAdaptedEvent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this PlaNus into the model's {@code Planus} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Planus toModelType() throws IllegalValueException {
        Planus planus = new Planus();
        for (JsonAdaptedDeadline jsonAdaptedDeadline : deadlines) {
            Deadline deadline = jsonAdaptedDeadline.toModelType();
            if (planus.hasTask(deadline)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            planus.addTask(deadline);
        }

        for (JsonAdaptedEvent jsonAdaptedEvent : events) {
            Event event = jsonAdaptedEvent.toModelType();
            if (planus.hasTask(event)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            planus.addTask(event);
            planus.addTaskToCalendar(event);
        }

        for (JsonAdaptedLesson jsonAdaptedLesson : lessons) {
            Lesson lesson = jsonAdaptedLesson.toModelType();
            ArrayList<Task> tasks = lesson.createRecurringTasks();

            for (Task task : tasks) {
                if (planus.hasTask(task)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
                }
                planus.addTask(task);
                planus.addTaskToCalendar(task);
            }
            planus.addLesson(lesson);
        }
        return planus;
    }

}
