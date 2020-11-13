package seedu.jarvis.logic.commands.edit;

import seedu.jarvis.logic.commands.Command;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    public static final String MESSAGE_USAGE =
            COMMAND_WORD + ": Edits student, login, or mastery check information stored in Jarvis.\n"
            + "edit -l [u/LUMINUS_USERNAME] [p/LUMINUS_PASSWORD]: Edit the login information of the current user\n"
            + "edit -s STUDENT_ID [n/NAME] [t/TELEGRAM] [e/EMAIL]: Edit the student with STUDENT_ID\n"
            + "edit -mc MASTERYCHECK_ID s/SCORE: Edit the score of the mastery check with MASTERYCHECK_ID\n\n"
            + "To check the IDs of students and mastery checks, "
            + "enter view -s and view -mc respectively and take note of the leftmost identifier of each item.\n"
            + "At least one of the optional parameters must be provided for -l and -s, "
            + "and the SCORE for -mc can only be 0 or 1, indicating FAIL and PASS respectively.";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
