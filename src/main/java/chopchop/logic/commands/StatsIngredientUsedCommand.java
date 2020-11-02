package chopchop.logic.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsIngredientUsedCommand extends Command {

    private final LocalDateTime before;
    private final LocalDateTime after;

    /**
     * Creates an StatsIngredientCommand to add the specified {@code Command}.
     * If both before and after are not specified. It is assumed that the time frame is today.
     */
    public StatsIngredientUsedCommand(LocalDateTime after, LocalDateTime before) {
        if (before == null && after == null) {
            var now = LocalDateTime.now();
            this.after = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
            this.before = this.after.plusDays(1);
        } else {
            this.before = before;
            this.after = after;
        }
    }

    private String getMessage(boolean isEmpty) {
        DateTimeFormatter onFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        String msg;
        if (this.before != null && this.after != null) {
            var before = this.before.format(formatter);
            var after = this.after.format(formatter);
            var onAfter = this.after.format(onFormatter);
            if (this.after.plusDays(1).equals(this.before)) {
                msg = String.format(isEmpty ? "No ingredients were used on %s"
                                            : "Showing ingredients used on %s", onAfter);
            } else {
                msg = String.format(isEmpty ? "No ingredients were used between %s and %s"
                                            : "Showing ingredients used between %s and %s", after, before);
            }
        } else if (this.before != null) {
            var before = this.before.format(formatter);
            msg = String.format(isEmpty ? "No ingredients were used before %s"
                                        : "Showing ingredients used before %s", before);
        } else {
            var before = this.after.format(formatter);
            msg = String.format(isEmpty ? "No ingredients were used after %s"
                                        : "Showing ingredients used after %s", before);
        }
        return msg;
    }

    @Override
    public CommandResult execute(Model model, HistoryManager historyManager) {
        var output = model.getIngredientUsageList().getUsagesBetween(after, before);
        return CommandResult.statsMessage(output, getMessage(output.isEmpty()));
    }

    @Override
    public String toString() {
        return String.format("StatsIngredientUsedCommand");
    }

    public static String getCommandString() {
        return "stats ingredient used";
    }

    public static String getCommandHelp() {
        return "Shows ingredients used by recipes that were made in a given time frame";
    }
}
