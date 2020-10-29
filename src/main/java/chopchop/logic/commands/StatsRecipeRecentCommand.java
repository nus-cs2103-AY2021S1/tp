package chopchop.logic.commands;

import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeRecentCommand extends Command {
    public static final String COMMAND_WORD = "stats recipe recent";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " : Shows a list"
        + "of recipes recently made. (Capped at 10 recipes)";
    private static final int N_MOST_RECENT = 10;

    /**
     * Executes the command and returns the result message.
     *
     * @param model          {@code Model} which the command should operate on.
     * @param historyManager {@code History} which the command should record to.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        try {
            var output = model.getRecentlyUsedRecipe(N_MOST_RECENT);
            var msgOutput = output.stream()
                .map(x -> new Pair<>(x.getName(), x.getPrintableDate()))
                .collect(Collectors.toList());
            return CommandResult.statsMessage(msgOutput, "Here is a list of recipes recently made");
        } catch (Exception e) {
            return CommandResult.message("Unable to generate recipes recently made");
        }
    }

    @Override
    public String toString() {
        return "StatsRecipeRecentCommand";
    }

    public static String getCommandString() {
        return "stats recipe recent";
    }

    public static String getCommandHelp() {
        return "Shows the recipes that were recently made";
    }

    public static String getUserGuideSection() {
        throw new RuntimeException("listing-recently-made-recipes--statsreciperecent");
    }
}
