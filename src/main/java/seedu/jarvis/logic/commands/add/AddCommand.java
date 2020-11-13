package seedu.jarvis.logic.commands.add;

import seedu.jarvis.logic.commands.Command;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;

/**
 * Adds a student to the jarvis book.
 */
public abstract class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_ADD_USAGE =
            COMMAND_WORD + ": Adds a consultation, mastery check, or task (todo, event, deadline) to Jarvis.\n"
            + "add -c NAME d/YYYY-MM-DD t/HH:MM: Add consultation with student with NAME at specified date and time\n"
            + "add -mc NAME d/YYYY-MM-DD t/HH:MM: Add mastery check with student with NAME at specified date and "
            + "time\n"
            + "add -t DESCRIPTION: Add todo with DESCRIPTION\n"
            + "add -e DESCRIPTION d/YYYY-MM-DD t/HH:MM: Add event with DESCRIPTION at specified date and time\n"
            + "add -d DESCRIPTION d/YYYY-MM-DD t/HH:MM: Add deadline with DESCRIPTION at specified date and time";

    public static final String MESSAGE_MISSING_DESCRIPTION = "Please include the DESCRIPTION";
    public static final String MESSAGE_MISSING_INFO_TASK = "When adding an Event or Deadline, please make sure to "
            + "include all of DESCRIPTION, DATE, and TIME in the correct order as listed here: DESCRIPTION "
            + "d/YYYY-MM-DD t/HH:MM";
    public static final String MESSAGE_MISSING_DATE = "Please make sure to include DATE and TIME: d/YYYY-MM-DD t/HH:MM";
    public static final String MESSAGE_MISSING_INFO = "please make sure to include all of NAME, DATE, and TIME in the "
            + "correct order as listed here: NAME d/YYYY-MM-DD t/HH:MM";
    public static final String MESSAGE_MISSING_INFO_CONSULTATION = "When adding a Consultation or Mastery Check, "
            + MESSAGE_MISSING_INFO;
    public static final String MESSAGE_INVALID_DATETIME = "The provided date and time is incorrect.\nBe careful of "
            + "leap years, number of months in a year and the number of days in a specific month and use the format "
            + "d/YYYY-MM-DD t/HH:MM.";

    public abstract CommandResult execute(Model model) throws CommandException;

}
