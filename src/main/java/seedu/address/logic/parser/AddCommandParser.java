package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG_NAME, PREFIX_FILE_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG_NAME, PREFIX_FILE_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }



        TagName tagName = ParserUtil.parseTagName(argMultimap.getValue(PREFIX_TAG_NAME).get());
        FileAddress fileAddress = ParserUtil.parseFileAddress(argMultimap.getValue(PREFIX_FILE_ADDRESS).get());

        Tag tag = new Tag(tagName, fileAddress);


        return new AddCommand(tag);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
