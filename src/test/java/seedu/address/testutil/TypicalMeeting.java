package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_01;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Seller} objects to be used in tests.
 */
public class TypicalMeeting {

    public static final Meeting MEETING01 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("Alice's Wonderland")
            .withTime("friends").build();
    public static final Meeting MEETING02 = new MeetingBuilder().withPropertyId("P2")
            .withTime("owesMoney").withVenue("Beverly Hills").build();
    public static final Meeting MEETING03 = new MeetingBuilder().withPropertyId("P3")
            .withBidderId("B2").withVenue("Carl Town").build();
    public static final Meeting MEETING04 = new MeetingBuilder().withPropertyId("P4")
            .withBidderId("B3").withTime("friends").withVenue("Dempsy hill").build();
    public static final Meeting MEETING05 = new MeetingBuilder().withPropertyId("P5")
            .withBidderId("B4").withVenue("Easton Avenue")
            .build();
    public static final Meeting MEETING06 = new MeetingBuilder().withPropertyId("P6")
            .withBidderId("B5").withVenue("Fiona Road")
            .build();
    public static final Meeting MEETING07 = new MeetingBuilder()
            .withPropertyId("P7").withBidderId("B6")
            .withVenue("George Town")
            .build();

    // Manually added - Meeting's details found in {@code CommandTestUtil}
    public static final Meeting AMY = new MeetingBuilder().withPropertyId("P1").withBidderId("B1")
            .withTime(VALID_TIME_01).withVenue("S Avenue")
            .build();
    public static final Meeting BOB = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withTime(VALID_TIME_02).withVenue("Serangoon")
            .build();

    /**
     * Returns an {@code MeetingBook} with all the meetings.
     */
    public static MeetingBook getTypicalMeetingAddressBook() {
        MeetingBook ab = new MeetingBook();
        for (Meeting bidder : getTypicalMeetings()) {
            ab.addMeeting(bidder);
        }
        return ab;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(MEETING01, MEETING02, MEETING03,
                MEETING04, MEETING05, MEETING06, MEETING07));
    }
}
