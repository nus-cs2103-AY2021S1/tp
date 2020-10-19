package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.admin.AdditionalDetail;

public class AddAdditionalDetailCommand extends AdditionalDetailCommand {

    public static final String MESSAGE_SUCCESS = "New detail added to student: %1$s";

    private final Index index;
    private final AdditionalDetail detailToAdd;

    /**
     * Creates an AddAdditionalDetailCommand to add the specified {@code AdditionalDetail} to the student
     * at the specified {@code Index}.
     */
    public AddAdditionalDetailCommand(Index index, AdditionalDetail detailToAdd) {
        requireAllNonNull(index, detailToAdd);
        this.index = index;
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
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToAddDetail = lastShownList.get(index.getZeroBased());

        List<AdditionalDetail> details = new ArrayList<>(studentToAddDetail.getDetails());
        details.add(detailToAdd);

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

        if (!(obj instanceof AddAdditionalDetailCommand)) {
            return false;
        }

        AddAdditionalDetailCommand other = (AddAdditionalDetailCommand) obj;
        return index.equals(other.index) && detailToAdd.equals(other.detailToAdd);
    }
}
