package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;

public abstract class QuestionCommand extends Command {

    public static final String COMMAND_WORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, resolves or deletes a question "
            + "from a student in Reeve.\n\n"
            + "Example:\n"
            + "To add a new question:\n"
            + COMMAND_WORD + " add 1 " + PREFIX_TEXT + "How do birds fly?\n"
            + "(adds a new question to the first student on the list)\n\n"
            + "To resolve a question:\n"
            + COMMAND_WORD + " solve 1 " + PREFIX_INDEX + "1 " + PREFIX_TEXT + "They don't.\n"
            + "(solves the first question from the first student on the list)\n\n"
            + "To delete a question:\n"
            + COMMAND_WORD + " delete 1 " + PREFIX_INDEX + "1\n"
            + "(deletes the first question from the first student on the list)";
}
