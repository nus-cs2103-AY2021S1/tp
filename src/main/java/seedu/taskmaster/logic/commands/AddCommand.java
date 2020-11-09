package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_NUSNETID;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.student.Student;

/**
 * Adds a student to the student list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add-student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the student list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TELEGRAM + "TELEGRAM "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_NUSNETID + "NUSNETID "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_TELEGRAM + "telehandle "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_NUSNETID + "e0123456 "
            + PREFIX_TAG + "weak "
            + PREFIX_TAG + "needsSpecialAttention";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the student list";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.addStudent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
