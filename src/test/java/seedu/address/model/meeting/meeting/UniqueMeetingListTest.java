package seedu.address.model.meeting.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.MEETING_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.meeting.exceptions.DuplicateMeetingException;
import seedu.address.model.meeting.meeting.exceptions.MeetingNotFoundException;
import seedu.address.testutil.MeetingBuilder;


class UniqueMeetingListTest {

    private final UniqueMeetingList uniqueMeetingList = new UniqueMeetingList();

    @Test
    public void contains_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.contains(null));
    }

    @Test
    public void contains_meetingNotInList_returnsFalse() {
        assertFalse(uniqueMeetingList.contains(MEETING_A));
    }

    @Test
    public void contains_meetingInList_returnsTrue() {
        uniqueMeetingList.add(MEETING_A);
        assertTrue(uniqueMeetingList.contains(MEETING_A));
    }

    // TODO:
    //    @Test
    //    public void contains_MeetingWithSameIdentityFieldsInList_returnsTrue() {
    //        uniqueMeetingList.add(MEETING_A);
    //        Meeting editedMeetingA = new MeetingBuilder(MEETING_A).withDescription(VALID_DESCRIPTION_B)
    //                .build();
    //        assertTrue(uniqueMeetingList.contains(editedMeetingA));
    //    }

    @Test
    public void add_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.add(null));
    }

    @Test
    public void add_duplicateMeeting_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(MEETING_A);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.add(MEETING_A));
    }

    @Test
    public void setMeeting_nullTargetMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(null, MEETING_A));
    }

    @Test
    public void setMeeting_nullEditedMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeeting(MEETING_A, null));
    }

    @Test
    public void setMeeting_targetMeetingNotInList_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.setMeeting(MEETING_A, MEETING_A));
    }

    @Test
    public void setMeeting_editedMeetingIsSameMeeting_success() {
        uniqueMeetingList.add(MEETING_A);
        uniqueMeetingList.setMeeting(MEETING_A, MEETING_A);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_A);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasSameIdentity_success() {
        uniqueMeetingList.add(MEETING_A);
        Meeting editedMeetingA = new MeetingBuilder(MEETING_A).withDescription(VALID_DESCRIPTION_B)
                .build();
        uniqueMeetingList.setMeeting(MEETING_A, editedMeetingA);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(editedMeetingA);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasDifferentIdentity_success() {
        uniqueMeetingList.add(MEETING_A);
        uniqueMeetingList.setMeeting(MEETING_A, MEETING_B);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_B);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeeting_editedMeetingHasNonUniqueIdentity_throwsDuplicateMeetingException() {
        uniqueMeetingList.add(MEETING_A);
        uniqueMeetingList.add(MEETING_B);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeeting(MEETING_A, MEETING_B));
    }

    @Test
    public void remove_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.remove(null));
    }

    @Test
    public void remove_meetingDoesNotExist_throwsMeetingNotFoundException() {
        assertThrows(MeetingNotFoundException.class, () -> uniqueMeetingList.remove(MEETING_A));
    }

    @Test
    public void remove_existingMeeting_removesMeeting() {
        uniqueMeetingList.add(MEETING_A);
        uniqueMeetingList.remove(MEETING_A);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullUniqueMeetingList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((UniqueMeetingList) null));
    }

    @Test
    public void setMeetings_uniqueMeetingList_replacesOwnListWithProvidedUniqueMeetingList() {
        uniqueMeetingList.add(MEETING_A);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_B);
        uniqueMeetingList.setMeetings(expectedUniqueMeetingList);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueMeetingList.setMeetings((List<Meeting>) null));
    }

    @Test
    public void setMeetings_list_replacesOwnListWithProvidedList() {
        uniqueMeetingList.add(MEETING_A);
        List<Meeting> meetingList = Collections.singletonList(MEETING_B);
        uniqueMeetingList.setMeetings(meetingList);
        UniqueMeetingList expectedUniqueMeetingList = new UniqueMeetingList();
        expectedUniqueMeetingList.add(MEETING_B);
        assertEquals(expectedUniqueMeetingList, uniqueMeetingList);
    }

    @Test
    public void setMeetings_listWithDuplicateMeetings_throwsDuplicateMeetingException() {
        List<Meeting> listWithDuplicateMeetings = Arrays.asList(MEETING_A, MEETING_A);
        assertThrows(DuplicateMeetingException.class, () -> uniqueMeetingList.setMeetings(listWithDuplicateMeetings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueMeetingList.asUnmodifiableObservableList().remove(0));
    }


}
