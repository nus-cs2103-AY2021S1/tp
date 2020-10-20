package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOLVE_QUESTION;

public abstract class QuestionCommand extends Command {

    public static final String COMMAND_WORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, resolves or deletes a question "
            + "from a student in Reeve. "
            + "Supported actions: \n"
            + PREFIX_ADD_QUESTION + " QUESTION: adds an unresolved question.\n"
            + "Examples:\n" + COMMAND_WORD + " 1 " + PREFIX_ADD_QUESTION + "1 + 1 = ?\n"
            + PREFIX_SOLVE_QUESTION + "QUESTION_INDEX SOLUTION: marks a question as solved.\n"
            + "Examples:\n" + COMMAND_WORD + " 2 " + PREFIX_SOLVE_QUESTION + " 1 Read your textbook.\n"
            + PREFIX_DELETE_QUESTION + "QUESTION_INDEX: deletes a question.\n"
            + "Examples:\n" + COMMAND_WORD + " 2 " + PREFIX_DELETE_QUESTION + " 1 \n";
}
