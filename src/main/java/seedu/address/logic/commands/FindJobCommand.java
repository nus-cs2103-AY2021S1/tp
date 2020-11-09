package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.information.Job;

/**
 * Finds and lists all jobs in job address book whose details contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindJobCommand extends Command {
    public static final String COMMAND_WORD = "find job";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all jobs whose details contain all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " c/samsung e/recruitment@samsung.com";

    private final List<Predicate<Job>> predicates;

    /**
     * Constructs a {@code FindJobCommand} from the given {@code predicate}.
     */
    public FindJobCommand(Predicate<Job> predicate) {
        List<Predicate<Job>> predicateList = new ArrayList<>();
        predicateList.add(predicate);
        this.predicates = predicateList;
    }

    /**
     * Constructs a {@code FindJobCommand} from the given list of predicates {@code predicates}.
     */
    public FindJobCommand(List<Predicate<Job>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical AND of all predicates in the list
     * {@code predicates}.
     */
    public static Predicate<Job> composePredicatesList(List<Predicate<Job>> predicates) {

        if (predicates.size() == 1) {
            return predicates.get(0);
        }

        Predicate<Job> composedPredicate = unused -> true;
        for (Predicate<Job> p : predicates) {
            composedPredicate = composedPredicate.and(p);
        }

        return composedPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredJobList(composePredicatesList(predicates));
        return new CommandResult(
                String.format(Messages.MESSAGE_JOBS_LISTED_OVERVIEW, model.getFilteredJobList().size()),
                Job.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindJobCommand // instanceof handles nulls
                && predicates.equals(((FindJobCommand) other).predicates)); // state check
    }
}
