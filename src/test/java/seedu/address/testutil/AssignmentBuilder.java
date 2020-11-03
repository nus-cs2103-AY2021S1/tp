package seedu.address.testutil;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;

/**
 * A utility class to help with building Assignment objects.
 */
public class AssignmentBuilder {

    public static final String DEFAULT_NAME = "CS1231S Homework";
    public static final String DEFAULT_DEADLINE = "01-02-2020 1800";
    public static final String DEFAULT_MODULE_CODE = "CS2103T";
    public static final boolean DEFAULT_REMIND = false;
    public static final Deadline DEFAULT_SUGGESTED_START_TIME = new Deadline("01-02-2020 1800");
    public static final Deadline DEFAULT_SUGGESTED_END_TIME = new Deadline("01-02-2020 2100");
    public static final String DEFAULT_PRIORITY = "None";
    public static final boolean DEFAULT_DONE = false;

    private Name name;
    private Deadline deadline;
    private ModuleCode moduleCode;
    private Remind remind;
    private Schedule schedule;
    private Priority priority;
    private Done done;

    /**
     * Creates a {@code AssignmentBuilder} with the default details.
     */
    public AssignmentBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        remind = new Remind(DEFAULT_REMIND);
        schedule = new Schedule(DEFAULT_SUGGESTED_START_TIME, DEFAULT_SUGGESTED_END_TIME);
        priority = new Priority();
        done = new Done(DEFAULT_DONE);
    }

    /**
     * Initializes the AssignmentBuilder with the data of {@code assignmentToCopy}.
     */

    public AssignmentBuilder(Assignment assignmentToCopy) {
        name = assignmentToCopy.getName();
        deadline = assignmentToCopy.getDeadline();
        moduleCode = assignmentToCopy.getModuleCode();
        remind = assignmentToCopy.getRemind();
        schedule = assignmentToCopy.getSchedule();
        priority = assignmentToCopy.getPriority();
        done = assignmentToCopy.getDone();
    }

    /**
     * Sets the {@code Name} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code ModuleCode} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withModuleCode(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Remind} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withRemindersSet() {
        this.remind = new Remind().setReminder();
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withSchedule(Deadline suggestedStartTime, Deadline suggestedEndTime) {
        this.schedule = new Schedule(suggestedStartTime, suggestedEndTime);
        return this;
    }

    /**
     * Sets the {@code Done} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withDoneStatusSet() {
        this.done = new Done().markAsDone();
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Assignment} that we are building.
     */
    public AssignmentBuilder withPriority(String priorityLevel) {
        this.priority = new Priority(priorityLevel);
        return this;
    }

    public Assignment build() {
        return new Assignment(name, deadline, moduleCode, remind, schedule, priority, done);
    }

}
