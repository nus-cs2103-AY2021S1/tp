package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.BidBook;
import seedu.address.model.ReadOnlyBidBook;
import seedu.address.model.bid.Bid;
import seedu.address.model.person.Person;

/**
 * An Immutable BidBook that is serializable to JSON format.
 */
@JsonRootName(value = "bidbook")
class JsonSerializableBidBook {

    public static final String MESSAGE_DUPLICATE_BID = "Bid list contains duplicate bids.";

    private final List<JsonAdaptedBid> bids = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableBidBook(@JsonProperty("bids") List<JsonAdaptedBid> bids) {
        this.bids.addAll(bids);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableBidBook(ReadOnlyBidBook source) {
        bids.addAll(source.getBidList().stream().map(JsonAdaptedBid::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public BidBook toModelType() throws IllegalValueException {
        BidBook bidBook = new BidBook();
        for (JsonAdaptedBid jsonAdaptedBid : bids) {
           // Person person = jsonAdaptedPerson.toModelType();
            Bid bid = jsonAdaptedBid.toModelType();
            if (bidBook.hasBid(bid)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_BID);
            }
            bidBook.addBid(bid);
        }
        return bidBook;
    }

}
