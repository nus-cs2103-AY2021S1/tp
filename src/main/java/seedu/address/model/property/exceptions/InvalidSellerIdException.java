package seedu.address.model.property.exceptions;

public class InvalidSellerIdException extends RuntimeException {

    public InvalidSellerIdException() {
        super("Seller id does not exist in the seller address book");
    }

}
