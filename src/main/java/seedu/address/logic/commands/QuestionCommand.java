package seedu.address.logic.commands;

import seedu.address.logic.parser.Prefix;

public abstract class QuestionCommand extends Command {

    public static final String COMMAND_WORD = "question";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds, resolves or deletes a question "
            + "from a student in Reeve. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Supported actions: \n"
            + QuestionCommandPrefix.ADD_QUESTION_PREFIX
            + " QUESTION: adds an unresolved question.\n"
            + "Examples:\n"
            + COMMAND_WORD + " 1 " + QuestionCommandPrefix.ADD_QUESTION_PREFIX
            + "What is 1 + 1 = ?";

    /**
     * Represents all prefixes used by QuestionCommand.
     * While it overlaps with CliSyntax prefixes, this is to prevent ambiguities since
     * the prefixes mean different fields in the context of Question Command.
     */
    public static class QuestionCommandPrefix {
        public static final Prefix ADD_QUESTION_PREFIX = new Prefix("a/");
        public static final Prefix SOLVE_QUESTION_PREFIX = new Prefix("s/");

        /**
         * Returns all prefixes used by QuestionCommand.
         */
        public static final Prefix[] PREFIX_LIST =
                new Prefix[] {ADD_QUESTION_PREFIX, SOLVE_QUESTION_PREFIX};
    }

}
