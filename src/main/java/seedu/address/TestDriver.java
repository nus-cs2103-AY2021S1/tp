package seedu.address;

import static seedu.address.model.util.SampleDataUtil.getExerciseTagSet;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.logic.Logic;
import seedu.address.model.ExerciseBook;
import seedu.address.model.Model;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.Muscle;
import seedu.address.model.exercise.Name;
import seedu.address.storage.JsonExerciseBookStorage;
import seedu.address.storage.StorageForExercise;
import seedu.address.ui.Ui;

/**
 * Runs the application.
 */
public class TestDriver {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected StorageForExercise storage;
    protected Model model;
    protected Config config;

    /**
     * Driver to test out JsonExerciseBook and its related classes.
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            JsonExerciseBookStorage jStorage = new JsonExerciseBookStorage(
                    new File("testingForExercise.json").toPath());
            ExerciseBook exerciseBook = new ExerciseBook();
            exerciseBook.resetData(jStorage.readExerciseBook().get());
            ArrayList<Muscle> musclesWorked = new ArrayList<Muscle>(Arrays.asList(Muscle.CHEST, Muscle.LEGS));

            exerciseBook.addExercise(new Exercise(new Name("Hello"), new Description("Test 1"),
                    new Date("10-10-2020"), new Calories("101"), musclesWorked, getExerciseTagSet("gym")));
            exerciseBook.addExercise(new Exercise(new Name("Hello1"), new Description("Test 2"),
                    new Date("10-10-2021"), new Calories("102"), musclesWorked, getExerciseTagSet("gym")));
            exerciseBook.addExercise(new Exercise(new Name("Hello2"), new Description("Test 3"),
                    new Date("10-10-2022"), new Calories("103"), musclesWorked, getExerciseTagSet("home")));
            exerciseBook.addExercise(new Exercise(new Name("Hello3"), new Description("Test 4"),
                    new Date("10-10-2023"), new Calories("104"), musclesWorked, getExerciseTagSet("home")));
            exerciseBook.addExercise(new Exercise(new Name("Hello4"), new Description("Test 5"),
                    new Date("10-10-2024"), new Calories("105"), musclesWorked, getExerciseTagSet("home, gym")));
            jStorage.saveExerciseBook(exerciseBook);
            System.out.println(exerciseBook.toString());
        } catch (Exception err) {
            System.out.println(err.toString());
        }
    }
}



