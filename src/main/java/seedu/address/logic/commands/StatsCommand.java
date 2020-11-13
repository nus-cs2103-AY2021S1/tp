package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_VIEW_STATS;

import seedu.address.model.Model;

/**
 * Shows the respective statistics in a new window
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the relevant statistics. \n"
            + "Options: " + COMMAND_WORD + " " + PREFIX_VIEW_STATS + "all \n"
            + COMMAND_WORD + " " + PREFIX_VIEW_STATS + "calorie \n"
            + COMMAND_WORD + " " + PREFIX_VIEW_STATS + "weight \n";

    public static final String SHOWING_ALL_STATS_MESSAGE = "Opened both stats window.";
    public static final String SHOWING_CALORIE_STATS_MESSAGE = "Opened calories stats window.";
    public static final String SHOWING_WEIGHT_STATS_MESSAGE = "Opened weight stats window.";
    public static final String SHOWING_COMMANDER_STATS_MESSAGE = "Opened commander stats window";

    private boolean showCalorie;
    private boolean showWeight;
    private boolean showCommander;

    /**
     * @param showCalorie to show the calorie statistics
     * @param showWeight to show the weight statistics
     */
    public StatsCommand(boolean showCalorie, boolean showWeight, boolean showCommander) {
        this.showCalorie = showCalorie;
        this.showWeight = showWeight;
        this.showCommander = showCommander;
    }

    @Override
    public CommandResult execute (Model model) {
        if (!showCalorie && !showWeight && showCommander) {
            return new CommandResult(SHOWING_COMMANDER_STATS_MESSAGE, false, false, false, true);
        } else if (showCalorie && !showWeight) { /* Show calorie statistics only*/
            return new CommandResult(SHOWING_CALORIE_STATS_MESSAGE, false, true, false, false);
        } else if (!showCalorie && showWeight) { /* Show weight statistics only*/
            return new CommandResult(SHOWING_WEIGHT_STATS_MESSAGE, false, false, true, false);
        } else { /* Show both statistics */
            return new CommandResult(SHOWING_ALL_STATS_MESSAGE, true, false, false, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this //short circuit if same object
                || (other instanceof StatsCommand //instanceof handles nulls
                && showCalorie == ((StatsCommand) other).showCalorie
                && showWeight == ((StatsCommand) other).showWeight
                && showCommander == ((StatsCommand) other).showCommander);
    }
}
