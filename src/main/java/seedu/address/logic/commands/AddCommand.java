package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSVENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASSTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONALDETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETINGLINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_SCHOOL + "SCHOOL "
            + PREFIX_YEAR + "YEAR "
            + PREFIX_CLASSVENUE + "CLASS_VENUE"
            + PREFIX_CLASSTIME + "CLASS_TIME"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_SCHOOL + "Commonwealth Secondary School"
            + PREFIX_CLASSVENUE + "Jurong West Community Centre Level 2 Classroom 2"
            + PREFIX_YEAR + "Secondary 2"
            + PREFIX_CLASSTIME + "13:00"
            + PREFIX_MEETINGLINK + "johnd@example.com "
            + PREFIX_SUBJECT + "311, Clementi Ave 2, #02-25 "
            + PREFIX_ADDITIONALDETAILS + "Student likes chocolate"
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
