package seedu.address.model;

import javafx.collections.ObservableList;

public interface ReadOnlyTimeSalesBook {
	/**
	 * Returns an unmodifiable view of the list of salesbook records.
	 * This map will not contain any duplicate salesbook.
	 */
	ObservableList<SalesBookEntry> getSalesBookRecord();
}
