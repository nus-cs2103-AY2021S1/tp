package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Attraction> PREDICATE_SHOW_ALL_ATTRACTIONS = unused -> true;
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Itinerary> PREDICATE_SHOW_ALL_ITINERARIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' attraction list file path.
     */
    Path getAttractionListFilePath();

    /**
     * Sets the user prefs' attraction list file path.
     */
    void setAttractionListFilePath(Path attractionListFilePath);

    /**
     * Returns the user prefs' itinerary list file path.
     */
    Path getItineraryListFilePath();

    /**
     * Sets the user prefs' itinerary list file path.
     */
    void setItineraryListFilePath(Path itineraryListFilePath);

    //=========== AttractionList ================================================================================

    /**
     * Replaces attraction list data with the data in {@code attractionList}.
     */
    void setAttractionList(ReadOnlyAttractionList attractionList);

    /**
     * Returns the attraction list
     */
    ReadOnlyAttractionList getAttractionList();

    /**
     * Returns true if a attraction with the same identity as {@code attraction} exists in the attraction list.
     */
    boolean hasAttraction(Attraction attraction);

    /**
     * Deletes the given attraction.
     * The attraction must exist in the attraction list.
     */
    void deleteAttraction(Attraction target);

    /**
     * Adds the given attraction.
     * {@code attraction} must not already exist in the attraction list.
     */
    void addAttraction(Attraction attraction);

    /**
     * Replaces the given attraction {@code target} with {@code editedAttraction}.
     * {@code target} must exist in the attraction list.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing attraction in
     * the attraction list.
     */
    void setAttraction(Attraction target, Attraction editedAttraction);

    /**
     * Marks the given attraction {@code target} as visited.
     * {@code target} must exist in the attraction list.
     */
    void markVisitedAttraction(Attraction target);

    /**
     * Returns an unmodifiable view of the filtered attraction list
     */
    ObservableList<Attraction> getFilteredAttractionList();

    /**
     * Updates the filter of the filtered attraction list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAttractionList(Predicate<Attraction> predicate);

    //=========== ItineraryList ================================================================================

    /**
     * Replaces itinerary list data with the data in {@code itineraryList}.
     */
    void setItineraryList(ReadOnlyItineraryList itineraryList);

    /**
     * Returns the itinerary list
     */
    ReadOnlyItineraryList getItineraryList();

    /**
     * Returns true if a itinerary with the same identity as {@code itinerary} exists in the itinerary list.
     */
    boolean hasItinerary(Itinerary itinerary);

    /**
     * Deletes the given itinerary.
     * The itinerary must exist in the itinerary list.
     */
    void deleteItinerary(Itinerary target);

    /**
     * Adds the given itinerary.
     * {@code itinerary} must not already exist in the itinerary list.
     */
    void addItinerary(Itinerary itinerary);

    /**
     * Replaces the given itinerary {@code target} with {@code editedItinerary}.
     * {@code target} must exist in the itinerary list
     * The itinerary identity of {@code editedItinerary} must not be the same as another existing itinerary in
     * the itinerary list.
     */
    void setItinerary(Itinerary target, Itinerary editedItinerary);

    /**
     * Returns an unmodifiable view of the filtered itinerary list
     */
    ObservableList<Itinerary> getFilteredItineraryList();

    /**
     * Updates the filter of the filtered itinerary list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItineraryList(Predicate<Itinerary> predicate);

    /**
     * Sets selected itinerary in ItineraryList.
     *
     * @param itinerary selected itinerary.
     */
    void setCurrentItinerary(Itinerary itinerary);

    /**
     * Returns the current itinerary.
     */
    Itinerary getCurrentItinerary();

    //=========== ItineraryAttractionList =============================================================================

    /**
     * Returns the itinerary attraction list
     */
    ReadOnlyItineraryAttractionList getItineraryAttractionList();

    /**
     * Returns an unmodifiable view of the filtered itinerary attraction list
     */
    ObservableList<ItineraryAttraction> getFilteredItineraryAttractionList();
}
