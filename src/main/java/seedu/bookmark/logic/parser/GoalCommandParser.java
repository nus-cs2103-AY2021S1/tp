package seedu.bookmark.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_PAGE;

import java.util.stream.Stream;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.GoalCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Goal;


public class GoalCommandParser implements Parser<GoalCommand> {

    @Override
    public GoalCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PAGE, PREFIX_DEADLINE);

        Index index;

        // Check index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_PAGE, PREFIX_DEADLINE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalCommand.MESSAGE_USAGE));
        }

        assert(arePrefixesPresent(argMultimap, PREFIX_PAGE, PREFIX_DEADLINE));

        Goal readerGoal = ParserUtil.parseGoal(argMultimap.getValue(PREFIX_PAGE).get(),
                                                argMultimap.getValue(PREFIX_DEADLINE).get());
        return new GoalCommand(index, readerGoal);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
