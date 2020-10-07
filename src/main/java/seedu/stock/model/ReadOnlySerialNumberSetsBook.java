package seedu.stock.model;

import javafx.collections.ObservableList;
import seedu.stock.model.stock.SerialNumberSet;

/**
 * Unmodifiable view of a SerialNumberSetsBook
 */
public interface ReadOnlySerialNumberSetsBook {

    /**
     * Returns an unmodifiable view of the serial number sets list.
     * This list will not contain any duplicate serial number set.
     */
    ObservableList<SerialNumberSet> getSerialNumberSetsList();

}
