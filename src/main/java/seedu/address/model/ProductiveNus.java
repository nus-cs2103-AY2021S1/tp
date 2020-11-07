package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.task.Time.TIME_DATE_TIME_FORMAT;

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
 * Wraps all data at the ProductiveNus level
 * Duplicates are not allowed (by .isSameAssignment comparison)
 */
public class ProductiveNus implements ReadOnlyProductiveNus {

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

    public ProductiveNus() { }

    /**
     * Creates a ProductiveNus using the Assignments in the {@code toBeCopied}
     */
    public ProductiveNus(ReadOnlyProductiveNus toBeCopied) {
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
     * Resets the existing data of this {@code ProductiveNus} with {@code newData}.
     */
    public void resetData(ReadOnlyProductiveNus newData) {
        requireNonNull(newData);

        setAssignments(newData.getAssignmentList());
        setLessons(newData.getLessonList());
        setTasks(newData.getTaskList());
    }

    //// assignment-level operations

    /**
     * Returns true if an assignment with the same identity as {@code assignment} exists in ProductiveNus.
     */
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return assignments.contains(assignment);
    }

    /**
     * Adds an assignment to ProductiveNus.
     * The assignment must not already exist in ProductiveNus.
     */
    public void addAssignment(Assignment a) {
        assignments.add(a);
        updateTasks();
    }

    /**
     * Replaces the given assignment {@code target} in the list with {@code editedAssignment}.
     * {@code target} must exist in ProductiveNus.
     * The assignment identity of {@code editedAssignment} must not be the same as another
     * existing assignment in ProductiveNus.
     */
    public void setAssignment(Assignment target, Assignment editedAssignment) {
        requireNonNull(editedAssignment);

        assignments.setAssignment(target, editedAssignment);
        updateTasks();
    }

    /**
     * Removes {@code key} from this {@code ProductiveNus}.
     * {@code key} must exist in ProductiveNus.
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
     * Adds a lesson to ProductiveNus.
     * The lesson must not already exist in ProductiveNus.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Clears all lessons in ProductiveNus.
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
     * Returns true if the upcoming task is over. A task is considered over if the deadline or end time of the lesson
     * has passed.
     *
     * @param upcomingTask the user's upcoming task displayed in the task list
     * @return true if the assignment's deadline or lesson is over
     */
    private boolean isOver(Task upcomingTask) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
                .withResolverStyle(ResolverStyle.STRICT);

        // If upcoming task is a lesson, check if end time of lesson has passed
        if (upcomingTask instanceof Lesson) {
            LocalDateTime lessonEndTime = LocalDateTime.parse(((Lesson) upcomingTask).getEndTime().value, inputFormat);
            return lessonEndTime.isBefore(LocalDateTime.now());
        }

        assert(upcomingTask instanceof Assignment);

        // Check if deadline of assignment has passed
        LocalDateTime deadline = LocalDateTime.parse(upcomingTask.getTime().value, inputFormat);
        return deadline.isBefore(LocalDateTime.now());
    }

    /**
     * Removes any tasks that are overdue.
     * A task is overdue if the date and time of the task is before the current date and time.
     */
    private void filterOverdueTasks() {
        tasks.getInternalList().removeIf(task -> {
            return isOver(task);
        });
    }

    /**
     * Sorts the task list according to date and time of the task.
     */
    private void sortTasks() {
        tasks.getInternalList().sort((firstTask, secondTask) -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(TIME_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            LocalDateTime firstTaskDateTime = LocalDateTime.parse(firstTask.getTime().value, inputFormat);
            LocalDateTime secondTaskDateTime = LocalDateTime.parse(secondTask.getTime().value, inputFormat);
            return firstTaskDateTime.compareTo(secondTaskDateTime);
        });
    }

    /**
     * Updates the task list in ProductiveNus.
     */
    private void updateTasks() {
        retrieveTasks();
        filterOverdueTasks();
        sortTasks();
    }

    /**
     * Checks upcoming tasks every second and updates the task list if a task is over.
     */
    private void checkTaskListEverySecond() {
        // Solution below adapted from https://stackoverflow.com/questions/9966136/javafx-periodic-background-task
        new Timer(true).schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (tasks.getInternalList().size() > 0) {
                            Task upcomingTask = tasks.getInternalList().get(0);
                            boolean isOver = isOver(upcomingTask);

                            if (isOver) {
                                Platform.runLater(() -> {
                                    updateTasks();
                                });
                            }
                        }
                    }
                }, 0, 1000);
    }

    /**
     * Updates the task list whenever a task is over.
     */
    private void autoUpdateTaskList() {
        // Solution below adapted from https://docs.oracle.com/javafx/2/api/javafx/concurrent/Task.html
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() {
                checkTaskListEverySecond();
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
                || (other instanceof ProductiveNus // instanceof handles nulls
                && assignments.equals(((ProductiveNus) other).assignments))
                && lessons.equals(((ProductiveNus) other).lessons);
    }

    @Override
    public int hashCode() {
        // multiply sum of fields with prime number 31
        return 31 * (assignments.hashCode() + lessons.hashCode());
    }
}
