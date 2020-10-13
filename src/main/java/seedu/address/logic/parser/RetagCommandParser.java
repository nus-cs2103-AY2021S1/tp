package seedu.address.logic.parser;

import seedu.address.logic.commands.RetagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagName;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class RetagCommandParser implements Parser<RetagCommand> {

    public RetagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OLD_TAG_NAME, PREFIX_TAG_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_OLD_TAG_NAME, PREFIX_TAG_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RetagCommand.MESSAGE_USAGE));
        }

        TagName oldTagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_OLD_TAG_NAME).get());
        TagName newTagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAG_NAME).get());

        return new RetagCommand(oldTagName, newTagName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
