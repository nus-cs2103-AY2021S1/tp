package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.model.Model;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks with "
            + "the specified keywords (case-insensitive) in description or remark.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " submit";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.findTasks(keyword);

        String feedbackMessage = "Search for tasks with keyword: " + keyword + " in description / remark.\n"
                + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size());

        return new CommandResult(feedbackMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && keyword.equals(((FindCommand) other).keyword)); // state check
    }
}
