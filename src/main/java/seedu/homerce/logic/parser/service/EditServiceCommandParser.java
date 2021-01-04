package seedu.homerce.logic.parser.service;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_DURATION;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_PRICE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_TITLE;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.service.EditServiceCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new EditServiceCommand object
 */
public class EditServiceCommandParser implements Parser<EditServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of
     * the EditServiceCommand and returns an EditServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditServiceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SERVICE_TITLE, PREFIX_SERVICE_DURATION,
                PREFIX_SERVICE_PRICE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditServiceCommand.MESSAGE_USAGE), pe);
        }

        EditServiceCommand.EditServiceDescriptor editServiceDescriptor = new EditServiceCommand.EditServiceDescriptor();
        if (argMultimap.getValue(PREFIX_SERVICE_TITLE).isPresent()) {
            editServiceDescriptor.setTitle(ParserUtil.parseTitle(
                    argMultimap.getValue(PREFIX_SERVICE_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_SERVICE_DURATION).isPresent()) {
            editServiceDescriptor.setDuration(ParserUtil.parseDuration(argMultimap.getValue(PREFIX_SERVICE_DURATION)
                .get()));
        }
        if (argMultimap.getValue(PREFIX_SERVICE_PRICE).isPresent()) {
            editServiceDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_SERVICE_PRICE).get()));
        }
        return new EditServiceCommand(index, editServiceDescriptor);
    }
}
