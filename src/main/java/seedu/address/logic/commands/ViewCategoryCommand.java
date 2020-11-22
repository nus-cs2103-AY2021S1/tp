package seedu.address.logic.commands;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.expense.Category;

/**
 * View all the categories the users created in the expense book.
 */
public class ViewCategoryCommand extends Command {

    public static final String COMMAND_WORD = "view-c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all the category labels used in the ExpenseBook so far. \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_VIEW_CATEGORY_LABELS_SUCCESS = "View all the existing category labels: \n";

    /**
     * Executes the view category command.
     * @param model {@code Model} which the command should operate on.
     * @return A command result in which all the categories are shown.
     */
    @Override
    public CommandResult execute(Model model) {
        String message = "";
        List<Category> categories = model.getCategoryLabels();
        for (int i = 0; i < categories.size(); i++) {
            message = message + categories.get(i).categoryName + "\n";
        }
        return new CommandResult(MESSAGE_VIEW_CATEGORY_LABELS_SUCCESS + message);
    }

}
