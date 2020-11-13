package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * API of logic component for deliverable (should only have one interface for logic, change later)
 */
public interface LogicDeliverable {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the DeliverableBook.
     *
     * @see ModelDeliverable#getDeliverableBook()
     */
    ReadOnlyDeliverableBook getDeliverableBook();

    /** Returns an unmodifiable view of the filtered list of deliverables */
    ObservableList<Deliverable> getFilteredDeliverableList();

    /** Returns the internal list of deliverables */
    ObservableList<Deliverable> getInternalDeliverableList();

    /** Returns the deliverable that is currently in view */
    Deliverable getDeliverableInView();

    /**
     * Returns the user prefs' deliverable book file path.
     */
    Path getDeliverableBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
