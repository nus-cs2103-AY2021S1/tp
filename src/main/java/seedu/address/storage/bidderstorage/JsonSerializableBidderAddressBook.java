package seedu.address.storage.bidderstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.person.bidder.Bidder;

/**
 * An Immutable BidderAddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializableBidderAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Bidders list contains duplicate bidder(s).";

    private final List<JsonAdaptedBidder> bidders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableBidderAddressBook} with the given bidders.
     */
    @JsonCreator
    public JsonSerializableBidderAddressBook(@JsonProperty("bidders") List<JsonAdaptedBidder> bidders) {
        this.bidders.addAll(bidders);
    }

    /**
     * Converts a given {@code ReadOnlyBidderAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableBidderAddressBook}.
     */
    public JsonSerializableBidderAddressBook(ReadOnlyBidderAddressBook source) {
        bidders.addAll(source.getBidderList().stream().map(JsonAdaptedBidder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code BidderAddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BidderAddressBook toModelType() throws IllegalValueException {
        BidderAddressBook bidderAddressBook = new BidderAddressBook();
        for (JsonAdaptedBidder jsonAdaptedBidder : bidders) {
            Bidder bidder = jsonAdaptedBidder.toModelType();
            if (bidderAddressBook.hasBidder(bidder)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            bidderAddressBook.addBidder(bidder);
        }
        return bidderAddressBook;
    }

}
