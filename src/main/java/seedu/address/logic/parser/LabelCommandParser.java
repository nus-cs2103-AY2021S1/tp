package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.LabelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.tag.TagName;

/**
 * Parses input arguments and creates a new LabelCommand object
 */
public class LabelCommandParser implements Parser<LabelCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LabelCommand
     * and returns an LabelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LabelCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG_NAME, PREFIX_LABEL_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG_NAME, PREFIX_LABEL_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LabelCommand.MESSAGE_USAGE));
        }

        TagName tagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAG_NAME).get());
        Set<Label> labels = ParserUtil.parseLabels(argMultimap.getAllValues(PREFIX_LABEL_NAME));

        return new LabelCommand(tagName, labels);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
