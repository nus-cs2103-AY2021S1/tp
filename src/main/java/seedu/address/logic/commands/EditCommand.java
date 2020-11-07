package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ASSIGNMENT;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Done;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Schedule;
import seedu.address.model.task.ModuleCode;
import seedu.address.model.task.Name;
import seedu.address.model.task.Time;

/**
 * Edits the details of an existing assignment in ProductiveNus.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "Format: " + COMMAND_WORD
            + " INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_MODULE_CODE + "MODULE CODE] ";

    public static final String MESSAGE_EDIT_ASSIGNMENT_SUCCESS = "Edited Assignment: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in ProductiveNus.";

    private final Index index;
    private final EditAssignmentDescriptor editAssignmentDescriptor;

    /**
     * @param index of the assignment in the filtered assignment list to edit
     * @param editAssignmentDescriptor details to edit the assignment with
     */
    public EditCommand(Index index, EditAssignmentDescriptor editAssignmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAssignmentDescriptor);

        this.index = index;
        this.editAssignmentDescriptor = new EditAssignmentDescriptor(editAssignmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Assignment> lastShownList = model.getFilteredAssignmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
        }

        Assignment assignmentToEdit = lastShownList.get(index.getZeroBased());
        Assignment editedAssignment = createEditedAssignment(assignmentToEdit, editAssignmentDescriptor);

        if (!assignmentToEdit.isSameAssignment(editedAssignment) && model.hasAssignment(editedAssignment)) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }

        model.setAssignment(assignmentToEdit, editedAssignment);
        model.updateFilteredAssignmentList(PREDICATE_SHOW_ALL_ASSIGNMENT);
        return new CommandResult(String.format(MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment));
    }

    /**
     * Creates and returns a {@code Assignment} with the details of {@code assignmentToEdit}
     * edited with {@code editAssignmentDescriptor}.
     */
    private static Assignment createEditedAssignment(Assignment assignmentToEdit,
                                                     EditAssignmentDescriptor editAssignmentDescriptor) {
        assert assignmentToEdit != null;
        Name updatedName = editAssignmentDescriptor.getName().orElse(assignmentToEdit.getName());
        Time updatedDeadline = editAssignmentDescriptor.getDeadline().orElse(assignmentToEdit.getDeadline());
        ModuleCode updatedModuleCode = editAssignmentDescriptor.getModuleCode()
                .orElse(assignmentToEdit.getModuleCode());
        Remind updatedRemind = assignmentToEdit.getRemind();
        Schedule updatedSchedule = assignmentToEdit.getSchedule();
        Priority priority = assignmentToEdit.getPriority();
        Done updatedDone = assignmentToEdit.getDone();

        return new Assignment(updatedName, updatedDeadline, updatedModuleCode, updatedRemind, updatedSchedule,
                priority, updatedDone);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editAssignmentDescriptor.equals(e.editAssignmentDescriptor);
    }

    /**
     * Stores the details to edit the assignment with. Each non-empty field value will replace the
     * corresponding field value of the assignment.
     */
    public static class EditAssignmentDescriptor {
        private Name name;
        private Time deadline;
        private ModuleCode moduleCode;

        public EditAssignmentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditAssignmentDescriptor(EditAssignmentDescriptor toCopy) {
            setName(toCopy.name);
            setDeadline(toCopy.deadline);
            setModuleCode(toCopy.moduleCode);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, deadline, moduleCode);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDeadline(Time deadline) {
            this.deadline = deadline;
        }

        public Optional<Time> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setModuleCode(ModuleCode moduleCode) {
            this.moduleCode = moduleCode;
        }

        public Optional<ModuleCode> getModuleCode() {
            return Optional.ofNullable(moduleCode);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAssignmentDescriptor)) {
                return false;
            }

            // state check
            EditAssignmentDescriptor e = (EditAssignmentDescriptor) other;

            return getName().equals(e.getName())
                    && getDeadline().equals(e.getDeadline())
                    && getModuleCode().equals(e.getModuleCode());
        }
    }
}
