package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_TEXT;

public abstract class QuestionCommand extends Command {

    public static final String COMMAND_WORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, resolves or deletes a question "
            + "from a student in Reeve. "
            + "Supported actions: \n"
            + PREFIX_DETAIL_TEXT + " QUESTION: adds an unresolved question.\n"
            + "Examples:\n" + COMMAND_WORD + "add 1 " + PREFIX_DETAIL_TEXT + "1 + 1 = ?\n"
            + PREFIX_DETAIL_INDEX + "QUESTION_INDEX " + PREFIX_DETAIL_TEXT + "SOLUTION: marks a question as solved.\n"
            + "Examples:\n" + COMMAND_WORD + "solve 1 " + PREFIX_DETAIL_INDEX + " 1 "
            + PREFIX_DETAIL_TEXT + "Read your textbook.\n"
            + PREFIX_DETAIL_INDEX + "QUESTION_INDEX: deletes a question.\n"
            + "Examples:\n" + COMMAND_WORD + "delete 2 " + PREFIX_DETAIL_INDEX + " 1 \n";
}
