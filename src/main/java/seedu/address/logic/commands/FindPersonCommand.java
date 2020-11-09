package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.information.Person;

/**
 * Finds and lists all persons in address book whose details contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "find can";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose details contain all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice p/90909090";

    private final List<Predicate<Person>> predicates;

    /**
     * Constructs a {@code FindPersonCommand} from the given {@code predicate}.
     */
    public FindPersonCommand(Predicate<Person> predicate) {
        List<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(predicate);
        this.predicates = predicateList;
    }

    /**
     * Constructs a {@code FindPersonCommand} from the given list of predicate {@code predicates}.
     */
    public FindPersonCommand(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical AND of all predicates in the list
     * {@code predicates}.
     */
    public static Predicate<Person> composePredicatesList(List<Predicate<Person>> predicates) {

        if (predicates.size() == 1) {
            return predicates.get(0);
        }

        Predicate<Person> composedPredicate = unused -> true;
        for (Predicate<Person> p : predicates) {
            composedPredicate = composedPredicate.and(p);
        }

        return composedPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(composePredicatesList(predicates));
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                Person.TAB_NAME);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicates.equals(((FindPersonCommand) other).predicates)); // state check
    }
}
