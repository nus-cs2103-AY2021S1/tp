package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Deadline.DEADLINE_DATE_TIME_FORMAT;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.parser.ParseException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.UniqueAssignmentList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.UniqueLessonList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.timetable.TimetableData;
import seedu.address.timetable.TimetableRetriever;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameAssignment comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueAssignmentList assignments;
    private final UniqueLessonList lessons;
    private final UniqueTaskList tasks;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        assignments = new UniqueAssignmentList();
        lessons = new UniqueLessonList();
        tasks = new UniqueTaskList();
    }

    public AddressBook() { }

    /**
     * Creates an AddressBook using the Assignments in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
        autoUpdateTaskList();
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the assignment list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Replaces the contents of the lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Replaces the contents of the tasks list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setAssignments(newData.getAssignmentList());
        setLessons(newData.getLessonList());
        setTasks(newData.getTaskList());
    }

    //// assignment-level operations

    /**
     * Returns true if an assignment with the same identity as {@code assignment} exists in the address book.
     */
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    /**
     * Adds an assignment to the address book.
     * The assignment must not already exist in the address book.
     */
    public void addAssignment(Assignment a) {
        assignments.add(a);
        updateTasks();
    }

    /**
     * Replaces the given assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in the address book.
     * The assignment identity of {@code editedAssignment} must not be the same as another
     * existing assignment in the address book.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireNonNull(editedAssignment);

        assignments.setAssignment(target, editedAssignment);
        updateTasks();
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAssignment(Assignment key) {
        assignments.remove(key);
        updateTasks();
    }

    //// lesson-level operations

    /**
     * Imports and adds lessons based on NUSMods timetable data.
     */
    public void importTimetable(TimetableData data) {
        try {
            clearLessons();
            List<Lesson> lessons = TimetableRetriever.retrieveLessons(data);
            for (Lesson lesson : lessons) {
                addLesson(lesson);
            }
            updateTasks();
        } catch (IOException | ParseException e) {
            // nothing happens for now.
        }
    }

    /**
     * Adds a lesson to the address book.
     * The lesson must not already exist in the address book.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Clears all lessons in address book.
     */
    public void clearLessons() {
        lessons.removeAll();
    }

    //// task-level operations

    /**
     * Retrieves all assignments and lessons from the respective lists and adds them into the task list.
     */
    private void retrieveTasks() {
        tasks.getInternalList().clear();
        tasks.getInternalList().addAll(assignments.getInternalList());
        tasks.getInternalList().addAll(lessons.getInternalList());
    }

    /**
     * Removes any tasks that are overdue.
     * A task is overdue if the date and time of the task is before the current date and time.
     */
    private void filterOverdueTasks() {
        tasks.getInternalList().removeIf(task -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime time = LocalDateTime.parse(task.getTime().value, inputFormat);
            return time.isBefore(LocalDateTime.now());
        });
    }

    /**
     * Sorts the task list according to date and time of the task.
     */
    private void sortTasks() {
        tasks.getInternalList().sort((firstTask, secondTask) -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime firstTaskDateTime = LocalDateTime.parse(firstTask.getTime().value, inputFormat);
            LocalDateTime secondTaskDateTime = LocalDateTime.parse(secondTask.getTime().value, inputFormat);
            return firstTaskDateTime.compareTo(secondTaskDateTime);
        });
    }

    /**
     * Updates the task list in the address book.
     */
    private void updateTasks() {
        retrieveTasks();
        filterOverdueTasks();
        sortTasks();
    }

    private void autoUpdateTaskList() {
        Thread javaFx = Thread.currentThread();
        System.out.println(javaFx.getName());

        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() {
                new Timer(true).schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                Task upcomingTask = tasks.getInternalList().get(0);
                                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(DEADLINE_DATE_TIME_FORMAT)
                                        .withResolverStyle(ResolverStyle.STRICT);
                                LocalDateTime time = LocalDateTime.parse(upcomingTask.getTime().value, inputFormat);
                                boolean isOverdue = time.isBefore(LocalDateTime.now());

                                if (isOverdue) {
                                    Platform.runLater(() -> {
                                        updateTasks();
                                    });
                                }
                            }
                        }, 0, 1000);
                return null;
            }
        };
        task.run();
    }

    //// util methods

    @Override
    public String toString() {
        return assignments.asUnmodifiableObservableList().size() + " assignments"
                + lessons.asUnmodifiableObservableList().size() + "lessons"
                + tasks.asUnmodifiableObservableList().size() + "tasks";
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return assignments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Task> getTaskList() {
        updateTasks();

        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && assignments.equals(((AddressBook) other).assignments))
                && lessons.equals(((AddressBook) other).lessons);
    }

    @Override
    public int hashCode() {
        // multiply sum of fields with prime number 31
        return 31 * (assignments.hashCode() + lessons.hashCode());
    }
}
