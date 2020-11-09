package seedu.address.testutil.seller;

import seedu.address.model.id.SellerId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.seller.Seller;

/**
 * A utility class to help with building Person objects.
 */
public class SellerBuilder {

    public static final String DEFAULT_NAME = "Ash Ketchum";
    public static final String DEFAULT_PHONE = "85355255";
    public static final SellerId DEFAULT_SELLER_ID = new SellerId(0);

    private Name name;
    private Phone phone;
    private SellerId id;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public SellerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        id = DEFAULT_SELLER_ID;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code sellerToCopy}.
     */
    public SellerBuilder(Seller sellerToCopy) {
        name = sellerToCopy.getName();
        phone = sellerToCopy.getPhone();
        id = (SellerId) sellerToCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code Seller} that we are building.
     */
    public SellerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Seller} that we are building.
     */
    public SellerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code SellerId} of the {@code Seller} that we are building.
     */
    public SellerBuilder withId(int number) {
        this.id = new SellerId(number);
        return this;
    }

    public Seller build() {
        return new Seller(name, phone, id);
    }

}
