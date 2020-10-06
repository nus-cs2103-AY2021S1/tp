package jimmy.mcgymmy.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;

import jimmy.mcgymmy.commons.core.Messages;
import jimmy.mcgymmy.logic.parser.parameter.Parameter;
import jimmy.mcgymmy.model.Model;
import jimmy.mcgymmy.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private Parameter<NameContainsKeywordsPredicate> predicateParameter = this.addParameter(
            "keyword",
            "",
            "keywords (case-insensitive).",
            "alice bob charlie", (s) -> new NameContainsKeywordsPredicate(Arrays.asList(s.split("\\s+")))
    );

    void setParameters(Parameter<NameContainsKeywordsPredicate> predicateParameter) {
        this.predicateParameter = predicateParameter;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        NameContainsKeywordsPredicate predicate = predicateParameter.consume();
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
