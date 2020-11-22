package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLOOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_GROUP;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Finds and lists all residents in Hall-y whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all residents whose characteristics match the"
            + " given keywords and parameters"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME_KEYWORDS..." + "] "
            + "[" + PREFIX_BLOCK + "BLOCK" + "] "
            + "[" + PREFIX_FLOOR + "FLOOR" + "] "
            + "[" + PREFIX_ROOM_NUMBER + "ROOM_NUMBER" + "] "
            + "[" + PREFIX_MATRICULATION_NUMBER + "MATRICULATION_NUMBER" + "] "
            + "[" + PREFIX_GENDER + "GENDER" + "] "
            + "[" + PREFIX_STUDENT_GROUP + "STUDENT_GROUP " + "]"
            + "...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie " + PREFIX_BLOCK + "B";

    public static final String MESSAGE_EMPTY_KEYWORD = "Keywords must not be empty";

    private final List<Predicate<Person>> predicates;

    /**
     * Create a findCommand with the appropriate predicates.
     * @param predicates List of predicates to use to find the resident
     */
    public FindCommand(List<Predicate<Person>> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert predicates != null : "predicates shouldn't be null";
        Predicate<Person> personPredicate = predicates.stream().reduce(Predicate::and).orElse(person -> true);
        model.updateFilteredPersonList(personPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        assert predicates != null : "predicates shouldn't be null";
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
