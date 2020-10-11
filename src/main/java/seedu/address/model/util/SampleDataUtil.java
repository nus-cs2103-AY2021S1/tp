package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.id.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    // ================= BIDDERS ==================
    public static Bidder[] getSampleBidders() {
        return new Bidder[] {
            new Bidder(new Name("Kor Ming Soon"), new Phone("125678"), null, new Id("B", 1)),
            new Bidder(new Name("Harsha"), new Phone("12345777"), null, new Id("B", 2)),
            new Bidder(new Name("Marcus"), new Phone("47876428"), null, new Id("B", 3))
        };
    }

    public static ReadOnlyBidderAddressBook getSampleBidderAddressBook() {
        BidderAddressBook sampleBidderAb = new BidderAddressBook();
        for (Bidder sampleBidder : getSampleBidders()) {
            sampleBidderAb.addBidder(sampleBidder);
        }
        return sampleBidderAb;
    }

    // ================= SELLERS ==================
    public static Seller[] getSampleSellers() {
        return new Seller[] {
            new Seller(new Name("Dianne"), new Phone("7897456"), null, new Id("S", 1)),
            new Seller(new Name("Christopher"), new Phone("12345777"), null, new Id("S", 2)),
        };
    }

    public static ReadOnlySellerAddressBook getSampleSellerAddressBook() {
        SellerAddressBook sampleSellerAb = new SellerAddressBook();
        for (Seller sampleSeller : getSampleSellers()) {
            sampleSellerAb.addSeller(sampleSeller);
        }
        return sampleSellerAb;
    }

}
