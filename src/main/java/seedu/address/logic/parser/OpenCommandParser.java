package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.OpenCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.tag.TagName;

public class OpenCommandParser implements Parser<OpenCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the OpenCommand
     * and returns an OpenCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public OpenCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG_NAME, PREFIX_LABEL_NAME);

        // Use XOR to ensure only 1 argument (t/ or l/) is present
        boolean hasTagName = arePrefixesPresent(argMultimap, PREFIX_TAG_NAME);
        boolean hasLabelName = arePrefixesPresent(argMultimap, PREFIX_LABEL_NAME);
        boolean hasOnlyOneArg = hasTagName ^ hasLabelName;

        if (!hasOnlyOneArg || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, OpenCommand.MESSAGE_USAGE));
        }

        if (hasTagName) {
            TagName tagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAG_NAME).get());
            return new OpenCommand(tagName);
        } else {
            Label labelName = ParserUtil.parseLabel(argMultimap.getValue(PREFIX_LABEL_NAME).get());
            return new OpenCommand(labelName);
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
