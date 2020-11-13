package seedu.jarvis.logic.commands.view;

import seedu.jarvis.logic.commands.Command;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;

/**
 * Lists all persons in the jarvis book to the user.
 */
public abstract class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Lists various information according to the command inputted.\n"
            + "view -s [NAME]: View a list of all students OR if [NAME] is provided only the information for the "
            + "student with the name corresponding NAME\n"
            + "view -m: View all missions\n"
            + "view -q: View all quests\n"
            + "view -um: View ungraded missions\n"
            + "view -uq: View ungraded quests\n"
            + "view -c: View all consultation sessions\n"
            + "view -cp: View past consultation sessions\n"
            + "view -cu: View upcoming consultation sessions\n"
            + "view -mc: View all mastery check sessions\n"
            + "view -mcp: View past mastery check sessions\n"
            + "view -mcu: View upcoming mastery check sessions\n"
            + "view -t: View all tasks (todos, events, deadlines)\n"
            + "view -tt: View all todos only\n"
            + "view -te: View all events only\n"
            + "view -td: View all deadlines only";

    public abstract CommandResult execute(Model model) throws CommandException;

}

