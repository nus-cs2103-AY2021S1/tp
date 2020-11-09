package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;

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

    //=========== Attraction List ================================================================================

    /**
     * Returns the AttractionList.
     *
     * @see seedu.address.model.Model#getAttractionList()
     */
    ReadOnlyAttractionList getAttractionList();

    /** Returns an unmodifiable view of the filtered list of attractions */
    ObservableList<Attraction> getFilteredAttractionList();

    /**
     * Returns the user prefs' AttractionList file path.
     */
    Path getAttractionListFilePath();

    //=========== Itinerary List ================================================================================

    /**
     * Returns the ItineraryList.
     *
     * @see seedu.address.model.Model#getItineraryList()
     */
    ReadOnlyItineraryList getItineraryList();

    /** Returns an unmodifiable view of the filtered list of itineraries */
    ObservableList<Itinerary> getFilteredItineraryList();

    /**
     * Returns the user prefs' ItineraryList file path.
     */
    Path getItineraryListFilePath();

    //=========== Itinerary Attraction List ===========================================================================

    /**
     * Returns the ItineraryAttractionList.
     *
     * @see seedu.address.model.Model#getItineraryAttractionList()
     */
    ReadOnlyItineraryAttractionList getItineraryAttractionList();

    /** Returns an unmodifiable view of the filtered list of itinerary attractions in the current selected itinerary */
    ObservableList<ItineraryAttraction> getFilteredItineraryAttractionList();

    //=========== GUI Settings ==================================================================================

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
