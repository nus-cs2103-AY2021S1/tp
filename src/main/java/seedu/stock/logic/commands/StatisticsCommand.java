package seedu.stock.logic.commands;

public abstract class StatisticsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = "The following are the different statistics you can display:\n"
            + "stats st/source: Shows statistics for source companies\n"
            + "stats st/source-qd-<source company>: Shows statistics for the quantity "
            + "distribution in a particular source company\n"
            + "Parameters:\n"
            + "st/source\n"
            + "st/source-qd-<source company>";
}
