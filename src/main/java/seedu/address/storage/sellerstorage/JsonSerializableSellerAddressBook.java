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
 * An Immutable SellerAddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "selleraddressbook")
public class JsonSerializableSellerAddressBook {

    public static final String MESSAGE_DUPLICATE_SELLER = "Sellers list contains duplicate seller(s).";

    private final List<JsonAdaptedSeller> sellers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given seller.
     */
    @JsonCreator
    public JsonSerializableSellerAddressBook(@JsonProperty("sellers") List<JsonAdaptedSeller> sellers) {
        this.sellers.addAll(sellers);
    }

    /**
     * Converts a given {@code ReadOnlySellerAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSellerAddressBook}.
     */
    public JsonSerializableSellerAddressBook(ReadOnlySellerAddressBook source) {
        sellers.addAll(source.getSellerList().stream().map(JsonAdaptedSeller::new).collect(Collectors.toList()));
    }

    /**
     * Converts this seller address book into the model's {@code SellerAddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SellerAddressBook toModelType() throws IllegalValueException {
        SellerAddressBook sellerAddressBook = new SellerAddressBook();
        for (JsonAdaptedSeller jsonAdaptedSeller : sellers) {
            Seller seller = jsonAdaptedSeller.toModelType();
            if (sellerAddressBook.hasSeller(seller)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SELLER);
            }
            sellerAddressBook.addSeller(seller);
        }
        return sellerAddressBook;
    }

}
