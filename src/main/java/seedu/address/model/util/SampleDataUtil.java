package seedu.address.model.util;

import seedu.address.model.MeetingBook;
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
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.Paperwork;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.Venue;
import seedu.address.model.meeting.Viewing;
import seedu.address.model.person.Name;
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

/**
 * Contains utility methods for populating Books with sample data.
 */
public class SampleDataUtil {

    // ================= BIDS ==================
    public static Bid[] getSampleBids() {
        return new Bid[] {
            new Bid(new PropertyId("P1"), new BidderId("B1"), new Price(45000)),
            new Bid(new PropertyId("P2"), new BidderId("B2"), new Price(123456)),
            new Bid(new PropertyId("P3"), new BidderId("B3"), new Price(42344)),
            new Bid(new PropertyId("P4"), new BidderId("B4"), new Price(45100)),
            new Bid(new PropertyId("P5"), new BidderId("B5"), new Price(65000)),
            new Bid(new PropertyId("P6"), new BidderId("B6"), new Price(450002)),
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
            new Bidder(new Name("Kor Ming Soon"), new Phone("123456789"), new BidderId("B1")),
            new Bidder(new Name("Harsha"), new Phone("12345777"), new BidderId("B2")),
            new Bidder(new Name("Marcus"), new Phone("47876428"), new BidderId("B3")),
            new Bidder(new Name("Dianne Loh"), new Phone("1256781"), new BidderId("B4")),
            new Bidder(new Name("Gandalf"), new Phone("1203577"), new BidderId("B5")),
            new Bidder(new Name("Amos Yee"), new Phone("4072428"), new BidderId("B6"))
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
            new Seller(new Name("Dianne"), new Phone("12345"), new SellerId("S1")),
            new Seller(new Name("Christopher"), new Phone("123456"), new SellerId("S2")),
            new Seller(new Name("Peter Parker"), new Phone("1234567"), new SellerId("S3")),
            new Seller(new Name("Donald Trump"), new Phone("12345777"), new SellerId("S4")),
            new Seller(new Name("Derp"), new Phone("7897456"), new SellerId("S5")),
            new Seller(new Name("Thanos"), new Phone("123456789"), new SellerId("S6"))
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
            new Property(new PropertyId("P1"), new PropertyName("Sunrise Condo"), new SellerId("S1"),
                new Address("Block 123"), new Price(100), new PropertyType("Condo"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P2"), new PropertyName("Sundown HDB"), new SellerId("S2"),
                new Address("Block 456"), new Price(200), new PropertyType("HDB"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P3"), new PropertyName("Moonshine Mansion"), new SellerId("S3"),
                new Address("Block 789"), new Price(1000), new PropertyType("Mansion"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P4"), new PropertyName("Nuclear Town"), new SellerId("S4"),
                new Address("33 Hiroshima Street"), new Price(112000.15), new PropertyType("Mansion"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P5"), new PropertyName("Underground Dungeon"), new SellerId("S5"),
                new Address("Jeffrey Epstein Hill"), new Price(69.99), new PropertyType("Dungeon"),
                new IsRental("No"), new IsClosedDeal("Active")),
            new Property(new PropertyId("P6"), new PropertyName("Hobbit Hole"), new SellerId("S6"),
                new Address("Hobbit Lane"), new Price(101284684.12), new PropertyType("Hobbit Hole"),
                new IsRental("Yes"), new IsClosedDeal("Active"))

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
                        new MeetingDate("15 OCT 2020"), new Venue("Marina Bay"),
                    new StartTime("12:00"), new EndTime("13:00")),
            new Admin(new BidderId("B5"), new PropertyId("P6"),
                        new MeetingDate("19 OCT 2020"), new Venue("Bedok"),
                    new StartTime("14:00"), new EndTime("15:00")),
            new Viewing(new BidderId("B3"), new PropertyId("P4"),
                        new MeetingDate("21 OCT 2020"), new Venue("Tampines"),
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
