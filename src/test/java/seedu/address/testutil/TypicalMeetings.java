package seedu.address.testutil;

import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICIA;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;

public class TypicalMeetings {

    public static final Meeting CS2103_NO_MEMBERS = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00")
            .withMembers(new HashSet<>(Arrays.asList(ALICE)))
            .build();

    public static final Meeting CS2103 = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00")
            .build();

    // Edited name from Alice Pauline to Alicia
    public static final Meeting CS2103_EDITED_MEMBER = new MeetingBuilder().withName("CS2103")
            .withDate("2020-10-07")
            .withTime("10:00")
            .withMembers(new HashSet<>(Arrays.asList(ALICIA)))
            .build();

    public static final Meeting CS2100_MEETING = new MeetingBuilder().withModule(CS2100)
            .withName("CS2100 Meeting")
            .withDate("2020-11-20")
            .withTime("17:30")
            .withMembers(new HashSet<>(Arrays.asList(BENSON)))
            .build();

    public static final Meeting CS2101_MEETING = new MeetingBuilder().withModule(CS2101)
            .withName("CS2101 Meeting")
            .withDate("2020-12-07")
            .withTime("12:00")
            .withMembers(new HashSet<>(Arrays.asList(CARL)))
            .build();

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
    // meeting has no members
    public static MeetingBook getTypicalMeetingBook() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetings()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    // meeting has one member
    public static MeetingBook getTypicalMeetingBookWithMember() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetingsWithMembers()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    // meeting has edited member
    public static MeetingBook getTypicalMeetingBookWithEditedMember() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetingsWithEditedMember()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2103, CS2100_MEETING, CS2101_MEETING));
    }
    public static List<Meeting> getTypicalMeetingsWithMembers() {
        return new ArrayList<>(Arrays.asList(CS2103_NO_MEMBERS));
    }
    public static List<Meeting> getTypicalMeetingsWithEditedMember() {
        return new ArrayList<>(Arrays.asList(CS2103_EDITED_MEMBER));
    }
}
