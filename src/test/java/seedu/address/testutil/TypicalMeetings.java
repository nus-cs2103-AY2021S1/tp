package seedu.address.testutil;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalMeetings {

    public static final Meeting CS2103 = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00").build();

    /*
    // Manually added
    public static final Meeting A = new MeetingBuilder().withName("Meeting A").withDate("8482424")
            .withEmail("stefan@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMeetings() {} // prevents instantiation
     */
    public static MeetingBook getTypicalMeetingBook() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetings()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2103));
    }
}
