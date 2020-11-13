package seedu.address.logic;

import java.nio.file.Path;
import java.util.Optional;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Renderable;
import seedu.address.model.budget.Threshold;

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

    /** Returns an unmodifiable view of the filtered list of budgets */
    ObservableList<Renderable> getFilteredRenderableList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getNusaveFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    BooleanProperty getIsBudgetPageProp();

    StringProperty getTotalExpenditureStringProp();

    StringProperty getThresholdStringProp();

    String getPageTitle();

    String getTotalExpenditureValue();

    Optional<Threshold> getThreshold();

    boolean isBudgetPage();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
