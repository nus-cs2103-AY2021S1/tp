package seedu.address.model.deliverable;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.deliverable.deliverable.Deliverable;

/**
 * Api of Model component of Deliverable
 */
public interface ModelDeliverable {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Deliverable> PREDICATE_SHOW_ALL_DELIVERABLES = unused -> true;

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
     * Returns the user prefs' deliverable book file path.
     */
    Path getDeliverableBookFilePath();

    /**
     * Sets the user prefs' deliverable book file path.
     */
    void setDeliverableBookFilePath(Path deliverableBookFilePath);

    /**
     * Replaces deliverable book data with the data in {@code deliverableBook}.
     */
    void setDeliverableBook(ReadOnlyDeliverableBook deliverableBook);

    /** Returns the DeliverableBook */
    ReadOnlyDeliverableBook getDeliverableBook();

    /**
     * Returns true if a deliverable with the same identity as {@code deliverable} exists in the deliverable book.
     */
    boolean hasDeliverable(Deliverable deliverable);

    /**
     * Deletes the given deliverable.
     * The deliverable must exist in the deliverable book.
     */
    void deleteDeliverable(Deliverable target);

    /**
     * Adds the given deliverable.
     * {@code deliverable} must not already exist in the deliverable book.
     */
    void addDeliverable(Deliverable deliverable);

    /**
     * Replaces the given deliverable {@code target} with {@code editedDeliverable}.
     * {@code target} must exist in the deliverable book.
     * The deliverable identity of {@code editedDeliverable} must not be the same as
     * another existing deliverable in the deliverable book.
     */
    void setDeliverable(Deliverable target, Deliverable editedDeliverable);

    /**
     * Updates the completion status of given deliverable {@code target}
     * by replacing it with {@code updatedDeliverable}.
     * {@code target} must exist in the deliverable book.
     */
    void updateDeliverableStatus(Deliverable target, Deliverable updatedDeliverable);

    /**
     * Obtains the deliverable currently being viewed.
     */
    Deliverable getDeliverableInView();

    void setDeliverableInView(Deliverable deliverableInView);

    /** Returns an unmodifiable view of the filtered deliverable list */
    ObservableList<Deliverable> getFilteredDeliverableList();

    /**
     * Updates the filter of the filtered deliverable list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDeliverableList(Predicate<Deliverable> predicate);

    /** Returns the internal list of deliverables */
    ObservableList<Deliverable> getInternalDeliverableList();
}
