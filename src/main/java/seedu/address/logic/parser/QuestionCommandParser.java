package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.QuestionCommand.MESSAGE_USAGE;
import static seedu.address.logic.commands.QuestionCommand.QuestionCommandPrefix.ADD_QUESTION_PREFIX;
import static seedu.address.logic.commands.QuestionCommand.QuestionCommandPrefix.PREFIX_LIST;
import static seedu.address.logic.commands.QuestionCommand.QuestionCommandPrefix.SOLVE_QUESTION_PREFIX;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Question;

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
                ArgumentTokenizer.tokenize(args, PREFIX_LIST);

        if (hasMoreThanOnePrefix(argMultimap)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index index = getIndex(argMultimap);
        if (argMultimap.getValue(ADD_QUESTION_PREFIX).isPresent()) {
            Question question = ParserUtil.parseQuestion(argMultimap.getValue(ADD_QUESTION_PREFIX).get());
            return new AddQuestionCommand(index, question);
        }
        if (argMultimap.getValue(SOLVE_QUESTION_PREFIX).isPresent()) {
            Index questionIndex = ParserUtil.parseIndex(argMultimap.getValue(SOLVE_QUESTION_PREFIX).get());
            return new SolveQuestionCommand(index, questionIndex);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    private Index getIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

    private boolean hasMoreThanOnePrefix(ArgumentMultimap argMultimap) {
        int prefixesDetected = 0;
        for (Prefix prefix : PREFIX_LIST) {
            if (argMultimap.getValue(prefix).isPresent()) {
                prefixesDetected++;
            }
        }
        return prefixesDetected > 1;
    }
}
