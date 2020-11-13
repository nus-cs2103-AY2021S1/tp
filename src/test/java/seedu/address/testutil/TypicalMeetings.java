package seedu.address.testutil;

import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_CONTACTS_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_CONTACTS_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_LOCATION_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_LOCATION_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TO_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TO_B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.util.TimeEventComparator;

public class TypicalMeetings {

    public static final String TITLE_C = "Final";
    public static final String DESCRIPTION_C = "Discussing final features";
    public static final String FROM_C = "12-12-2020 08:00";
    public static final String TO_C = "10:00";
    public static final String CONTACTS_C = "Alice   Bob, Sarah  Goldman Jane, Jane Salmon";
    public static final String LOCATION_C = "Somewhere";

    public static final String TITLE_D = "Mid v1.2";
    public static final String DESCRIPTION_D = "Discuss features";
    public static final String FROM_D = "12-12-2020 08:00";
    public static final String TO_D = "09:00";
    public static final String CONTACTS_D = "Alice, Sarah, Jane Salmon";
    public static final String LOCATION_D = "Somewhere";

    public static final String TITLE_E = "Mid v1.3";
    public static final String FROM_E = "12-12-2020 08:00";
    public static final String TO_E = "09:00";

    public static final Meeting MEETING_A = new MeetingBuilder().withTitle(VALID_TITLE_A)
            .withDescription(VALID_DESCRIPTION_A)
            .withFrom(VALID_FROM_A)
            .withTo(VALID_TO_A)
            .withContacts(VALID_CONTACTS_A)
            .withLocation(VALID_LOCATION_A).build();

    public static final Meeting MEETING_B = new MeetingBuilder().withTitle(VALID_TITLE_B)
            .withDescription(VALID_DESCRIPTION_B)
            .withFrom(VALID_FROM_B)
            .withTo(VALID_TO_B)
            .withContacts(VALID_CONTACTS_B)
            .withLocation(VALID_LOCATION_B).build();

    public static final Meeting MEETING_C = new MeetingBuilder().withTitle(TITLE_C)
            .withDescription(DESCRIPTION_C)
            .withFrom(FROM_C)
            .withTo(TO_C)
            .withContacts(CONTACTS_C)
            .withLocation(LOCATION_C).build();

    public static final Meeting MEETING_D = new MeetingBuilder().withTitle(TITLE_D)
            .withDescription(DESCRIPTION_D)
            .withFrom(FROM_D)
            .withTo(TO_D)
            .withContacts(CONTACTS_D)
            .withLocation(LOCATION_D).build();

    public static final Meeting MEETING_E = new MeetingBuilder().withTitle(TITLE_E)
            .withDescription()
            .withFrom(FROM_E)
            .withTo(TO_E)
            .withContacts()
            .withLocation().build();

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static MeetingBook getTypicalMeetingBook() {
        MeetingBook ab = new MeetingBook();
        for (Meeting meeting : getTypicalMeeting()) {
            ab.addMeeting(meeting);
        }
        return ab;
    }

    public static List<Meeting> getTypicalMeeting() {
        ArrayList<Meeting> meetingArrayList =
                new ArrayList<>(Arrays.asList(MEETING_A, MEETING_B, MEETING_C, MEETING_D, MEETING_E));
        meetingArrayList.sort(new TimeEventComparator());
        return meetingArrayList;
    }
}
