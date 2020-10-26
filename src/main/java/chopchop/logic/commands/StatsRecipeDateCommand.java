package chopchop.logic.commands;

import static chopchop.commons.util.Strings.ARG_AFTER;
import static chopchop.commons.util.Strings.ARG_BEFORE;
import static chopchop.commons.util.Strings.ARG_ON;

import java.time.LocalDateTime;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeDateCommand extends Command {
    public static final String COMMAND_WORD = "stats recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of recipes. "
        + "Parameters: "
        + "[" + ARG_ON + " DATE] "
        + "[" + ARG_BEFORE + "DATE] "
        + "[" + ARG_AFTER + "DATE] "
        + "Example " + COMMAND_WORD + " " + ARG_BEFORE + " 2020-02-13";

    private final LocalDateTime on;
    private final LocalDateTime before;
    private final LocalDateTime after;

    /**
     * Creates an StatsRecipeDateCommand to add the specified {@code Ingredient}.
     * On takes precedence over before and after.
     * If on is specified together with before and after, only 'on' is considered.
     */
    public StatsRecipeDateCommand(LocalDateTime on, LocalDateTime before,
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
            output = model.getRecipeUsageList().getUsagesBetween(on, on.plusDays(1)).toString();
        } else {
            output = model.getRecipeUsageList().getUsagesBetween(before, after).toString();
        }

        return CommandResult.message(String.format("Here is the list of recipes %s", output));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof StatsRecipeDateCommand
            && this.on.equals(((StatsRecipeDateCommand) other).on)
            && this.before.equals(((StatsRecipeDateCommand) other).before)
            && this.after.equals(((StatsRecipeDateCommand) other).after));
    }

    @Override
    public String toString() {
        return String.format("StatsRecipeDateCommand");
    }
}
