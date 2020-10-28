package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.resireg.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;

/**
 * Adds a student to ResiReg.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add-student";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s (%2$s)";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in ResiReg";

    public static final Help HELP = new Help(COMMAND_WORD,
        "Adds a student to ResiReg.\n",
        "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_STUDENT_ID + "STUDENT_ID "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_FACULTY + "FACULTY "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_STUDENT_ID + "E0123456 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@u.nus.edu "
            + PREFIX_FACULTY + "FASS ");

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model, Storage storage, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addStudent(toAdd);
        model.saveStateResiReg();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getNameAsString(), toAdd.getStudentId().value));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
