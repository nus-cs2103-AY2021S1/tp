package com.eva.logic.commands;

import static java.util.Objects.requireNonNull;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.model.Model;
import com.eva.model.person.NameContainsKeywordsPredicate;
import com.eva.model.person.applicant.Applicant;

/**
 * Finds and lists all persons in eva database whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindApplicantCommand extends FindCommand {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [a-|s-] KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";


    private final NameContainsKeywordsPredicate<Applicant> predicate;

    public FindApplicantCommand(NameContainsKeywordsPredicate<Applicant> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPanelState(PanelState.APPLICANT_LIST);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindApplicantCommand // instanceof handles nulls
                && predicate.equals(((FindApplicantCommand) other).predicate)); // state check
    }
}
