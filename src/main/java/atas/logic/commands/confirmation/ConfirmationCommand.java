package atas.logic.commands.confirmation;

import static atas.commons.core.Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX;
import static atas.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static java.util.Objects.requireNonNull;

import atas.commons.core.index.Index;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.commands.sessionlist.IndexedSessionListCommand;
import atas.logic.commands.studentlist.IndexedStudentCommand;
import atas.model.Model;

/**
 * Confirms whether or not to execute a command.
 */
public class ConfirmationCommand extends ConfirmCommand {
    public static final String MESSAGE_CONFIRMATION = "(yes/no)";
    public static final String MESSAGE_CONFIRMATION_DELETE_STUDENT = "Delete student %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_EDIT_STUDENT = "Edit student %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_CLEAR_STUDENT_LIST = "Clear student list? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_DELETE_SESSION = "Delete session %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_EDIT_SESSION = "Edit session %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_CLEAR_SESSION_LIST = "Clear session list? " + MESSAGE_CONFIRMATION;
    public static final String ACCEPT_COMMAND_FULL = "yes";
    public static final String ACCEPT_COMMAND_SHORT = "y";
    public static final String REJECT_COMMAND_FULL = "no";
    public static final String REJECT_COMMAND_SHORT = "n";

    /**
     * Constructs a {@code ConfirmationCommand} with the specified DangerousCommand.
     */
    public ConfirmationCommand(DangerousCommand dangerousCommand) {
        super(dangerousCommand);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        DangerousCommand dangerousCommand = getDangerousCommand();
        if (dangerousCommand instanceof IndexedStudentCommand) {
            Index targetIndex = ((IndexedStudentCommand) dangerousCommand).getTargetIndex();
            int numberOfStudents = model.getNumberOfStudents();
            if (targetIndex.getZeroBased() >= numberOfStudents) {
                throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
        } else if (dangerousCommand instanceof IndexedSessionListCommand) {
            Index targetIndex = ((IndexedSessionListCommand) dangerousCommand).getTargetIndex();
            int numberOfSessions = model.getNumberOfSessions();
            if (targetIndex.getZeroBased() >= numberOfSessions) {
                throw new CommandException(MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
            }
        }

        return new CommandResult(String.format("%s? %s", getDangerousCommand(), MESSAGE_CONFIRMATION));
    }

    public ConfirmationAcceptCommand accept() {
        return new ConfirmationAcceptCommand(getDangerousCommand());
    }

    public ConfirmationRejectCommand reject() {
        return new ConfirmationRejectCommand(getDangerousCommand());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmationCommand)) {
            return false;
        }

        // calls for checks between the dangerousCommands
        DangerousCommand otherDangerousCommand = ((ConfirmationCommand) other).getDangerousCommand();
        return this.getDangerousCommand().equals(otherDangerousCommand);
    }
}
