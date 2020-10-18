package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.calendar.CalendarMeeting;

/**
 * A utility class containing a list of {@code Seller} objects to be used in tests.
 */
public class TypicalMeeting {

    public static final CalendarMeeting ALICE = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("Alice's Wonderland")
            .withTime("friends").build();
    public static final CalendarMeeting BENSON = new MeetingBuilder().withPropertyId("P2")
            .withTime("owesMoney").withVenue("Beverly Hills").build();
    public static final CalendarMeeting CARL = new MeetingBuilder().withPropertyId("P3")
            .withBidderId("B2").withVenue("Carl Town").build();
    public static final CalendarMeeting DANIEL = new MeetingBuilder().withPropertyId("P4")
            .withBidderId("B3").withTime("friends").withVenue("Dempsy hill").build();
    public static final CalendarMeeting ELLE = new MeetingBuilder().withPropertyId("P5")
            .withBidderId("B4").withVenue("Easton Avenue")
            .build();
    public static final CalendarMeeting FIONA = new MeetingBuilder().withPropertyId("P6")
            .withBidderId("B5").withVenue("Fiona Road")
            .build();
    public static final CalendarMeeting GEORGE = new MeetingBuilder()
            .withPropertyId("P7").withBidderId("B6")
            .withVenue("George Town")
            .build();

    // Manually added - Meeting's details found in {@code CommandTestUtil}
    public static final CalendarMeeting AMY = new MeetingBuilder().withPropertyId("P1").withBidderId("B1")
            .withTime(VALID_TAG_FRIEND).withVenue("S Avenue")
            .build();
    public static final CalendarMeeting BOB = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withTime(VALID_TAG_HUSBAND).withVenue("Serangoon")
            .build();

    /**
     * Returns an {@code MeetingBook} with all the meetings.
     */
    public static MeetingBook getTypicalMeetingAddressBook() {
        MeetingBook ab = new MeetingBook();
        for (CalendarMeeting bidder : getTypicalMeetings()) {
            ab.addMeeting(bidder);
        }
        return ab;
    }

    public static List<CalendarMeeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
