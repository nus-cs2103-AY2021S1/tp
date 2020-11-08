package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;
import static seedu.address.logic.parser.ReeveParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddQuestionCommand;
import seedu.address.logic.commands.DeleteQuestionCommand;
import seedu.address.logic.commands.QuestionCommand;
import seedu.address.logic.commands.SolveQuestionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.academic.question.UnsolvedQuestion;

/**
 * Parses input arguments and creates a QuestionCommand.
 */
public class QuestionCommandParser extends PrefixDependentParser<QuestionCommand> {

    private static final String MESSAGE_INVALID_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, QuestionCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_ADD_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddQuestionCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_SOLVE_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SolveQuestionCommand.MESSAGE_USAGE);
    private static final String MESSAGE_INVALID_DEL_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteQuestionCommand.MESSAGE_USAGE);

    /**
     * Parses the given {@code String} in the context of a QuestionCommand
     * and returns a QuestionCommand for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public QuestionCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND);
        }

        String commandWord = matcher.group("commandWord");
        String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddQuestionCommand.COMMAND_WORD:
            return parseAddQuestionCommand(arguments);

        case DeleteQuestionCommand.COMMAND_WORD:
            return parseDeleteQuestionCommand(arguments);

        case SolveQuestionCommand.COMMAND_WORD:
            return parseSolveQuestionCommand(arguments);

        default:
            throw new ParseException(MESSAGE_INVALID_COMMAND);
        }
    }

    //======Parsers=====//

    private AddQuestionCommand parseAddQuestionCommand(String input) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, PREFIX_TEXT);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_TEXT)) {
            throw new ParseException(MESSAGE_INVALID_ADD_COMMAND);
        }

        Index studentIndex;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_ADD_COMMAND, pe);
        }

        UnsolvedQuestion questionToAdd = ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_TEXT).get());

        return new AddQuestionCommand(studentIndex, questionToAdd);
    }

    private SolveQuestionCommand parseSolveQuestionCommand(String input) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, COMMAND_PREFIXES)) {
            throw new ParseException(MESSAGE_INVALID_SOLVE_COMMAND);
        }

        Index studentIndex;
        Index questionIndex;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            questionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_SOLVE_COMMAND, pe);
        }

        String solution = ParserUtil.parseSolution(argMultimap.getValue(PREFIX_TEXT).get());
        return new SolveQuestionCommand(studentIndex, questionIndex, solution);
    }

    private DeleteQuestionCommand parseDeleteQuestionCommand(String input) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input, PREFIX_INDEX);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_INDEX)) {
            throw new ParseException(MESSAGE_INVALID_DEL_COMMAND);
        }

        Index studentIndex;
        Index questionIndex;
        try {
            studentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            questionIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_DEL_COMMAND, pe);
        }

        return new DeleteQuestionCommand(studentIndex, questionIndex);
    }

}
