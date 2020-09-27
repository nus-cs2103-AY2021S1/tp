package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.parameter.Parameter;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    private final Parameter<NameContainsKeywordsPredicate> predicateParameter = this.addParameter(
        "keyword",
        "",
        "keywords (case-insensitive).",
        "alice bob charlie",
        (s) -> new NameContainsKeywordsPredicate(Arrays.asList(s.split("\\s+")))
    );

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        NameContainsKeywordsPredicate predicate = predicateParameter.consume();
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
