package seedu.stock.logic.commands;

public abstract class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = "The following are the different statistics you can display:\n"
            + "stats st/source: Shows statistics for source companies\n"
            + "Parameters: No parameters\n";
}
