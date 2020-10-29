package chopchop.logic.commands;

import static chopchop.model.Model.PREDICATE_SHOW_ALL_ENTRIES;
import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.ui.DisplayNavigator;

/**
 * Lists all recommended recipes in the recipe book to the user.
 */
public class ListRecommendationCommand extends Command {
    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_ENTRIES);

        if (DisplayNavigator.hasDisplayController()) {
            DisplayNavigator.loadRecommendationPanel();
        }

        return CommandResult.message("Listed recommendations");
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListRecommendationCommand;
    }

    @Override
    public String toString() {
        return "ListRecommendationCommand";
    }

    public static String getCommandString() {
        return "list recommendation";
    }

    public static String getCommandHelp() {
        return "Lists all recommendations, clearing any search filters";
    }

    public static String getUserGuideSection() {
        return "list-recommendations--listrecommendation";
    }
}
