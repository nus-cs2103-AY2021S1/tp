package seedu.address.model;

import java.time.LocalDate;
import java.util.Objects;

public class SalesBookEntry {

	private final LocalDate localdate;
	private final SalesBook salesbook;

	/**
	 * Creates a SalesBookEntry which records salebook of everyday.
	 *
	 * @param localdate date of that day
	 * @param salesbook drinks and their sold number
	 */
	public SalesBookEntry(LocalDate localdate, SalesBook salesbook) {
		this.localdate = localdate;
		this.salesbook = salesbook;
	}

	public LocalDate getLocaldate() {
		return localdate;
	}

	public SalesBook getSalesbook() {
		return salesbook;
	}

	/**
	 * A salesbook entry is the same as another record entry if they have the same localdate
	 *
	 * @param otherEntry the other salesbook entry to compare to
	 * @return true if they have the same localdate, and false otherwise
	 */
	public boolean isSameSalesBook (SalesBookEntry otherEntry) {
		if (otherEntry == this) {
			return true;
		}

		return this.getLocaldate() == otherEntry.getLocaldate();
	}

}
