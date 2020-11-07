package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.lessons.LessonAddCommand;
import seedu.address.model.FitNus;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.body.Body;
import seedu.address.model.body.Height;
import seedu.address.model.body.Weight;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.DailyCalorie;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.routine.Routine;
import seedu.address.model.timetable.Slot;
import seedu.address.testutil.LessonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonAddCommand(null));
    }

    @Test
    public void execute_lessonAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLessonAdded modelLessonStub = new ModelStubAcceptingLessonAdded();
        Lesson validLesson = new LessonBuilder().build();

        CommandResult commandResult = new LessonAddCommand(validLesson).execute(modelLessonStub);

        assertEquals(String.format(LessonAddCommand.MESSAGE_SUCCESS, validLesson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validLesson), modelLessonStub.lessonsAdded);
    }

    @Test
    public void execute_duplicateLesson_throwsCommandException() {
        Lesson validLesson = new LessonBuilder().build();
        LessonAddCommand lessonAddCommand = new LessonAddCommand(validLesson);
        ModelStub modelLessonStub = new ModelStubWithLesson(validLesson);

        assertThrows(CommandException.class, LessonAddCommand.MESSAGE_DUPLICATE_LESSON, () ->
                lessonAddCommand.execute(modelLessonStub));
    }

    @Test
    public void lessonEquals() {
        Lesson cs1000 = new LessonBuilder().withName("CS1000").build();
        Lesson cs2000 = new LessonBuilder().withName("CS2000").build();
        LessonAddCommand lessonAddCS1000Command = new LessonAddCommand(cs1000);
        LessonAddCommand lessonAddCS2000Command = new LessonAddCommand(cs2000);

        // same object -> returns true
        assertTrue(lessonAddCS1000Command.equals(lessonAddCS1000Command));

        // same values -> returns true
        LessonAddCommand lessonAddCS1000CommandCopy = new LessonAddCommand(cs1000);
        assertTrue(lessonAddCS1000Command.equals(lessonAddCS1000CommandCopy));

        // different types -> returns false
        assertFalse(lessonAddCS1000Command.equals(1));

        // null -> returns false
        assertFalse(lessonAddCS1000Command.equals(null));

        // different body -> returns false
        assertFalse(lessonAddCS1000Command.equals(lessonAddCS2000Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getFitNusFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFitNusFilePath(Path fitNusFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRoutine(Routine r) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExerciseToRoutine(Routine r, Exercise e) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoutineList(Predicate<Routine> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRoutine(Routine target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHeight(Height height) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addWeight(Weight weight) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getBmi() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Routine> getFilteredRoutineList() {
            return null;
        }

        @Override
        public void setFitNus(ReadOnlyFitNus newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSlot(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOverlappingSlot(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSlotToTimetable(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSlotFromTimetable(Slot slot) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExercise(Exercise target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLesson(Lesson target) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void setExercise(Exercise target, Exercise editedExercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLesson(Lesson target, Lesson editedLesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Lesson retrieveLesson(Lesson lesson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Routine retrieveRoutine(Routine routine) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExerciseFromRoutine(Routine routine, Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void viewRoutine(Routine routineToView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCalories(Calorie calories) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void minusCalories(Calorie calories) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DailyCalorie> getFilteredDailyCalorie() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<DailyCalorie> getFilteredDailyCalorieList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getCalories() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Lesson> getFilteredLessonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Body> getFilteredBody() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Slot> getFilteredSlotList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Slot> getFilteredSlotList(String day) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCalorieLog(Predicate<DailyCalorie> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLessonList(Predicate<Lesson> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single lesson.
     */
    private class ModelStubWithLesson extends ModelStub {
        private final Lesson lesson;

        ModelStubWithLesson(Lesson lesson) {
            requireNonNull(lesson);
            this.lesson = lesson;
        }

        @Override
        public boolean hasLesson(Lesson lesson) {
            requireNonNull(lesson);
            return this.lesson.isSameLesson(lesson);
        }
    }

    /**
     * A Model stub that always accept the lesson being added.
     */
    private class ModelStubAcceptingLessonAdded extends ModelStub {
        final ArrayList<Lesson> lessonsAdded = new ArrayList<>();

        @Override
        public boolean hasLesson(Lesson lesson) {
            requireNonNull(lesson);
            return lessonsAdded.stream().anyMatch(lesson::isSameLesson);
        }

        @Override
        public void addLesson(Lesson lesson) {
            requireNonNull(lesson);
            lessonsAdded.add(lesson);
        }

        @Override
        public ReadOnlyFitNus getFitNus() {
            return new FitNus();
        }
    }
}
