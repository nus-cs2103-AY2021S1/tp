package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalEvents;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalPersons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import jfxtras.icalendarfx.components.VEvent;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class SchedulerTest {
    private final Scheduler scheduler = new Scheduler();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduler.getEventsList());
        assertEquals(Collections.emptyList(), scheduler.getVEvents());
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Scheduler(null));
    }

    @Test
    public void setVEvents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.setVEvents(null));
    }

    @Test
    public void setVEvents_validArguments_success() {
        List<VEvent> validVEventList = new ArrayList<>();
        validVEventList.add(new VEvent());
        scheduler.setVEvents(validVEventList);
        assertEquals(scheduler.getVEvents(), validVEventList);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.resetData(null));
    }

    @Test
    public void resetData_withValidEventList_replacesData() {
        List<Event> eventList = new ArrayList<>(getTypicalEvents());
        scheduler.resetData(eventList);
        assertEquals(eventList, scheduler.getEventsList());
    }

    @Test
    public void addEvent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.addEvent(null));
    }

    @Test
    public void addEvent_validEvent_success() {
        List<Event> validEventList = Arrays.asList(ALICE_CLASS_EVENT);
        scheduler.addEvent(ALICE_CLASS_EVENT);
        assertEquals(scheduler.getEventsList(), validEventList);
    }

    @Test
    public void addListOfEvents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.addListOfEvents(null));
    }

    @Test
    public void addListOfEvents_validEventList_success() {
        scheduler.addListOfEvents(getTypicalEvents());
        assertEquals(getTypicalEvents(), scheduler.getEventsList());
    }

    @Test
    public void getVEvents_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduler.getVEvents().remove(0));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.addEvent(null));
    }

    @Test
    public void equals() {
        Scheduler otherScheduler = new Scheduler();

        // same object return true
        assertTrue(scheduler.equals(scheduler));

        // same internal List return true
        scheduler.addListOfEvents(getTypicalEvents());
        otherScheduler.addListOfEvents(getTypicalEvents());
        assertTrue(scheduler.equals(otherScheduler));

        // null return false
        assertFalse(scheduler.equals(null));

        // different types return false
        assertFalse(scheduler.equals(1));

        // different event list return false
        otherScheduler.addListOfEvents(Arrays.asList(ALICE_CLASS_EVENT));
        scheduler.addListOfEvents(getTypicalEvents());
        assertFalse(scheduler.equals(otherScheduler));

    }

    @Test
    public void mapClassTimeToLessonEvent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.mapClassTimesToLessonEvent(null));
    }

    @Test
    public void isClashingClassTime() {
        List<Student> studentList = getTypicalPersons();

        // Class time overlap exactly return true (Alice is in studentList)
        assertTrue(scheduler.isClashingClassTime(ALICE, studentList));

        // Class time half overlapped return true (Alice is "5 1500-1700")
        Student studentWithHalfOverlapClassTime = new StudentBuilder(ALICE).withClassTime("5 1400-1600").build();
        assertTrue(scheduler.isClashingClassTime(studentWithHalfOverlapClassTime, studentList));

        // class time exactly contained in between another student's class time return true
        Student studentWithContainedClassTime = new StudentBuilder(ALICE).withClassTime("5 1600-1630").build();
        assertTrue(scheduler.isClashingClassTime(studentWithContainedClassTime, studentList));

        // End Time of one student same as start time of another return false
        Student studentWithEndTimeSameAsStartTime = new StudentBuilder(ALICE).withClassTime("5 1400-1500").build();
        assertFalse(scheduler.isClashingClassTime(studentWithEndTimeSameAsStartTime, studentList));

        // different dayOfWeek same time return false
        Student studentWithDiffDaySameTime = new StudentBuilder(ALICE).withClassTime("4 1500-1700").build();
        assertFalse(scheduler.isClashingClassTime(studentWithDiffDaySameTime, studentList));

    }


}
