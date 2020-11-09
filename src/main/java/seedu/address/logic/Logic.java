package seedu.address.logic;

import java.nio.file.Path;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyProjact;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
     * Returns the Projact.
     *
     * @see seedu.address.model.Model#getProjact()
     */
    ReadOnlyProjact getProjact();

    public List<Person> findContactsByTag(Tag target);

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of all persons*/
    ObservableList<Person> getAllFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of tags*/
    ObservableList<Tag> getFilteredTagList();

    /** Returns an unmodifiable view of the filtered list of all tags*/
    ObservableList<Tag> getAllFilteredTagList();

    /** Returns an unmodifiable view of the filtered list of tags*/
    ObservableList<Tag> getSortedTagList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getSortedPersonList();

    /**
     * Returns the user prefs' Projact file path.
     */
    Path getProjactFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
