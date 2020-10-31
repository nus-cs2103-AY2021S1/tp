package seedu.address.testutil;

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
            .withBidderId("B1").withVenue("33 Pasir Ris Drive").withDate("03-08-2021")
            .withStartTime("12:30").withEndTime("15:30").build();
    public static final Meeting MEETING02 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("15-08-2021").withVenue("Beverly Hills").withStartTime("13:30").withEndTime("16:30").build();

    // Manually added - Meeting's details found in {@code CommandTestUtil}
    public static final Meeting AMY = new MeetingBuilder().withPropertyId("P1").withBidderId("B1")
            .withDate(VALID_TIME_01).withVenue("S Avenue")
            .build();
    public static final Meeting BOB = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate(VALID_TIME_02).withVenue("Serangoon")
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
        return new ArrayList<>(Arrays.asList(MEETING01, MEETING02));
    }
}
