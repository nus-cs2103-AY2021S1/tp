package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.exercise.Exercise;

public interface LogicForExercise {

    public CommandResult execute(String commandText) throws CommandException, ParseException, IOException;

    public ReadOnlyExerciseBook getExerciseBook();

    public ObservableList<Exercise> getFilteredExerciseList();

    public HashMap<String, Integer> getCaloriesByDay();

    public Path getExerciseBookFilePath();

    public GuiSettings getGuiSettings();

    public void setGuiSettings(GuiSettings guiSettings);
}
