package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;

public class DeleteAdditionalDetailCommand extends AdditionalDetailCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = AdditionalDetailCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": deletes an Additional Detail in the student identified"
            + " by the index number used in the displayed student list. \n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_DETAIL_INDEX + "DETAIL_INDEX (must be a positive integer)\n"
            + "Example: " + AdditionalDetailCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_DETAIL_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Detail removed from %s: %s";
    public static final String MESSAGE_BAD_DETAIL_INDEX = "There is no detail at this index";

    private static Logger logger = Logger.getLogger("Delete Additional Detail Log");

    private final Index studentIndex;
    private final Index detailIndex;

    /**
     * Constructs a DeleteQuestionCommand to remove a specified {@code Question} from a {@code Student}.
     * @param studentIndex
     * @param detailIndex
     */
    public DeleteAdditionalDetailCommand(Index studentIndex, Index detailIndex) {
        requireAllNonNull(studentIndex, detailIndex);
        this.studentIndex = studentIndex;
        this.detailIndex = detailIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert(studentIndex != null && detailIndex != null);
        requireNonNull(model);
        logger.log(Level.INFO, "Beginning command execution");

        List<Student> lastShownList = model.getFilteredPersonList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Invalid student index input error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddDetail = lastShownList.get(studentIndex.getZeroBased());
        if (detailIndex.getZeroBased() >= studentToAddDetail.getDetails().size()) {
            logger.log(Level.WARNING, "Invalid detail index input error");
            throw new CommandException(MESSAGE_BAD_DETAIL_INDEX);
        }

        List<AdditionalDetail> details = new ArrayList<>(studentToAddDetail.getDetails());
        AdditionalDetail removedDetail = details.remove(detailIndex.getZeroBased());

        Student updatedStudent = super.updateStudentDetail(studentToAddDetail, details);

        model.setPerson(studentToAddDetail, updatedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), removedDetail));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeleteAdditionalDetailCommand)) {
            return false;
        }

        DeleteAdditionalDetailCommand other = (DeleteAdditionalDetailCommand) obj;
        return studentIndex.equals(other.studentIndex) && detailIndex.equals(other.detailIndex);
    }
}
