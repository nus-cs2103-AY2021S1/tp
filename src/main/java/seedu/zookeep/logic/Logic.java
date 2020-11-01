package seedu.zookeep.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.zookeep.commons.core.GuiSettings;
import seedu.zookeep.logic.commands.CommandResult;
import seedu.zookeep.logic.commands.exceptions.CommandException;
import seedu.zookeep.logic.parser.exceptions.ParseException;
import seedu.zookeep.model.ReadOnlyZooKeepBook;
import seedu.zookeep.model.animal.Animal;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ZooKeepBook.
     *
     * @see seedu.zookeep.model.Model#getZooKeepBook()
     */
    ReadOnlyZooKeepBook getZooKeepBook();

    /** Returns an unmodifiable view of the filtered list of animals */
    ObservableList<Animal> getFilteredAnimalList();

    /**
     * Returns the user prefs' ZooKeepBook file path.
     */
    Path getZooKeepBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
