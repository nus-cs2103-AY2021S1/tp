package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.collections.ObservableList;
import seedu.address.model.Drink;
import seedu.address.model.ReadOnlySalesBook;
import seedu.address.model.ReadOnlyTimeSalesBook;
import seedu.address.model.SalesBook;
import seedu.address.model.SalesBookEntry;
import seedu.address.model.SalesRecordEntry;
import seedu.address.model.UniqueSalesBookList;
import seedu.address.model.UniqueSalesRecordList;


public class TimeSalesBook implements ReadOnlyTimeSalesBook {

	private UniqueSalesBookList salesBookList;

	public TimeSalesBook() {
		salesBookList = new UniqueSalesBookList();
	}


	/**
	 * Creates a TimeSalesBook using the record in {@code toBeCopied}.
	 *
	 * @param toBeCopied the TimeSalesBook to be copied from
	 */

	public TimeSalesBook(ReadOnlyTimeSalesBook toBeCopied) {
		this();
		resetData(toBeCopied);
	}

	/**
	 * Resets the existing data of this {@code TimeSalesBook} with {@code newData}.
	 */
	public void resetData(ReadOnlyTimeSalesBook newData) {
		requireNonNull(newData);
		setSalesBookRecord(newData.getSalesBookRecord());
	}

	public void setSalesBookRecord(List<SalesBookEntry> salesBookEntries) {
		requireNonNull(salesBookEntries);
		salesBookList.setSalesBook(salesBookEntries);
	}

	/**
	 * Sets the sales record to the salesbook information which is provided as a Map.
	 * This is used only at initialisation of the salesbook
	 *
	 * @param sales sales information that has been parsed.
	 */
	public void setSalesBookRecord(Map<LocalDate, SalesBook> sales) {
		requireNonNull(sales);
		salesBookList.setSalesBook(sales);
	}

	public UniqueSalesBookList getSaleBookRecord() {
		return salesBookList;
	}

//	/**
//	 * Overwrites existing sales book based on the sales information which is provided as a Map.
//	 * This is used after sales book has been initialised.
//	 *
//	 * @param sales sales information that has been parsed.
//	 */
//	public void overwriteSales(Map<LocalDate, SalesBook> sales) {
//		requireNonNull(sales);
//		HashMap<LocalDate, SalesBook> newRecord = new HashMap<>();
//		// for all the sales items in sales, overwrite them in record
//		for (LocalDate key : sales.keySet()) {
//			Optional<SalesBook> userInput = Optional.ofNullable(sales.get(key));
//			Optional<SalesBook> changedValue = userInput.map(x -> x == 0
//					? record.getSalesEntry(key).getNumberSold()
//					: sales.get(key));
//			newRecord.put(key, changedValue.get());
//		}
//		record.setSalesRecord(newRecord);
//	}

	/**
	 * Checks whether the timesalesbook is empty.
	 *
	 * @return true if the sales record is empty, false otherwise.
	 */
	public boolean isEmptySalesBookList() {
		return salesBookList.isEmpty();
	}


	/**
	 * Returns an unmodifiable view of the list of sales records.
	 * This map will not contain any duplicate drink items.
	 */
	@Override
	public ObservableList<SalesBookEntry> getSalesBookRecord() {
		return salesBookList.asUnmodifiableObservableList();
	}


//	@Override
//	public String toString() {
//		//return record.toString();
//		StringBuilder display = new StringBuilder();
//
//		// record.forEach((k, v) -> display.append(k.getName() + " (" + k + ") : " + v + "\n"));
//		record.forEach(x -> display.append(x + "\n"));
//
//		return display.toString();
//	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof TimeSalesBook // instanceof handles nulls
				&& salesBookList.equals(((TimeSalesBook) other)));
	}

	@Override
	public int hashCode() {
		return salesBookList.hashCode();
	}


}
