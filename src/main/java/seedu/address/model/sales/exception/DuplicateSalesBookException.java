package seedu.address.model.sales.exception;

public class DuplicateSalesBookException extends RuntimeException {
	public DuplicateSalesBookException() {
		super("operation would result in duplicate salesbook entry");
	}
}
