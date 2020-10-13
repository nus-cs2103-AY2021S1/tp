package seedu.address.storage.sellerstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "selleraddressbook")
class JsonSerializableSellerAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Sellers list contains duplicate person(s).";

    private final List<JsonAdaptedSeller> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableSellerAddressBook(@JsonProperty("persons") List<JsonAdaptedSeller> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableSellerAddressBook(ReadOnlySellerAddressBook source) {
        persons.addAll(source.getSellerList().stream().map(JsonAdaptedSeller::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SellerAddressBook toModelType() throws IllegalValueException {
        SellerAddressBook sellerAddressBook = new SellerAddressBook();
        for (JsonAdaptedSeller jsonAdaptedSeller : persons) {
            Seller seller = jsonAdaptedSeller.toModelType();
            if (sellerAddressBook.hasSeller(seller)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            sellerAddressBook.addSeller(seller);
        }
        return sellerAddressBook;
    }

}
