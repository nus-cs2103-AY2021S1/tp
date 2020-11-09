package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACANCY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.information.Job;

/**
 * Adds a job to the job address book.
 */
public class AddJobCommand extends Command {

    public static final String COMMAND_WORD = "add job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a job to the job address book. "
            + "Parameters: "
            + PREFIX_JOB_TITLE + "JOB TITLE "
            + PREFIX_COMPANY_NAME + "COMPANY NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_VACANCY + "VACANCY "
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_JOB_TITLE + "Cashier "
            + PREFIX_COMPANY_NAME + "Walmart "
            + PREFIX_PHONE + "68765432 "
            + PREFIX_EMAIL + "recruitment@walmart.com "
            + PREFIX_ADDRESS + "1, Manhatten Street 2, #01-25 "
            + PREFIX_VACANCY + "2 "
            + PREFIX_PRIORITY + "low "
            + PREFIX_TAG + "fulltime ";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the address book.";

    private final Job toAdd;

    /**
     * Creates an AddJobCommand to add the specified {@code Job}
     */
    public AddJobCommand(Job job) {
        requireNonNull(job);
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        model.addJob(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), Job.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddJobCommand // instanceof handles nulls
                && toAdd.equals(((AddJobCommand) other).toAdd));
    }
}
