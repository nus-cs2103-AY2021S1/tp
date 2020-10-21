package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.sales.exception.DuplicateSalesBookException;
import seedu.address.model.sales.exception.SalesRecordNotFoundException;
import seedu.address.model.sales.exception.SalesBookNotFoundException;

/**
 * A list of sales book  that enforces uniqueness between its elements and does not allow nulls.
 * A sales record is considered unique by comparing using {@code SalesRecordEntry#isSameRecord(SalesEntryRecord)}. As
 * such, adding and updating of a sales record entry uses {@code SalesRecordEntry#isSameRecord(SalesEntryRecord)} for
 * equality so as to ensure that the sales record entry being added or updated is unique in terms of identity in the
 * UniqueSalesRecordList. However, the removal of a sales record entry uses SalesRecordEntry#equals(Object) so
 * as to ensure that the sales record entry with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueSalesBookList implements Iterable<SalesBookEntry> {
	private final ObservableList<SalesBookEntry> internalList = FXCollections.observableArrayList();
	private final ObservableList<SalesBookEntry> internalUnmodifiableList =
			FXCollections.unmodifiableObservableList(internalList);

	/**
	 * Returns true if the list contains an equivalent record entry of {@Code SalesRecordEntry toCheck}.
	 *
	 * @param toCheck the sales record entry to check for
	 * @return true if the list contains an equivalent record entry
	 */
	public boolean contains(SalesBookEntry toCheck) {
		requireNonNull(toCheck);
		return internalList.stream().anyMatch(toCheck::isSameSalesBook);
	}

	/**
	 * Adds the given {@SalesBookEntry toAdd} to the list.
	 * If there exists a salesbook of the same date in the list, that record will be replaced.
	 *
	 * @param toAdd the SalesRecordEntry to be added
	 */
	public void add(SalesBookEntry toAdd) {
		requireNonNull(toAdd);
		if (contains(toAdd)) {
			// if it exists, then replace it with the new entry
			setSalesBookEntry(toAdd);
		}
		internalList.add(toAdd);
	}

	/**
	 * Sets and replaces the entry that is recording the same salesbook item as {@Code newEntry}.
	 *
	 * @param newEntry the sales record entry to be updated.
	 */
	public void setSalesBookEntry(SalesBookEntry newEntry) {
		requireNonNull(newEntry);
		// find the sales entry with the drink
		int index = indexOf(newEntry.getLocaldate());
		if (index == -1) {
			throw new SalesRecordNotFoundException();
		}

		internalList.set(index, newEntry); // replace with the new entry
	}

	public SalesBookEntry getSalesBook(LocalDate localDate) {
		int index = indexOf(localDate);
		if (index == -1) {
			throw new SalesRecordNotFoundException();
		}

		return internalList.get(index);
	}

	/**
	 * Returns the index of the salesbook which stores the {@Code LocalDate localDate}.
	 * Otherwise, returns -1 if the {@Code localDate} cannot be found.
	 *
	 * @param localDate the localdate item to search for in the record
	 * @return the index of the salesbook entry that stores this localdate
	 */
	private int indexOf(LocalDate localDate) {
		requireNonNull(localDate);
		for (int i = 0; i < internalList.size(); i++) {
			if (internalList.get(i).getLocaldate().equals(localDate)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Removes the salesbook of the same date in the list.
	 * The entry must already exist in the list.
	 *
	 * @param toRemove the salesbook entry to be removed
	 */
	public void remove(SalesBookEntry toRemove) {
		requireNonNull(toRemove);
		if (!internalList.remove(toRemove)) {
			throw new SalesBookNotFoundException();
		}
	}

	public boolean isEmpty() {
		return internalList.isEmpty();
	}

	public int size() {
		return internalList.size();
	}

	/**
	 * Replaces the content of the list with a {@Code UniqueSalesBookList replacement}.
	 *
	 * @param replacement the list to be replaced with
	 */
	public void setSalesBook(UniqueSalesBookList replacement) {
		requireNonNull(replacement);
		internalList.setAll(replacement.internalList);
	}

	/**
	 * Replaces the content of the list with the {@Code sales} as a List.
	 *
	 * @param sales a List containing salesbook entries
	 */
	public void setSalesBook(List<SalesBookEntry> sales) {
		requireAllNonNull(sales);
		if (!salesBookEntriesAreUnique(sales)) {
			throw new DuplicateSalesBookException();
		}

		internalList.setAll(sales);
	}

	/**
	 * Replaces the content of the list with the {@Code sales} as a Map.
	 *
	 * @param sales a Map containing salesbook information and its local date
	 */
	public void setSalesBook(Map<LocalDate, SalesBook> sales) {
		requireNonNull(sales);
		ArrayList<SalesBookEntry> newRecord = new ArrayList<>();
		sales.forEach((k, v) -> newRecord.add(new SalesBookEntry(k, v)));
		internalList.setAll(newRecord);
	}

	/**
	 * Returns true if {@code sales} contains only unique salesbook entries.
	 */
	private boolean salesBookEntriesAreUnique(List<SalesBookEntry> salesbook) {
		for (int i = 0; i < salesbook.size() - 1; i++) {
			for (int j = i + 1; j < salesbook.size(); j++) {
				if (salesbook.get(i).isSameSalesBook(salesbook.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the backing list as an unmodifiable {@code ObservableList}.
	 */
	public ObservableList<SalesBookEntry> asUnmodifiableObservableList() {
		return internalUnmodifiableList;
	}


	/**
	 * Returns an iterator over elements of type {@code SalesBookEntry}.
	 *
	 * @return an Iterator.
	 */
	@Override
	public Iterator<SalesBookEntry> iterator() {
		return internalList.iterator();
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof UniqueSalesBookList // instanceof handles nulls
				&& internalList.equals(((UniqueSalesBookList) other).internalList));
	}

	@Override
	public int hashCode() {
		return internalList.hashCode();
	}

}
