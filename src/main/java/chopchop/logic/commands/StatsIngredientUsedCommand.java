package chopchop.logic.commands;

import static chopchop.commons.util.Strings.ARG_AFTER;
import static chopchop.commons.util.Strings.ARG_BEFORE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;

public class StatsIngredientUsedCommand extends Command {
    public static final String COMMAND_WORD = "stats ingredient used";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a list of ingredient. "
        + "Parameters: "
        + "[" + ARG_BEFORE + " DATE] "
        + "[" + ARG_AFTER + " DATE] "
        + "Example: " + COMMAND_WORD + " " + ARG_BEFORE + " 2020-02-13 ";

    private final LocalDateTime before;
    private final LocalDateTime after;

    /**
     * Creates an StatsIngredientCommand to add the specified {@code Command}.
     * If both before and after are not specified. It is assumed that the time frame is today.
     */
    public StatsIngredientUsedCommand(LocalDateTime before, LocalDateTime after) {
        if (before == null && after == null) {
            var now = LocalDateTime.now();
            this.before = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfYear(), 0, 0, 0);
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
                msg = String.format("Here is a list of ingredients made on %s", before);
            } else {
                msg = String.format("Here is a list of ingredients made from the period %s to %s", after, before);
            }
        } else if (this.before != null) {
            var before = this.before.format(formatter);
            msg = String.format("Here is a list of ingredients made before %s", before);
        } else {
            var before = this.after.format(formatter);
            msg = String.format("Here is a list of ingredients made after %s", before);
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
            || (other instanceof StatsIngredientUsedCommand
            && this.before.equals(((StatsIngredientUsedCommand) other).before)
            && this.after.equals(((StatsIngredientUsedCommand) other).after));
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

    public static String getUserGuideSection() {
        throw new RuntimeException("listing-ingredients-used-in-a-given-time-frame-statsingredientused");
    }
}
