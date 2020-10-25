package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddAdditionalDetailCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEXT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAdditionalDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.admin.AdditionalDetail;

public class AddAdditionalDetailCommandParser implements Parser<AddAdditionalDetailCommand> {

    /**
     * Parses the given {@code String} in the context of a AddAdditionalDetailCommand
     * and returns an AddAdditionalDetailCommand for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public AddAdditionalDetailCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TEXT);

        if (!areRequiredPrefixesPresent(argMultimap, PREFIX_TEXT)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index index = getIndex(argMultimap);

        AdditionalDetail additionalDetail = ParserUtil.parseAdditionalDetail(argMultimap
                .getValue(PREFIX_TEXT).get());

        return new AddAdditionalDetailCommand(index, additionalDetail);
    }

    private boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    private Index getIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

}
