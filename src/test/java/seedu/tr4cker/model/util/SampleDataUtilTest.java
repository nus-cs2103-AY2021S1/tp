package seedu.tr4cker.model.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.tr4cker.model.util.SampleDataUtil.getModuleCodeSet;
import static seedu.tr4cker.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

class SampleDataUtilTest {

    private final Task[] tasks = new Task[] {
        new Task(new Name("CS2100 Lab 1"), new Deadline("10-Jun-2020 1800", false),
                new CompletionStatus(90), new TaskDescription("Warmup lab practice"),
                getModuleCodeSet("CS2100"), getTagSet("Urgent")),
        new Task(new Name("CS2100 Lab 2"), new Deadline("10-Aug-2020 1800", false),
                new CompletionStatus(90), new TaskDescription("First official lab"),
                getModuleCodeSet("CS2100"), getTagSet("demo", "Important")),
        new Task(new Name("GEQ1000 Tutorial 3"), new Deadline("10-Oct-2019 1800", false),
                new CompletionStatus(90), new TaskDescription("Too much reasoning"),
                getModuleCodeSet("GEQ1000"), getTagSet("tutorial", "origami")),
        new Task(new Name("GER1000 Graded Assignment 4"), new Deadline("20-Oct-2019 1800", false),
                new CompletionStatus(80), new TaskDescription("Chapter 3 - Sampling Methods"),
                getModuleCodeSet("GER1000"), getTagSet("graded", "assignment")),
        new Task(new Name("Team meeting"), new Deadline("19-Sep-2020 1000", false),
                new CompletionStatus(100), new TaskDescription("Discuss v1.2"),
                getModuleCodeSet("CS2103T"), getTagSet("meeting")),
        new Task(new Name("Team meeting"), new Deadline("25-Oct-2020 1000", false),
                new CompletionStatus(100), new TaskDescription("Discuss v1.3"),
                getModuleCodeSet("CS2103T"), getTagSet("meeting")),
        new Task(new Name("Thai Lesson"), new Deadline("27-Oct-2020 1000", false),
                new CompletionStatus(100), new TaskDescription("Learning new consonants"),
                getModuleCodeSet("LAT1201"), getTagSet("lesson")),
        new Task(new Name("Thai Listening Quiz 2"), new Deadline("27-Oct-2020 2000", false),
                new CompletionStatus(100), new TaskDescription("Pages 1-43"),
                getModuleCodeSet("LAT1201"), getTagSet("listening")),
        new Task(new Name("OP2 Pitch Meeting"), new Deadline("27-Oct-2020 2130", false),
                new CompletionStatus(100), new TaskDescription("Prepare materials for meeting"),
                getModuleCodeSet("CS2101"), getTagSet("op2")),
        new Task(new Name("CS2103T Mock Exam"), new Deadline("30-Oct-2020 1600", false),
                new CompletionStatus(0), new TaskDescription("Read instructions"),
                getModuleCodeSet("CS2103T"), getTagSet("mock", "practical")),
        new Task(new Name("CS2100 Assignment 3"), new Deadline("31-Oct-2020 2359", false),
                new CompletionStatus(80), new TaskDescription("Boolean algebra"),
                getModuleCodeSet("CS2100"), getTagSet("graded", "assignment")),
        new Task(new Name("GEQ1000 Quiz 6"), new Deadline("05-Nov-2020 2359", false),
                new CompletionStatus(50), new TaskDescription("Design quiz"),
                getModuleCodeSet("GEQ1000"), getTagSet("graded", "readings")),
        new Task(new Name("CS1101S Mission"), new Deadline("10-Oct-2021 1800", false),
                new CompletionStatus(100), new TaskDescription("A very fun mission"),
                getModuleCodeSet("CS1101S"), getTagSet("Mission", "Fun")),
        new Task(new Name("CS1101S Quiz"), new Deadline("11-Sep-2021 2359", false),
                new CompletionStatus(0), new TaskDescription("A quiz that's too hard for me"),
                getModuleCodeSet("CS1101S"), getTagSet("Quiz", "2Hard4Me")),
        new Task(new Name("CS2100 MidTerms"), new Deadline("12-Oct-2021 1200", false),
                new CompletionStatus(20), new TaskDescription("Need do cheat sheet"),
                getModuleCodeSet("CS2100"), getTagSet("cheatSheetsFTW")),
        new Task(new Name("CS2103T Project"), new Deadline("13-Sep-2021 1500", false),
                new CompletionStatus(50), new TaskDescription("I love project work"),
                getModuleCodeSet("CS2103T"), getTagSet("EthanSGKK")),
        new Task(new Name("CS2101 OP2"), new Deadline("14-Apr-2021 1010", false),
                new CompletionStatus(0), new TaskDescription("All the best Good Luck"),
                getModuleCodeSet("CS2101"), getTagSet("20Percent")),
        new Task(new Name("CS1231S Graded Assignment"), new Deadline("25-Dec-2021 0000", false),
                new CompletionStatus(1), new TaskDescription("JustDueet"),
                getModuleCodeSet("CS1231S"), getTagSet("Assignment"))
    };

    private final Module[] modules = new Module[] {
        new Module("Asking Questions", new ModuleCode("GEQ1000")),
        new Module("Quantitative Reasoning", new ModuleCode("GER1000")),
        new Module("Programming Methodology", new ModuleCode("CS1101S")),
        new Module("Computer Organization", new ModuleCode("CS2100")),
        new Module("Software Engineering", new ModuleCode("CS2103T")),
        new Module("Effective Communication for Computing Professionals",
                    new ModuleCode("CS2101")),
        new Module("Discrete Structures", new ModuleCode("CS1231S")),
        new Module("Thai 1", new ModuleCode("LAT1201"))
    };

    private final Event[] events = new Event[]{
        new Event(new EventName("CS2103T Final Exam"), new EventDate("02-Dec-2020", false)),
        new Event(new EventName("CS2103T Practical Exam Dry Run"),
                new EventDate("30-10-2020", false)),
        new Event(new EventName("CS2103T Practical Exam"), new EventDate("13-Nov-2020", false)),
        new Event(new EventName("1st Anniversary"), new EventDate("15-11-2021", false)),
        new Event(new EventName("Confession Day"), new EventDate("15-11-2020", false)),
        new Event(new EventName("CS2101 OP2 Demo"), new EventDate("03-11-2020", false)),
        new Event(new EventName("CS2101 OP2 Pitch"), new EventDate("06-11-2020", false)),
        new Event(new EventName("Christmas"), new EventDate("25-Dec-2020", false)),
        new Event(new EventName("New Years Eve"), new EventDate("31-Dec-2020", false)),
        new Event(new EventName("ST2334 Finals"), new EventDate("23-Nov-2020", false)),
        new Event(new EventName("CS2100 Finals Exam"), new EventDate("27-11-2020", false))
    };

    @Test
    public void testGetSampleData() {
        assertArrayEquals(SampleDataUtil.getSampleTasks(), tasks);
    }

    @Test
    public void testGetSampleModules() {
        assertArrayEquals(SampleDataUtil.getSampleModules(), modules);
    }

    @Test
    public void testGetSampleEvents() {
        assertArrayEquals(SampleDataUtil.getSampleEvents(), events);
    }

    @Test
    public void testGetSampleTr4cker_doesNotThrow() {
        assertDoesNotThrow(SampleDataUtil::getSampleTr4cker);
    }
}
