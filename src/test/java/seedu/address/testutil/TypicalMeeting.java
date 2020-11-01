package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Seller} objects to be used in tests.
 */
public class TypicalMeeting {

    public static final Meeting ADMINMEETING01 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("39 Pasiris Ris Drive").withDate("03-08-2021")
            .withStartTime("12:30").withEndTime("15:30").buildAdmin();
    public static final Meeting ADMINMEETING02 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("15-08-2021").withVenue("Beverly Hills").withStartTime("13:30")
                    .withEndTime("16:30").buildAdmin();
    public static final Meeting VIEWINGMEETING03 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("37 Pasirs Ris Drive").withDate("03-08-2021")
            .withStartTime("12:30").withEndTime("15:30").buildViewing();
    public static final Meeting VIEWINGMEETING04 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("15-08-2021").withVenue("george Hills").withStartTime("13:30")
                    .withEndTime("16:30").buildViewing();
    public static final Meeting PAPERWORKMEETING05 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("34 Pasir Ris Drive").withDate("03-08-2021")
            .withStartTime("12:30").withEndTime("15:30").buildPaperwork();
    public static final Meeting PAPERWORKMEETING06 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("15-08-2021").withVenue("waterwraft Hills").withStartTime("13:30")
                    .withEndTime("16:30").buildPaperwork();

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
        return new ArrayList<>(Arrays.asList(ADMINMEETING01, ADMINMEETING02, VIEWINGMEETING03,
                VIEWINGMEETING04, PAPERWORKMEETING05, PAPERWORKMEETING06));
    }
}
