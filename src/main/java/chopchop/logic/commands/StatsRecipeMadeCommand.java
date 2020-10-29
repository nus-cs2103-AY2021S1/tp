package chopchop.logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsRecipeMadeCommand extends Command {

    private final LocalDateTime before;
    private final LocalDateTime after;

    /**
     * Creates an StatsRecipeDateCommand to add the specified {@code Ingredient}.
     * If both before and after are not specified, it is assumed that the time frame is today.
     */
    public StatsRecipeMadeCommand(LocalDateTime after, LocalDateTime before) {
        if (after == null && before == null) {
            var now = LocalDateTime.now();
            this.after = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
            this.before = this.after.plusDays(1);
        } else {
            this.after = after;
            this.before = before;
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
            if (this.after.plusDays(1).equals(this.before)) {
                msg = String.format("Here is a list of recipes made on %s", onBefore);
            } else {
                msg = String.format("Here is a list of recipes made from the period %s to %s", after, before);
            }
        } else if (this.before != null) {
            var before = this.before.format(formatter);
            msg = String.format("Here is a list of recipes made before %s", before);
        } else {
            var before = this.after.format(formatter);
            msg = String.format("Here is a list of recipes made after %s", before);
        }
        return msg;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) throws CommandException {
        var output = model.getRecipeUsageList().getUsagesBetween(after, before);
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
}
