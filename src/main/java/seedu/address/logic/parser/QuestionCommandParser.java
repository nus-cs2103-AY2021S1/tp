package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.QuestionCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOLVE_QUESTION;
import static seedu.address.logic.parser.CliSyntax.QUESTION_COMMAND_PREFIXES;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.question.Question;

/**
 * Parses input arguments and creates a QuestionCommand.
 */
public class QuestionCommandParser implements Parser<QuestionCommand> {

    /**
     * Parses the given {@code String} in the context of a QuestionCommand
     * and returns a QuestionCommand for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public QuestionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, QUESTION_COMMAND_PREFIXES);

        if (!hasOnlyOnePrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index index = getIndex(argMultimap);
        if (argMultimap.getValue(PREFIX_ADD_QUESTION).isPresent()) {
            Question question = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_ADD_QUESTION).get());
            return new AddQuestionCommand(index, question);
        }
        if (argMultimap.getValue(PREFIX_SOLVE_QUESTION).isPresent()) {
            Index questionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SOLVE_QUESTION).get());
            return new SolveQuestionCommand(index, questionIndex);
        }
        if (argMultimap.getValue(PREFIX_DELETE_QUESTION).isPresent()) {
            Index questionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DELETE_QUESTION).get());
            return new DeleteQuestionCommand(index, questionIndex);
        }
        throw new AssertionError("This stage should not be reachable.");
    }

    private Index getIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

    private boolean hasOnlyOnePrefix(ArgumentMultimap argMultimap) {
        int prefixesDetected = 0;
        for (Prefix prefix : QUESTION_COMMAND_PREFIXES) {
            if (argMultimap.getValue(prefix).isPresent()) {
                prefixesDetected++;
            }
        }
        return prefixesDetected == 1;
    }

}
