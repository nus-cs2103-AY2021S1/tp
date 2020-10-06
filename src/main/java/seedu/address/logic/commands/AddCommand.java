package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.TASK_DAY;
import static seedu.address.logic.parser.CliSyntax.TASK_EVENT;
import static seedu.address.logic.parser.CliSyntax.TASK_TODO;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Event;
import seedu.address.model.task.Todo;

/**
 * Adds a student to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String TO_ADD_STUDENT = "S";
    public static final String TO_ADD_TODO = "T";
    public static final String TO_ADD_EVENT = "E";
    public static final String TO_ADD_DEADLINE = "D";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";
    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This student already exists in jarvis";
    public static final String MESSAGE_INVALID_TO_ADD_TYPE = "This object to add is unidentifiable";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in jarvis";
    public static final String MESSAGE_SUCCESS_TASK = "New task added: %1$s";


    public static final String MESSAGE_TASK_USAGE = COMMAND_WORD + ": Adds a task to the address book. "
            + "Parameters: \n"
            + TASK_TODO + " DESCRIPTION "
            + "\nor\n"
            + TASK_EVENT + " DESCRIPTION "
            + TASK_DAY + " YYYY-MM-DD"
            + "\nor\n"
            + TASK_DEADLINE + " DESCRIPTION "
            + CliSyntax.TASK_DAY + " YYYY-MM-DD";
    public static final String MESSAGE_MISSING_DESCRIPTION = "Please include task DESCRIPTION";
    public static final String MESSAGE_MISSING_TIME = "Please include task TIME in YYYY-MM-DD";

    private final Object toAdd;
    private final String toAddType;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
        toAddType = TO_ADD_STUDENT;
    }

    /**
     * Creates an AddCommand to add the specified {@code Todo}
     */
    public AddCommand(Todo todo) {
        requireNonNull(todo);
        toAdd = todo;
        toAddType = TO_ADD_TODO;
    }

    /**
     * Creates an AddCommand to add the specified {@code Event}
     */
    public AddCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
        toAddType = TO_ADD_EVENT;
    }

    /**
     * Creates an AddCommand to add the specified {@code Deadline}
     */
    public AddCommand(Deadline deadline) {
        requireNonNull(deadline);
        toAdd = deadline;
        toAddType = TO_ADD_DEADLINE;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch(toAddType) {
        case TO_ADD_STUDENT:
            Student toAddStudent = (Student)toAdd;
            if (model.hasPerson(toAddStudent)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.addPerson(toAddStudent);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAddStudent));

        case TO_ADD_TODO:
            Todo toAddTodo = (Todo)toAdd;
            if (model.hasTodo(toAddTodo)) {
                throw new CommandException(MESSAGE_DUPLICATE_TODO);
            }

            model.addTodo(toAddTodo);
            return new CommandResult(String.format(MESSAGE_SUCCESS_TASK, toAddTodo));

        case TO_ADD_EVENT:
            Event toAddEvent = (Event)toAdd;
            if (model.hasEvent(toAddEvent)) {
                throw new CommandException(MESSAGE_DUPLICATE_TODO);
            }

            model.addEvent(toAddEvent);
            return new CommandResult(String.format(MESSAGE_SUCCESS_TASK, toAddEvent));

        case TO_ADD_DEADLINE:
            Deadline toAddDeadline = (Deadline)toAdd;
            if (model.hasEvent(toAddE)) {
                throw new CommandException(MESSAGE_DUPLICATE_TODO);
            }

            model.addEvent(toAddEvent);
            return new CommandResult(String.format(MESSAGE_SUCCESS_TASK, toAddEvent));

        default:
            throw new CommandException(MESSAGE_INVALID_TO_ADD_TYPE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
