package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.meeting.Admin;
import seedu.address.model.meeting.Date;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Paperwork;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.Venue;
import seedu.address.model.meeting.Viewing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
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

    // ================= BIDS ==================
    public static Bid[] getSampleBids() {
        return new Bid[] {
            new Bid(new PropertyId("P01"), new BidderId("B01"), new Price(45000)),
            new Bid(new PropertyId("P02"), new BidderId("B23"), new Price(123456)),
            new Bid(new PropertyId("P31"), new BidderId("B11"), new Price(42344)),
            new Bid(new PropertyId("P01"), new BidderId("B02"), new Price(45100)),
            new Bid(new PropertyId("P01"), new BidderId("B45"), new Price(65000)),
            new Bid(new PropertyId("P12"), new BidderId("B22"), new Price(450002)),
        };
    }

    public static ReadOnlyBidBook getSampleBidBook() {
        BidBook sampleBb = new BidBook();
        for (Bid sampleBid : getSampleBids()) {
            sampleBb.addBid(sampleBid);
        }
        return sampleBb;
    }

    // ================= BIDDERS ==================
    public static Bidder[] getSampleBidders() {
        return new Bidder[] {
            new Bidder(new Name("Kor Ming Soon"), new Phone("125678"), new HashSet<>(), new BidderId(1))
                    .setBidderTag(),
            new Bidder(new Name("Harsha"), new Phone("12345777"), new HashSet<>(), new BidderId(2))
                    .setBidderTag(),
            new Bidder(new Name("Marcus"), new Phone("47876428"), new HashSet<>(), new BidderId(3))
                .setBidderTag()
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
            new Seller(new Name("Dianne"), new Phone("7897456"), new HashSet<>(), new SellerId(1))
                .setSellerTag(),
            new Seller(new Name("Christopher"), new Phone("12345777"), new HashSet<>(), new SellerId(2))
                .setSellerTag(),
        };
    }

    public static ReadOnlySellerAddressBook getSampleSellerAddressBook() {
        SellerAddressBook sampleSellerAb = new SellerAddressBook();
        for (Seller sampleSeller : getSampleSellers()) {
            sampleSellerAb.addSeller(sampleSeller);
        }
        return sampleSellerAb;
    }

    // ================= PROPERTY ==================
    public static Property[] getSampleProperties() {
        return new Property[]{
            new Property(new PropertyId("P1"), new PropertyName("Sunrise Condo"), new SellerId("S123"),
                new Address("Block 123"), new Price(100), new PropertyType("Condo"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P2"), new PropertyName("Sundown HDB"), new SellerId("S567"),
                new Address("Block 456"), new Price(200), new PropertyType("HDB"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P3"), new PropertyName("Moonshine Mansion"), new SellerId("S789"),
                new Address("Block 789"), new Price(1000), new PropertyType("Mansion"),
                new IsRental("No"), new IsClosedDeal("Active"))
        };
    }

    public static ReadOnlyPropertyBook getSamplePropertyBook() {
        PropertyBook samplePb = new PropertyBook();
        for (Property sampleProperty : getSampleProperties()) {
            samplePb.addProperty(sampleProperty);
        }
        return samplePb;
    }

    // ================= MEETING ==================
    public static Meeting[] getSampleMeetings() {
        return new Meeting[] {
            new Paperwork(new BidderId("B1"), new PropertyId("P2"),
                        new Date("15 OCT 2020"), new Venue("Marina Bay"),
                    new StartTime("12:00"), new EndTime("13:00")),
            new Admin(new BidderId("B5"), new PropertyId("P6"),
                        new Date("19 OCT 2020"), new Venue("Bedok"),
                    new StartTime("14:00"), new EndTime("15:00")),
            new Viewing(new BidderId("B11"), new PropertyId("P8"),
                        new Date("21 OCT 2020"), new Venue("Tampines"),
                    new StartTime("16:00"), new EndTime("17:00"))
        };
    }

    public static ReadOnlyMeetingBook getSampleMeetingBook() {
        MeetingBook sampleMeetingAb = new MeetingBook();
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleMeetingAb.addMeeting(sampleMeeting);
        }
        return sampleMeetingAb;
    }
}
