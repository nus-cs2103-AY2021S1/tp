package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TestCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class TestCommandParser implements Parser<TestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TestCommand
     * and returns an TestCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TestCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ANSWER, PREFIX_OPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TestCommand.MESSAGE_USAGE), pe);
        }

        TestCommand.TestAnswerDescriptor testAnswerDescriptor = new TestCommand.TestAnswerDescriptor();
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            testAnswerDescriptor.setAnswer(ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get()));
        }
        if (argMultimap.getValue(PREFIX_OPTION).isPresent()) {
            testAnswerDescriptor.setOption(ParserUtil.parseOption(argMultimap.getValue(PREFIX_OPTION).get()));
        }

        if (!testAnswerDescriptor.isAnyFieldPresent()) {
            throw new ParseException(TestCommand.MESSAGE_NO_OPTION_OR_ANSWER_PROVIDED);
        }

        return new TestCommand(index, testAnswerDescriptor);
    }

}
