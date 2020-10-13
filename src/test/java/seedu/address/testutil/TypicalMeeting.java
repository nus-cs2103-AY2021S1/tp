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
import seedu.address.model.person.seller.Seller;

/**
 * A utility class containing a list of {@code Seller} objects to be used in tests.
 */
public class TypicalMeeting {

    public static final CalendarMeeting ALICE = new MeetingBuilder().withName("Alice Pauline")
            .withPhone("94351253").withId("S", 1)
            .withTags("friends").build();
    public static final CalendarMeeting BENSON = new MeetingBuilder().withName("Benson Meier")
            .withTags("owesMoney").withId("S", 2).build();
    public static final CalendarMeeting CARL = new MeetingBuilder().withName("Carl Kurz")
            .withPhone("95352563").withPhone("9482442").withId("S", 3).build();
    public static final CalendarMeeting DANIEL = new MeetingBuilder().withName("Daniel Meier")
            .withPhone("87652533").withTags("friends").withId("S", 4).build();
    public static final CalendarMeeting ELLE = new MeetingBuilder().withName("Elle Meyer")
            .withPhone("9482224").withId("S", 5)
            .build();
    public static final CalendarMeeting FIONA = new MeetingBuilder().withName("Fiona Kunz")
            .withPhone("9482427").withId("S", 6)
            .build();
    public static final CalendarMeeting GEORGE = new MeetingBuilder()
            .withName("George Best").withPhone("9482442")
            .withId("S", 7)
            .build();

    // Manually added - Meeting's details found in {@code CommandTestUtil}
    public static final CalendarMeeting AMY = new MeetingBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTags(VALID_TAG_FRIEND).withId("S", 12)
            .build();
    public static final CalendarMeeting BOB = new MeetingBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND).withId("S", 13)
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
