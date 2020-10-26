package chopchop.logic.commands;

import static chopchop.commons.util.Strings.ARG_AFTER;
import static chopchop.commons.util.Strings.ARG_BEFORE;
import static chopchop.commons.util.Strings.ARG_ON;

import java.time.LocalDateTime;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsIngredientDateCommand extends Command {
    public static final String COMMAND_WORD = "stats ingredient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of ingredient. "
        + "Parameters: "
        + "[" + ARG_ON + " DATE] "
        + "[" + ARG_BEFORE + "DATE] "
        + "[" + ARG_AFTER + "DATE] "
        + "Example " + COMMAND_WORD + " " + ARG_BEFORE + " 2020-02-13";

    private final LocalDateTime on;
    private final LocalDateTime before;
    private final LocalDateTime after;

    /**
     * Creates an StatsIngredientCommand to add the specified {@code Command}.
     * On takes precedence over before and after.
     * If on is specified together with before and after, only 'on' is considered.
     */
    public StatsIngredientDateCommand(LocalDateTime on, LocalDateTime before,
                                LocalDateTime after) {
        this.on = on;
        if (this.on != null) {
            this.before = null;
            this.after = null;
        } else {
            this.before = before;
            this.after = after;
        }
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        String output;
        if (on != null) {
            output = model.getIngredientUsageList().getUsagesBetween(on, on.plusDays(1)).toString();
        } else {
            output = model.getIngredientUsageList().getUsagesBetween(before, after).toString();
        }

        return CommandResult.message(String.format("Here is the list of ingredient %s", output));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof StatsIngredientDateCommand
            && this.on.equals(((StatsIngredientDateCommand) other).on)
            && this.before.equals(((StatsIngredientDateCommand) other).before)
            && this.after.equals(((StatsIngredientDateCommand) other).after));
    }

    @Override
    public String toString() {
        return String.format("StatsIngredientDateCommand");
    }
}
