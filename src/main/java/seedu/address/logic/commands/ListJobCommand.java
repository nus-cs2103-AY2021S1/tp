package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_JOBS;

import seedu.address.model.Model;
import seedu.address.model.information.Job;

/**
 * Lists all jobs in the job address book to the user.
 */
public class ListJobCommand extends Command {

    public static final String COMMAND_WORD = "list job";

    public static final String MESSAGE_SUCCESS = "Listed all jobs";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(MESSAGE_SUCCESS, Job.TAB_NAME);
    }
}
