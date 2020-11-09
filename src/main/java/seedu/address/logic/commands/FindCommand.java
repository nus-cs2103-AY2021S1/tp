package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose attributes contain all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "At least one of the fields must be provided.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "[" + PREFIX_OFFICE + "OFFICE] "
            + "[" + PREFIX_REMARK + "REMARK] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "Alex Yeoh "
            + PREFIX_DEPARTMENT + "computing";

    private final List<Predicate<Person>> predicates;

    /**
     * Constructs a {@code FindCommand} from the given {@code predicate}.
     *
     * @param predicate The searching parameter used in finding contacts.
     */
    public FindCommand(Predicate<Person> predicate) {
        List<Predicate<Person>> predicateList = new ArrayList<>();
        predicateList.add(predicate);
        this.predicates = predicateList;
    }

    /**
     * Constructs a {@code FindCommand} from the given list of {@code predicates}.
     *
     * @param predicates The searching parameters used in finding contacts.
     */
    public FindCommand(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical AND of {@code predicates}.
     */
    public static Predicate<Person> composePredicates(List<Predicate<Person>> predicates) {

        if (predicates.size() == 1) {
            return predicates.get(0);
        }

        Predicate<Person> composedPredicate = person -> true;

        for (Predicate<Person> p : predicates) {
            composedPredicate = composedPredicate.and(p);
        }

        return composedPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(composePredicates(predicates));
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicates.equals(((FindCommand) other).predicates)); // state check
    }

    @Override
    public String toString() {
        return Arrays.toString(predicates.toArray());
    }

}
