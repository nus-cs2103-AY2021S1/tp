package seedu.address.logic.parser.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itinerary.EditItineraryCommand;
import seedu.address.logic.commands.itinerary.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditItineraryCommand object
 */
public class EditItineraryCommandParser implements Parser<EditItineraryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditItineraryCommand
     * and returns an EditItineraryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditItineraryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_START_DATE,
                PREFIX_END_DATE, PREFIX_BUDGET);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditItineraryCommand.MESSAGE_USAGE), pe);
        }

        EditItineraryDescriptor editItineraryDescriptor = new EditItineraryDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editItineraryDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editItineraryDescriptor
                .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            editItineraryDescriptor.setStartDate(ParserUtil.parseItineraryDate(argMultimap.getValue(PREFIX_START_DATE)
                .get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            editItineraryDescriptor.setEndDate(ParserUtil.parseItineraryDate(argMultimap.getValue(PREFIX_END_DATE)
                .get()));
        }

        if (argMultimap.getValue(PREFIX_BUDGET).isPresent()) {
            editItineraryDescriptor.setBudget(ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get()));
        }

        if (!editItineraryDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditItineraryCommand.MESSAGE_NOT_EDITED);
        }

        return new EditItineraryCommand(index, editItineraryDescriptor);
    }
}
