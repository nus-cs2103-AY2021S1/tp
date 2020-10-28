package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeMostMadeCommand extends Command {
    public static final String COMMAND_WORD = "stats recipe most made";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List recipes that are most made";

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);

        var output = model.getRecipeUsageList().getMostUsed();
        return CommandResult.statsMessage(output, "Here are the most made recipes");
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof StatsRecipeMostMadeCommand);
    }

    @Override
    public String toString() {
        return "StatsRecipeMostCookedCommand";
    }

    public static String getCommandString() {
        return "stats recipe most made";
    }

    public static String getCommandHelp() {
        return "Shows the recipes that are cooked the most";
    }

    public static String getUserGuideSection() {
        throw new RuntimeException("Travis pls implement this");
    }
}
