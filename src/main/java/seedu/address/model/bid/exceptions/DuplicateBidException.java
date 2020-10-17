package seedu.address.model.bid.exceptions;

public class DuplicateBidException extends RuntimeException {

    public DuplicateBidException() {
        super("Operation would result in duplicate bids");
    }
}
