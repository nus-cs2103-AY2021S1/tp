package ay2021s1_cs2103_w16_3.finesse.logic.commands;

import static ay2021s1_cs2103_w16_3.finesse.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;
import static java.util.Objects.requireNonNull;

import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.ui.UiState.Tab;

/**
 * Lists all income in the finance tracker to the user.
 */
public class ListIncomeCommand extends Command {

    public static final String COMMAND_WORD = "ls-income";
    public static final String COMMAND_ALIAS = "lsi";

    public static final String MESSAGE_SUCCESS = "Listed all income.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredIncomeList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        return new CommandResult(MESSAGE_SUCCESS, Tab.INCOME);
    }
}
