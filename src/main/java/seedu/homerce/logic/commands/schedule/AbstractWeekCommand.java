package seedu.homerce.logic.commands.schedule;

import seedu.homerce.logic.commands.Command;

/** Wrapper class for both current and custom week commands. It is unknown whether
 * the user's schedule command has no parameters or contains a date. Thus, the parser
 * has to account for both scenarios by returning an abstract week command that is
 * either to display the current week or a custom week.*/
public abstract class AbstractWeekCommand extends Command {
    public static final String COMMAND_WORD = "schedule";
}
