package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeMostMadeCommand extends Command {

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        requireNonNull(model);

        String output;
        output = model.getRecipeUsageList().getMostUsed().toString();
        return CommandResult.message(String.format("The most used recipes are %s", output));
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
