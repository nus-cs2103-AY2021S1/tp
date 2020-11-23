package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DELETE_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NEW_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.logic.commands.TagCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.tag.Tag;

/**
 * Parses input arguments and creates a new TagCommand object.
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagCommand
     * and returns an TagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NEW_TAG, PREFIX_DELETE_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NEW_TAG, PREFIX_DELETE_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagCommand.MESSAGE_USAGE), ive);
        }
        assert index != null : "Index of task should not be null here.";

        Set<Tag> tagListToAdd = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_NEW_TAG));
        Set<Tag> tagListToDelete = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_DELETE_TAG));

        return new TagCommand(index, tagListToAdd, tagListToDelete);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
