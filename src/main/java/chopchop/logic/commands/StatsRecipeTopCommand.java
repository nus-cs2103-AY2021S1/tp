package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeTopCommand extends Command {

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
}
