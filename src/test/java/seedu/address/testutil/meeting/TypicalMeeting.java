package seedu.address.testutil.meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

//@@author Christopher-LM
/**
 * A utility class containing a list of {@code Seller} objects to be used in tests.
 * @author christophermervyn
 */
public class TypicalMeeting {

    public static final Meeting ADMINMEETING01 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("39 Pasiris Ris Drive").withDate("03-08-2021")
            .withStartTime("12:30").withEndTime("15:30").buildAdmin();
    public static final Meeting ADMINMEETING02 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("15-08-2021").withVenue("Beverly Hills").withStartTime("15:30")
                    .withEndTime("16:30").buildAdmin();
    public static final Meeting VIEWINGMEETING03 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("bedok").withDate("17-08-2021")
            .withStartTime("16:30").withEndTime("17:30").buildViewing();
    public static final Meeting VIEWINGMEETING04 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("20-08-2021").withVenue("tampines").withStartTime("17:30")
                    .withEndTime("18:30").buildViewing();
    public static final Meeting PAPERWORKMEETING05 = new MeetingBuilder().withPropertyId("P1")
            .withBidderId("B1").withVenue("simei").withDate("21-08-2021")
            .withStartTime("18:30").withEndTime("19:30").buildPaperwork();
    public static final Meeting PAPERWORKMEETING06 = new MeetingBuilder().withPropertyId("P2").withBidderId("B2")
            .withDate("23-08-2021").withVenue("london").withStartTime("20:30")
                    .withEndTime("21:30").buildPaperwork();

    /**
     * Returns an {@code MeetingBook} with all the meetings.
     */
    public static MeetingBook getTypicalMeetingAddressBook() {
        MeetingBook ab = new MeetingBook();
        for (Meeting meeting : getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(ADMINMEETING01, ADMINMEETING02, VIEWINGMEETING03,
                VIEWINGMEETING04, PAPERWORKMEETING05, PAPERWORKMEETING06));
    }
}
