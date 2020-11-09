package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME_CM1111_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_CM1111_MEETING;
import static seedu.address.testutil.TypicalModules.CM1111;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2101;
import static seedu.address.testutil.TypicalModules.CS2102;
import static seedu.address.testutil.TypicalModules.CS2104;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICIA;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.MeetingBook;
import seedu.address.model.meeting.Meeting;

public class TypicalMeetings {
    public static final Meeting CS1100_MEETING = new MeetingBuilder()
            .withName("Weekly Meeting")
            .withDate("2020-01-05")
            .withTime("13:00")
            .build();

    public static final Meeting CS1100_NO_MEMBERS = new MeetingBuilder()
            .withName("Weekly Meeting")
            .withDate("2020-01-05")
            .withTime("13:00")
            .withMembers(new HashSet<>(Arrays.asList(ALICE)))
            .build();

    // Edited name from Alice Pauline to Alicia
    public static final Meeting CS1100_EDITED_MEMBER = new MeetingBuilder()
            .withName("Weekly Meeting")
            .withDate("2020-01-05")
            .withTime("13:00")
            .withMembers(new HashSet<>(Arrays.asList(ALICIA)))
            .build();

    public static final Meeting CS2100_MEETING = new MeetingBuilder()
            .withModule(CS2100)
            .withName("CS2100 Meeting")
            .withDate("2020-02-02")
            .withTime("10:00")
            .withMembers(new HashSet<>(Arrays.asList(BENSON)))
            .build();

    public static final Meeting CS2101_MEETING = new MeetingBuilder()
            .withModule(CS2101)
            .withName("CS2101 Meeting")
            .withDate("2020-03-03")
            .withTime("11:00")
            .withMembers(new HashSet<>(Arrays.asList(CARL)))
            .build();

    public static final Meeting CS2102_MEETING = new MeetingBuilder()
            .withModule(CS2102)
            .withName("CS2102 Meeting")
            .withDate("2020-04-04")
            .withTime("12:00")
            .withMembers(new HashSet<>(Arrays.asList(DANIEL)))
            .build();

    public static final Meeting CS2102_MEETING_DUPLICATE_NAME = new MeetingBuilder()
            .withModule(CS2102)
            .withName("CS2102 Meeting 2")
            .withDate("2020-04-04")
            .withTime("12:00")
            .withMembers(new HashSet<>(Arrays.asList(DANIEL)))
            .build();

    public static final Meeting CS2104_MEETING = new MeetingBuilder()
            .withModule(CS2104)
            .withName("CS2104 Meeting")
            .withDate("2020-05-06")
            .withTime("14:00")
            .withMembers(new HashSet<>(Arrays.asList(ELLE)))
            .build();

    public static final Meeting CM1111_MEETING = new MeetingBuilder()
            .withModule(CM1111)
            .withName(VALID_MEETING_NAME_CM1111_MEETING)
            .withDate(VALID_DATE_CM1111_MEETING)
            .withTime(VALID_TIME_CM1111_MEETING)
            .withMembers(new HashSet<>(Arrays.asList(AMY)))
            .build();

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

    // meeting has duplicate name
    public static MeetingBook getTypicalMeetingBookWithDuplicateName() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetingsWithDuplicateName()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(CS2100_MEETING, CS2101_MEETING, CS2102_MEETING));
    }

    public static List<Meeting> getTypicalMeetingsWithMembers() {
        return new ArrayList<>(Arrays.asList(CS1100_NO_MEMBERS));
    }

    public static List<Meeting> getTypicalMeetingsWithEditedMember() {
        return new ArrayList<>(Arrays.asList(CS1100_EDITED_MEMBER));
    }

    public static List<Meeting> getTypicalMeetingsWithDuplicateName() {
        return new ArrayList<>(
                Arrays.asList(CS2100_MEETING, CS2101_MEETING, CS2102_MEETING,
                        CS2102_MEETING_DUPLICATE_NAME));
    }
}
