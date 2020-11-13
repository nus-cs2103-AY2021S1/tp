package seedu.jarvis.logic.commands.delete;

import seedu.jarvis.logic.commands.Command;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;

/**
 * Deletes a student identified using it's displayed index from the jarvis book.
 */
public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Deletes a consultation, mastery check, or task (todo, event, deadline) from Jarvis.\n"
            + "delete -c CONSULTATION_ID: Delete consultation with CONSULTATION_ID\n"
            + "delete -mc MASTERYCHECK_ID: Delete mastery check with MASTERYCHECK_ID\n"
            + "delete -t TASK_ID: Delete task with TASK_ID\n\n"
            + "To check the IDs of consultations, mastery checks, and tasks, "
            + "enter view -c, view -mc, view -t respectively and take note of the leftmost identifier of each item.";

    public abstract CommandResult execute(Model model) throws CommandException;

}
