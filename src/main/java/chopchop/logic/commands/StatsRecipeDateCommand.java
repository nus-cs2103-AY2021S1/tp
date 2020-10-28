package chopchop.logic.commands;

import static chopchop.commons.util.Strings.ARG_AFTER;
import static chopchop.commons.util.Strings.ARG_BEFORE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeDateCommand extends Command {
    public static final String COMMAND_WORD = "stats recipe made";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of recipes. "
        + "Parameters: "
        + "[" + ARG_BEFORE + " DATE] "
        + "[" + ARG_AFTER + " DATE] "
        + "Example: " + COMMAND_WORD + " " + ARG_BEFORE + " 2020-02-13 " + ARG_AFTER + " 2020-01-13";

    private final LocalDateTime before;
    private final LocalDateTime after;

    /**
     * Creates an StatsRecipeDateCommand to add the specified {@code Ingredient}.
     * On takes precedence over before and after.
     * If on is specified together with before and after, only 'on' is considered.
     */
    public StatsRecipeDateCommand(LocalDateTime before, LocalDateTime after) {
        if (before == null && after == null) {
            var now = LocalDateTime.now();
            this.before = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
            this.after = this.before.plusDays(1);
        } else {
            this.before = before;
            this.after = after;
        }
    }

    private String getMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String msg;
        if (this.before != null && this.after != null) {
            var before = this.before.format(formatter);
            var after = this.after.format(formatter);
            if (this.before.plusDays(1).equals(this.after)) {
                msg = String.format("Here is the list of ingredients used on %s", before);
            } else {
                msg = String.format("Here is the list of ingredients used from the period %s to %s", after, before);
            }
        } else if (this.before != null) {
            var before = this.before.format(formatter);
            msg = String.format("Here is the list of ingredients used before %s", before);
        } else {
            var before = this.after.format(formatter);
            msg = String.format("Here is the list of ingredients used after %s", before);
        }
        return msg;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        var output = model.getRecipeUsageList().getUsagesBetween(before, after);
        return CommandResult.statsMessage(output, getMessage());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof StatsRecipeDateCommand
            && this.before.equals(((StatsRecipeDateCommand) other).before)
            && this.after.equals(((StatsRecipeDateCommand) other).after));
    }

    @Override
    public String toString() {
        return String.format("StatsRecipeDateCommand");
    }

    public static String getCommandString() {
        return "stats recipe";
    }

    public static String getCommandHelp() {
        return "Shows recipes that were cooked in a given timeframe";
    }

    public static String getUserGuideSection() {
        throw new RuntimeException("Travis pls implement this");
    }
}
