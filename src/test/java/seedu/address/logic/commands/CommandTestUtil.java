package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Planus;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.LessonContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    //valid lesson
    public static final String VALID_TITLE_CS2103T = "CS2103T Lecture";
    public static final String VALID_DESC_CS2103T = "Best lecture ever!";
    public static final String VALID_DAY_CS2103T = "Monday";
    public static final String VALID_START_TIME_CS2103T = "12:00";
    public static final String VALID_END_TIME_CS2103T = "14:00";
    public static final String VALID_START_DATE_CS2103T = "01-01-2020";
    public static final String VALID_END_DATE_CS2103T = "01-11-2020";
    public static final String VALID_TAG_CS2103T = "CS2103T";
    public static final Lesson VALID_LESSON_CS2103T = new Lesson(new Title(VALID_TITLE_CS2103T),
            new Tag(VALID_TAG_CS2103T), new Description(VALID_DESC_CS2103T), DayOfWeek.MONDAY,
            LocalTime.of(12, 0), LocalTime.of(14, 0),
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 11, 1));
    public static final Lesson VALID_LESSON_CS2103T_NO_DESC = new Lesson(new Title(VALID_TITLE_CS2103T),
            new Tag(VALID_TAG_CS2103T), Description.defaultDescription(), DayOfWeek.MONDAY,
            LocalTime.of(12, 0), LocalTime.of(14, 0),
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 11, 1));
    //valid lesson
    public static final String VALID_TITLE_CS2000 = "CS2000 Lecture";
    public static final String VALID_DESC_CS2000 = "Best lecture ever!";
    public static final String VALID_DAY_CS2000 = "Monday";
    public static final String VALID_TAG_CS2000 = "CS2103T";
    public static final String VALID_START_DATE_CS2000 = "01-12-2020";
    public static final String VALID_END_DATE_CS2000 = "21-12-2020";
    public static final String VALID_START_TIME_CS2000 = "10:00";
    public static final String VALID_END_TIME_CS2000 = "12:00";
    public static final Lesson VALID_LESSON_CS2000 = new Lesson(new Title(VALID_TITLE_CS2000),
            new Tag(VALID_TAG_CS2000), new Description(VALID_DESC_CS2000), DayOfWeek.MONDAY,
            LocalTime.of(13, 0), LocalTime.of(14, 0),
            LocalDate.of(2020, 1, 1), LocalDate.of(2020, 11, 1));
    //valid lesson
    public static final String VALID_TITLE_CS2100 = "CS2100 Lecture";
    public static final String VALID_DESC_CS2100 = "Cool lecture!";
    public static final String VALID_DAY_CS2100 = "Tuesday";
    public static final String VALID_START_TIME_CS2100 = "14:00";
    public static final String VALID_END_TIME_CS2100 = "16:00";
    public static final String VALID_START_DATE_CS2100 = "01-11-2020";
    public static final String VALID_END_DATE_CS2100 = "01-12-2020";
    public static final String VALID_TAG_CS2100 = "CS2100";
    public static final Lesson VALID_LESSON_CS2100 = new Lesson(new Title(VALID_TITLE_CS2100),
            new Tag(VALID_TAG_CS2100), new Description(VALID_DESC_CS2100), DayOfWeek.TUESDAY,
            LocalTime.of(14, 0), LocalTime.of(16, 0),
            LocalDate.of(2020, 11, 1), LocalDate.of(2020, 12, 1));
    //invalid lesson
    public static final String INVALID_DAY_CS2103T = "ajhsf";
    public static final String INVALID_START_TIME_CS2103T = "14:60";
    public static final String INVALID_END_TIME_CS2103T = "16:60";
    public static final String INVALID_START_DATE_CS2103T = "32-11-2020";
    public static final String INVALID_END_DATE_CS2103T = "01-13-2020";
    //invalid lesson
    public static final String INVALID_DAY_CS2100 = "ajhsf";
    public static final String INVALID_START_TIME_CS2100 = "14:60";
    public static final String INVALID_END_TIME_CS2100 = "16:60";
    public static final String INVALID_START_DATE_CS2100 = "32-11-2020";
    public static final String INVALID_END_DATE_CS2100 = "01-13-2020";
    //valid event
    public static final String VALID_TITLE_EXPERIMENT = "Science experiment";
    public static final String VALID_DESC_EXPERIMENT = "Do grape experiment";
    public static final String VALID_DATE_EXPERIMENT = "01-01-2020";
    public static final String VALID_START_TIME_EXPERIMENT = "10:00";
    public static final String VALID_END_TIME_EXPERIMENT = "12:00";
    public static final String VALID_START_DATETIME_EXPERIMENT = "01-01-2020 10:00";
    public static final String VALID_END_DATETIME_EXPERIMENT = "01-01-2020 12:00";
    public static final String VALID_TAG_EXPERIMENT = "LSM1301";
    public static final Event VALID_EVENT_EXPERIMENT = Event.createUserEvent(new Title(VALID_TITLE_EXPERIMENT),
            new StartDateTime(VALID_START_DATETIME_EXPERIMENT), new EndDateTime(VALID_END_DATETIME_EXPERIMENT),
            new Description(VALID_DESC_EXPERIMENT), new Tag(VALID_TAG_EXPERIMENT));
    //valid event that clashes with CS2100 Lecture
    public static final String VALID_TITLE_MEETING = "Project meeting";
    public static final String VALID_DESC_MEETING = "Important meeting that clashes with CS2100 Lecture";
    public static final String VALID_DATE_MEETING = "03-11-2020";
    public static final String VALID_START_TIME_MEETING = "14:00";
    public static final String VALID_END_TIME_MEETING = "22:00";
    public static final String VALID_START_DATETIME_MEETING = "03-11-2020 14:00";
    public static final String VALID_END_DATETIME_MEETING = "03-11-2020 22:00";
    public static final String VALID_TAG_MEETING = "CS2101";
    public static final Event VALID_EVENT_MEETING = Event.createUserEvent(new Title(VALID_TITLE_MEETING),
            new StartDateTime(VALID_START_DATETIME_MEETING), new EndDateTime(VALID_END_DATETIME_MEETING),
            new Description(VALID_DESC_MEETING), new Tag(VALID_TAG_MEETING));
    //invalid event
    public static final String INVALID_DATE_MEETING = "32-01-2020";
    public static final String INVALID_START_TIME_MEETING = "20:60";
    public static final String INVALID_END_TIME_MEETING = "25:60";
    //valid deadline
    public static final String VALID_TITLE_LAB = "Do weekly lab assignment";
    public static final String VALID_DESC_LAB = "Prepare for demo during tutorial";
    public static final String VALID_DATETIME_LAB = "01-01-2020 22:00";
    public static final String VALID_TAG_LAB = "CS2100";
    //valid deadline
    public static final String VALID_TITLE_ESSAY = "submit essay for ES2660";
    public static final String VALID_DESC_ESSAY = "2000 words!";
    public static final String VALID_DATETIME_ESSAY = "01-01-2020 23:59";
    public static final String VALID_TAG_ESSAY = "ES2660";
    //invalid deadline
    public static final String INVALID_DATETIME_LAB = "01-13-2020 23:59";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Planus expectedPlanus = new Planus(actualModel.getPlanus());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPlanus, actualModel.getPlanus());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTitle = task.getTitle().title.split("\\s+");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, splitTitle[0]);
        model.updateFilteredTaskList(predicate);

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the lessons at the given {@code targetIndex} in the
     * {@code model}'s lesson list.
     */
    public static void showLessonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredLessonList().size());

        Lesson lesson = model.getFilteredLessonList().get(targetIndex.getZeroBased());
        final String[] splitTitle = lesson.getTitle().title.split("\\s+");
        LessonContainsKeywordsPredicate predicate = new LessonContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, splitTitle[0]);
        model.updateFilteredLessonList(predicate);

        assertEquals(1, model.getFilteredLessonList().size());
    }
}
