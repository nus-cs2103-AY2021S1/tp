package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.JobAddressBook;
import seedu.address.model.Model;
import seedu.address.model.information.Job;

/**
 * Clears the job list.
 */
public class ClearJobCommand extends Command {

    public static final String COMMAND_WORD = "clear job";
    public static final String MESSAGE_SUCCESS = "Job list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setJobAddressBook(new JobAddressBook());
        return new CommandResult(MESSAGE_SUCCESS, Job.TAB_NAME);
    }
}
