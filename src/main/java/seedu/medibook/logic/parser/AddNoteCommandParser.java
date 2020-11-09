package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.stream.Stream;

import seedu.medibook.logic.commands.AddNoteCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.medicalnote.Content;

/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddNoteCommand
     * and returns a AddNoteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_CONTENT);

            if (!arePrefixesPresent(argMultimap, PREFIX_CONTENT)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
            }

            Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).orElse(Date.getTodayDate()));
            Content content = ParserUtil.parseContent(argMultimap.getValue(PREFIX_CONTENT).get());

            return new AddNoteCommand(date, content);
        } catch (IllegalArgumentException iae) {
            throw new ParseException(iae.getMessage());
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
