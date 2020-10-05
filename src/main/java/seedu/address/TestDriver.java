package seedu.address;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import seedu.address.commons.core.Config;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Version;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.ConfigUtil;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.*;
import seedu.address.model.exercise.*;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.storage.*;
import seedu.address.ui.Ui;
import seedu.address.ui.UiManager;

/**
 * Runs the application.
 */
public class TestDriver {

    public static final Version VERSION = new Version(0, 6, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    public static void main(String[] args) {
        try {
            JsonExerciseBookStorage jStorage = new JsonExerciseBookStorage(new File("data/testing.json").toPath());
            ExerciseBook exerciseBook = new ExerciseBook();
            exerciseBook.resetData(jStorage.readExerciseBook().get());
            exerciseBook.addExercise(new Exercise(new Name("Hello"), new Description("Test 1"),
                    new Date("10-10-20"), new Calories("100")));
            exerciseBook.addExercise(new Exercise(new Name("Hello1"), new Description("Test 2"),
                    new Date("10-10-20"), new Calories("100")));
            exerciseBook.addExercise(new Exercise(new Name("Hello2"), new Description("Test 3"),
                    new Date("10-10-30"), new Calories("100")));
            exerciseBook.addExercise(new Exercise(new Name("Hello3"), new Description("Test 4"),
                    new Date("10-10-40"), new Calories("100")));
            exerciseBook.addExercise(new Exercise(new Name("Hello4"), new Description("Test 5"),
                    new Date("10-10-50"), new Calories("100")));
            jStorage.saveExerciseBook(exerciseBook);
            System.out.println(exerciseBook.toString());
        } catch (Exception err) {
            System.out.println("Exceptions");
        }

    }
}
