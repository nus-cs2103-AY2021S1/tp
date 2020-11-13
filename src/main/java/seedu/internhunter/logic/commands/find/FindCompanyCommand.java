package seedu.internhunter.logic.commands.find;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.commands.util.CommandUtil.getCommandResult;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_ALIAS;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.company.CompanyNameContainsKeyWordsPredicate;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Finds and lists all company items in InternHunter whose CompanyName contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCompanyCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + COMPANY_ALIAS + ": Finds all companies in the list of companies whose names contain any of the given "
            + "keywords.\n"
            + "Parameters: KEYWORD [ANOTHER_KEYWORD]...\n"
            + "Example: " + COMMAND_WORD + " " + COMPANY_ALIAS + " Google Facebook";

    private final CompanyNameContainsKeyWordsPredicate predicate;

    /**
     * Creates a FindCompanyCommand to find all company items whose CompanyName contains any of the argument keywords.
     * Keyword matching is case-insensitive.
     *
     * @param predicate The predicate to check if a company's name contains any of the argument keywords.
     */
    public FindCompanyCommand(CompanyNameContainsKeyWordsPredicate predicate) {
        this.predicate = predicate;
    }

    /**
     * Executes the find company command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredCompanyList(predicate);
        model.setCompanyViewIndex(Index.fromOneBased(1));
        String message = String.format(Messages.MESSAGE_FIND_SUCCESS,
                model.getFilteredCompanyListSize(), COMPANY_NAME);
        return getCommandResult(model, message, TabName.COMPANY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCompanyCommand // instanceof handles nulls
                && predicate.equals(((FindCompanyCommand) other).predicate)); // state check
    }
}
