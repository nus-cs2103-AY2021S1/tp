package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeMostCookedCommand extends Command {

    public static final String COMMAND_WORD = "stats recipe most cooked";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List recipes that are most cooked";

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
            || (other instanceof StatsRecipeMostCookedCommand);
    }

    @Override
    public String toString() {
        return "StatsRecipeMostCookedCommand";
    }
}
