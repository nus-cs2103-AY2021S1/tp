package seedu.fma.logic.parser;

import static seedu.fma.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_C;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_E;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_R;

import seedu.fma.logic.commands.AddCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args, ReadOnlyLogBook logBook) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_E, PREFIX_R, PREFIX_C);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_E, PREFIX_R, PREFIX_C)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Exercise exercise = ParserUtil.parseExercise(argMultimap.getValue(PREFIX_E).get(), logBook);
        Rep rep = ParserUtil.parseRep(argMultimap.getValue(PREFIX_R).get());
        Comment comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_C).get());

        Log log = new Log(exercise, rep, comment);

        return new AddCommand(log);
    }

}
