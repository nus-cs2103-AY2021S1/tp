package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;

/**
 * Sets reminders for an assignment identified using it's displayed index from the address book.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets reminders for assignment(s) identified by the index numbers "
            + "used in the displayed assignment list."
            + " Assignments with reminders set are permanently shown in the displayed reminders list.\n"
            + "Parameters: INDEX [MORE_INDEXES] (must be a positive integer)\n"
            + "Examples: \n"
            + COMMAND_WORD + " 1 \n"
            + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_REMIND_ASSIGNMENT_SUCCESS = "Set reminder for Assignment: %1$s";
    public static final String MESSAGE_REMINDED_ASSIGNMENT = "This assignment already has reminders set: %1$s";

    private final List<Index> targetIndexes;

    /**
     * Constructs a RemindCommand to set reminders to the specified assignment(s).
     * @param targetIndexes indexes of assignments in the filtered assignment list to remind
     */
    public RemindCommand(List<Index> targetIndexes) {
        requireNonNull(targetIndexes);
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();
        List<Assignment> assignmentsToRemind = new ArrayList<>();

        targetIndexes.sort(CommandLogic.INDEX_COMPARATOR);

        CommandLogic.checkForDuplicatedIndexes(targetIndexes);
        CommandLogic.checkForInvalidIndexes(targetIndexes, model, RemindCommand.MESSAGE_USAGE);

        for (Index targetIndex : targetIndexes) {
            Assignment assignmentToRemind = lastShownList.get(targetIndex.getZeroBased());

            if (assignmentToRemind.isReminded() && model.hasAssignment(assignmentToRemind)) {
                throw new CommandException(String.format(MESSAGE_REMINDED_ASSIGNMENT, assignmentToRemind));
            }

            assert(!assignmentToRemind.isReminded());
            Assignment remindedAssignment = createRemindedAssignment(assignmentToRemind);

            model.setAssignment(assignmentToRemind, remindedAssignment);
            model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
            assignmentsToRemind.add(assignmentToRemind);
        }

        return new CommandResult(String.format(MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentsToRemind));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToRemind}.
     */
    private static Assignment createRemindedAssignment(Assignment assignmentToRemind) {
        assert assignmentToRemind != null;

        Name updatedName = assignmentToRemind.getName();
        Deadline updatedDeadline = assignmentToRemind.getDeadline();
        ModuleCode updatedModuleCode = assignmentToRemind.getModuleCode();
        Remind updatedRemind = assignmentToRemind.getRemind().setReminder();
        Schedule updatedSchedule = assignmentToRemind.getSchedule();
        Priority priority = assignmentToRemind.getPriority();
        Done updatedDone = assignmentToRemind.getDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                priority, updatedDone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && targetIndexes.equals(((RemindCommand) other).targetIndexes)); // state check
    }
}
