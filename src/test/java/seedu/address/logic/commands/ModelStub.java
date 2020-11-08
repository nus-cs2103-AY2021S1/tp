package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
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

public class ModelStub implements Model {

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setFitNusFilePath(Path fitNusFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getFitNusFilePath() {
        throw new AssertionError("This method should not be called.");
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
    public void setExercise(Exercise target, Exercise editedExercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setLesson(Lesson target, Lesson editedLesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public double getBmi() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int getCalories() {
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
    public void addLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addExerciseToRoutine(Routine r, Exercise e) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addSlotToTimetable(Slot slot) {
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
    public void addCalories(Calorie calories) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void minusCalories(Calorie calories) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasExercise(Exercise exercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRoutine(Routine r) {
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
    public void deleteExercise(Exercise target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRoutine(Routine target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteLesson(Lesson target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteExerciseFromRoutine(Routine routine, Exercise exercise) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteSlotFromTimetable(Slot slot) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Routine retrieveRoutine(Routine routine) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Lesson retrieveLesson(Lesson lesson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void viewRoutine(Routine routineToView) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Exercise> getFilteredExerciseList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Routine> getFilteredRoutineList() {
        return null;
    }

    @Override
    public ObservableList<Lesson> getFilteredLessonList() {
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
    public ObservableList<DailyCalorie> getFilteredDailyCalorie() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<DailyCalorie> getFilteredDailyCalorieList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Body> getFilteredBody() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredExerciseList(Predicate<Exercise> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRoutineList(Predicate<Routine> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredLessonList(Predicate<Lesson> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredCalorieLog(Predicate<DailyCalorie> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
