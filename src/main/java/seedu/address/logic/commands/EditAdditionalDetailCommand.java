package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class EditAdditionalDetailCommand extends AdditionalDetailCommand {

    public static final String COMMAND_WORD = AdditionalDetailCommand.COMMAND_WORD + " edit";

    public static final String MESSAGE_SUCCESS = "Detail edited: %1$s";
    public static final String MESSAGE_BAD_DETAIL_INDEX = "There is no detail at this index";

    private final Index studentIndex;
    private final Index detailIndex;
    private final AdditionalDetail detailToAdd;

    /**
     * Constructs a DeleteQuestionCommand to remove a specified {@code Question} from a {@code Student}.
     * @param studentIndex
     * @param detailIndex
     * @param detailToAdd
     */
    public EditAdditionalDetailCommand(Index studentIndex, Index detailIndex, AdditionalDetail detailToAdd) {
        requireAllNonNull(studentIndex, detailIndex, detailToAdd);
        this.studentIndex = studentIndex;
        this.detailIndex = detailIndex;
        this.detailToAdd = detailToAdd;
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
        requireNonNull(model);

        List<Student> lastShownList = model.getFilteredPersonList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddDetail = lastShownList.get(studentIndex.getZeroBased());

        if (detailIndex.getZeroBased() >= studentToAddDetail.getDetails().size()) {
            throw new CommandException(MESSAGE_BAD_DETAIL_INDEX);
        }

        List<AdditionalDetail> details = new ArrayList<>(studentToAddDetail.getDetails());
        details.set(detailIndex.getZeroBased(), detailToAdd);

        Student updatedStudent = super.updateStudentDetail(studentToAddDetail, details);

        model.setPerson(studentToAddDetail, updatedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, detailToAdd));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof EditAdditionalDetailCommand)) {
            return false;
        }

        EditAdditionalDetailCommand other = (EditAdditionalDetailCommand) obj;
        return studentIndex.equals(other.studentIndex) && detailIndex.equals(other.detailIndex)
                && detailToAdd.equals(other.detailToAdd);
    }
}
