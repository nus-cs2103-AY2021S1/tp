package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeTopCommand extends Command {
    public static final String COMMAND_WORD = "stats recipe top";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List recipes that are made the most number of times.";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);

        var output = model.getRecipeUsageList().getMostUsed();
        return CommandResult.statsMessage(output, "Here are the top recipes");
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof StatsRecipeTopCommand);
    }

    @Override
    public String toString() {
        return "StatsRecipeTopCommand";
    }

    public static String getCommandString() {
        return "stats recipe top";
    }

    public static String getCommandHelp() {
        return "Shows the recipes that are made the most";
    }

    public static String getUserGuideSection() {
        throw new RuntimeException("list-top-recipe-statsrecipetop");
    }
}
