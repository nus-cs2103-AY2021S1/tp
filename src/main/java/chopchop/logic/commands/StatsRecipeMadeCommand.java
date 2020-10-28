package chopchop.logic.commands;

import static chopchop.commons.util.Strings.ARG_AFTER;
import static chopchop.commons.util.Strings.ARG_BEFORE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeMadeCommand extends Command {
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
     * If both before and after are not specified, it is assumed that the time frame is today.
     */
    public StatsRecipeMadeCommand(LocalDateTime before, LocalDateTime after) {
        if (before == null && after == null) {
            var now = LocalDateTime.now();
            this.before = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
            this.after = this.before.plusDays(1);
        } else {
            this.before = before;
            this.after = after;
        }
    }

    private String getMessage() {
        DateTimeFormatter onFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String msg;
        if (this.before != null && this.after != null) {
            var onBefore = this.before.format(onFormatter);
            var before = this.before.format(formatter);
            var after = this.after.format(formatter);
            if (this.before.plusDays(1).equals(this.after)) {
                msg = String.format("Here is a list of ingredients used on %s", onBefore);
            } else {
                msg = String.format("Here is a list of ingredients used from the period %s to %s", after, before);
            }
        } else if (this.before != null) {
            var before = this.before.format(formatter);
            msg = String.format("Here is a list of ingredients used before %s", before);
        } else {
            var before = this.after.format(formatter);
            msg = String.format("Here is a list of ingredients used after %s", before);
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
            || (other instanceof StatsRecipeMadeCommand
            && this.before.equals(((StatsRecipeMadeCommand) other).before)
            && this.after.equals(((StatsRecipeMadeCommand) other).after));
    }

    @Override
    public String toString() {
        return String.format("StatsRecipeMadeCommand");
    }

    public static String getCommandString() {
        return "stats recipe made";
    }

    public static String getCommandHelp() {
        return "Shows recipes that were made in a given time frame";
    }

    public static String getUserGuideSection() {
        throw new RuntimeException("listing-recipes-made-in-a-given-time-frame--statsrecipemade");
    }
}
