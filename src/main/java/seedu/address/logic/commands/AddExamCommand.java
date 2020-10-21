package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.academic.exam.Exam;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

public class AddExamCommand extends Command{
    public static final String COMMAND_WORD = "addexam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exam to a student.\n\n"
            + "Parameters: INDEX (must be a positive integer)"
            + PREFIX_EXAM_NAME + "EXAM_NAME "
            + PREFIX_EXAM_DATE + "EXAM_DATE "
            + PREFIX_EXPECTED_SCORE + "EXPECTED_SCORE "
            + "[" + PREFIX_SCORE + "SCORE] \n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_EXAM_NAME + "End of Year Examination 2020 "
            + PREFIX_EXAM_DATE + "7/11/2020 "
            + PREFIX_EXPECTED_SCORE + "50/100 ";

    public static final String MESSAGE_EXAM_ADDED_SUCCESS = "New exam added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists under %1$s";

    private final Index index;
    private final Exam toAdd;


    /**
     * Creates an AddExamCommand to add the specified {@code Exam}
     */
    public AddExamCommand(Index index, Exam exam) {
        requireNonNull(exam);
        this.index = index;
        toAdd = exam;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student selectedStudent = lastShownList.get(index.getZeroBased());
        selectedStudent.getAcademic().getExamList().addExam(toAdd);
        return new CommandResult(String.format(MESSAGE_EXAM_ADDED_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddExamCommand // instanceof handles nulls
                && toAdd.equals(((AddExamCommand) other).toAdd));
    }
}
