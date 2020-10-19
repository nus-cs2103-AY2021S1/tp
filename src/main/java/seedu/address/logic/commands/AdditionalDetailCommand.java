package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_DETAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_DETAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT_DETAIL;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_QUESTION;

public abstract class AdditionalDetailCommand extends Command {

    public static final String COMMAND_WORD = "detail";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, edits or deletes an Additional Detail "
            + "from a student in Reeve. ";
}
