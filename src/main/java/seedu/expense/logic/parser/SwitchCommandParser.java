package seedu.expense.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.logic.commands.SwitchCommand;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.tag.Tag;

/**
 * Parses input arguments and creates a new SwitchCommand object
 */
public class SwitchCommandParser implements Parser<SwitchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code SwitchCommand}
     * and returns a {@code SwitchCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SwitchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
        }

        Tag category;
        try {
            category = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE), ive);
        }
        return new SwitchCommand(category);
    }

}
