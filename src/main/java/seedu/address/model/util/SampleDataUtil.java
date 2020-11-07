package seedu.address.model.util;

import java.time.LocalDateTime;

import seedu.address.model.ProductiveNus;
import seedu.address.model.ReadOnlyProductiveNus;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

/**
 * Contains utility methods for populating {@code ProductiveNus} with sample data.
 */
public class SampleDataUtil {
    private static final Remind NOT_REMINDED = new Remind();
    private static final Remind IS_REMINDED = new Remind(true);
    private static final Schedule NOT_SCHEDULED = new Schedule();
    private static final Priority NO_PRIORITY = new Priority();
    private static final Priority HIGH_PRIORITY = new Priority("HIGH");
    private static final Priority LOW_PRIORITY = new Priority("LOW");
    private static final Priority MEDIUM_PRIORITY = new Priority("MEDIUM");
    private static final Done NOT_DONE = new Done();
    private static final Done IS_DONE = new Done(true);
    private static final Time DEADLINE_OVERDUE = new Time(LocalDateTime.now().minusDays(1));
    private static final Time DEADLINE_12_HOURS = new Time(LocalDateTime.now().plusHours(12));
    private static final Time DEADLINE_5_DAYS = new Time(LocalDateTime.now().plusDays(5));
    private static final Time DEADLINE_15_DAYS = new Time(LocalDateTime.now().plusDays(15));
    private static final Time DEADLINE_25_DAYS = new Time(LocalDateTime.now().plusDays(25));
    private static final Time DEADLINE_60_DAYS = new Time(LocalDateTime.now().plusDays(60));
    public static Assignment[] getSampleAssignments() {
        return new Assignment[] {
            new Assignment(new Name("CS1231S Homework"), DEADLINE_5_DAYS,
                    new ModuleCode("CS1231S"), NOT_REMINDED, getValidScheduleBeforeNextWeek(), MEDIUM_PRIORITY,
                    IS_DONE),
            new Assignment(new Name("CS2103T Quiz"), DEADLINE_12_HOURS,
                    new ModuleCode("CS2103T"), NOT_REMINDED, NOT_SCHEDULED, HIGH_PRIORITY, IS_DONE),
            new Assignment(new Name("CS2106 Lab"), DEADLINE_25_DAYS,
                    new ModuleCode("CS2106"), IS_REMINDED, NOT_SCHEDULED, LOW_PRIORITY, NOT_DONE),
            new Assignment(new Name("Peer review"), DEADLINE_60_DAYS,
                    new ModuleCode("CS2101"), NOT_REMINDED, getValidScheduleBeforeNextMonth(), NO_PRIORITY, NOT_DONE),
            new Assignment(new Name("IS1103 Mission"), DEADLINE_OVERDUE,
                    new ModuleCode("IS1103"), IS_REMINDED, NOT_SCHEDULED, NO_PRIORITY, IS_DONE),
            new Assignment(new Name("Oral Presentation"), DEADLINE_15_DAYS,
                    new ModuleCode("CS2101"), NOT_REMINDED, NOT_SCHEDULED, NO_PRIORITY, NOT_DONE)
        };
    }

    private static Schedule getValidScheduleBeforeNextWeek() {
        LocalDateTime suggestedStartTime = LocalDateTime.now().plusDays(3);
        // round to nearest hour
        suggestedStartTime = suggestedStartTime.withHour(7);
        suggestedStartTime = suggestedStartTime.withMinute(0);
        LocalDateTime suggestedEndTime = suggestedStartTime.plusHours(2);
        return new Schedule(new Time(suggestedStartTime), new Time(suggestedEndTime));
    }

    private static Schedule getValidScheduleBeforeNextMonth() {
        LocalDateTime suggestedStartTime = LocalDateTime.now().plusDays(20);
        // round to nearest hour
        suggestedStartTime = suggestedStartTime.withHour(19);
        suggestedStartTime = suggestedStartTime.withMinute(0);
        LocalDateTime suggestedEndTime = suggestedStartTime.plusHours(3);
        return new Schedule(new Time(suggestedStartTime), new Time(suggestedEndTime));
    }

    public static ReadOnlyProductiveNus getSampleProductiveNus() {
        ProductiveNus sampleAb = new ProductiveNus();
        for (Assignment sampleAssignment : getSampleAssignments()) {
            sampleAb.addAssignment(sampleAssignment);
        }
        return sampleAb;
    }
}
