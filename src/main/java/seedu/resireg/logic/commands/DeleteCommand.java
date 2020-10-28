package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;

/**
 * Deletes a student identified using its displayed index from ResiReg.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete-student";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_ROOM_ALLOCATION_EXISTS =
            "Student %1$s currently has a room! Deallocate student first!";

    public static final Help HELP = new Help(COMMAND_WORD,
            "Deletes the student identified by the index number used in the displayed student list.",
            "Parameters: INDEX (must be a positive integer)\nExample: " + COMMAND_WORD + " 1");

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());

        if (model.isAllocated(studentToDelete)) {
            throw new CommandException(String.format(MESSAGE_ROOM_ALLOCATION_EXISTS,
                studentToDelete.getNameAsString()));
        }

        model.addBinItem(new BinItem(studentToDelete));
        model.deleteStudent(studentToDelete);
        model.saveStateResiReg();
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete.getNameAsString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
