package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.Detail;

public class DeleteDetailCommand extends DetailCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = DetailCommand.COMMAND_WORD + " " + COMMAND_WORD
            + ": deletes a Detail in the specified student\n\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer) "
            + PREFIX_INDEX + "DETAIL_INDEX (must be a positive integer)\n\n"
            + "Example: " + DetailCommand.COMMAND_WORD + " " + COMMAND_WORD + " 2 "
            + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "Detail removed from %s: %s";
    public static final String MESSAGE_BAD_DETAIL_INDEX = "There is no detail at this index";

    private static Logger logger = Logger.getLogger("Delete Detail Log");

    private final Index studentIndex;
    private final Index detailIndex;

    /**
     * Constructs a DeleteQuestionCommand to remove a specified {@code Question} from a {@code Student}.
     * @param studentIndex
     * @param detailIndex
     */
    public DeleteDetailCommand(Index studentIndex, Index detailIndex) {
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

        List<Student> lastShownList = model.getSortedStudentList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            logger.log(Level.WARNING, "Invalid student index input error");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDeleteDetail = lastShownList.get(studentIndex.getZeroBased());
        if (detailIndex.getZeroBased() >= studentToDeleteDetail.getDetails().size()) {
            logger.log(Level.WARNING, "Invalid detail index input error");
            throw new CommandException(MESSAGE_BAD_DETAIL_INDEX);
        }

        List<Detail> details = studentToDeleteDetail.getDetails();
        Detail removedDetail = details.remove(detailIndex.getZeroBased());

        Student updatedStudent = super.updateStudentDetail(studentToDeleteDetail, details);

        model.setStudent(studentToDeleteDetail, updatedStudent);

        logger.log(Level.INFO, "Execution complete");
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedStudent.getName(), removedDetail));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DeleteDetailCommand)) {
            return false;
        }

        DeleteDetailCommand other = (DeleteDetailCommand) obj;
        return studentIndex.equals(other.studentIndex) && detailIndex.equals(other.detailIndex);
    }
}
