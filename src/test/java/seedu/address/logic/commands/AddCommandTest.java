package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.model.ExerciseBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Template;
import seedu.address.model.goal.Goal;
import seedu.address.testutil.ExerciseBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullExercise_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_exerciseAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAdded modelStub = new ModelStubAcceptingAdded();
        Exercise validExercise = new ExerciseBuilder().build();
        CommandResult commandResult = new AddCommand(validExercise).execute(modelStub);

        //assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validExercise), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExercise), modelStub.exercisesAdded);
    }

    @Test
    public void equals() {
        Exercise running = new ExerciseBuilder().withName("running").build();
        Exercise jumping = new ExerciseBuilder().withName("jumping").build();
        AddCommand addRunningCommand = new AddCommand(running);
        AddCommand addJumpingCommand = new AddCommand(jumping);

        // same object -> returns true
        assertTrue(addRunningCommand.equals(addRunningCommand));

        // different types -> returns false
        assertFalse(addRunningCommand.equals(1));

        // null -> returns false
        assertFalse(addRunningCommand.equals(null));

        // different exercise -> returns false
        assertFalse(addRunningCommand.equals(addJumpingCommand));
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
        public Path getExerciseBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getGoalBookFilePath() {
            return null;
        }

        @Override
        public void setExerciseBookFilePath(Path exerciseBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGoalBookFilePath(Path goalBookFilePath) {

        }

        @Override
        public Optional<Goal> addExercise(Exercise exercise) throws CaloriesOverflow {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTemplate(Template template) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addGoal(Goal goal) {

        }

        @Override
        public void setExerciseBook(ReadOnlyExerciseBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGoalBook(ReadOnlyGoalBook goalBook) {

        }

        @Override
        public ReadOnlyExerciseBook getExerciseBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyGoalBook getGoalBook() {
            return null;
        }

        @Override
        public boolean hasExercise(Exercise exercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasGoal(Goal goal) {
            return false;
        }

        @Override
        public void deleteExercise(Exercise target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteGoal(Goal target) {

        }

        @Override
        public void setExercise(Exercise target, Exercise editExercise) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public HashMap<String, Integer> getCaloriesByDay() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Exercise> getFilteredExerciseList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGoal(Goal target, Goal editedGoal) {

        }

        @Override
        public ObservableList<Template> getFilteredTemplateList() {
            throw new AssertionError("this method should not be called.");
        }

        @Override
        public boolean checkOverflow(Exercise newE) {
            throw new AssertionError("this method should not be called.");
        }

        @Override
        public boolean checkOverflow(Exercise oldE, Exercise newE) {
            throw new AssertionError("this method should not be called.");
        }

        @Override
        public void archive(Path path) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetAll() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that always accept the exercise being added.
     */
    private class ModelStubAcceptingAdded extends ModelStub {
        final ArrayList<Exercise> exercisesAdded = new ArrayList<>();
        final HashMap<String, Integer> caloriesByDay = new HashMap<>();

        @Override
        public boolean hasExercise(Exercise exercise) {
            requireNonNull(exercise);
            return exercisesAdded.stream().anyMatch(exercise::isSameExercise);
        }

        @Override
        public Optional<Goal> addExercise(Exercise exercise) throws CaloriesOverflow {
            requireNonNull(exercise);
            addCaloriesForDay(exercise);
            exercisesAdded.add(exercise);
            return Optional.of(new Goal(exercise.getDate()));
        }

        private void addCaloriesForDay(Exercise newEntry) {
            String stringDate = newEntry.getDate().value;
            int intCalories = Integer.parseInt(newEntry.getCalories().toString());
            if (caloriesByDay.containsKey(stringDate)) {
                Integer newCalories = caloriesByDay.get(stringDate) + intCalories;
                caloriesByDay.put(stringDate, newCalories);
            } else {
                caloriesByDay.put(stringDate, intCalories);
            }
        }

        @Override
        public boolean checkOverflow(Exercise newE) {
            return false;
        }

        @Override
        public boolean checkOverflow(Exercise oldE, Exercise newE) {
            return false;
        }

        @Override
        public HashMap<String, Integer> getCaloriesByDay() {
            return caloriesByDay;
        }

        @Override
        public ReadOnlyExerciseBook getExerciseBook() {
            return new ExerciseBook();
        }
    }

}
