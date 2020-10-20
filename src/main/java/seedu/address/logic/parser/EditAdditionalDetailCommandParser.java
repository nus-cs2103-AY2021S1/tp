package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.EditAdditionalDetailCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.CliSyntax.ADDITIONAL_DETAIL_COMMAND_PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAIL_TEXT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditAdditionalDetailCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.admin.AdditionalDetail;

public class EditAdditionalDetailCommandParser implements Parser<EditAdditionalDetailCommand> {

    /**
     * Parses the given {@code String} in the context of a EditAdditionalDetailCommand
     * and returns an EditAdditionalDetailCommand for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public EditAdditionalDetailCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, ADDITIONAL_DETAIL_COMMAND_PREFIXES);

        if (!areRequiredPrefixesPresent(argMultimap, ADDITIONAL_DETAIL_COMMAND_PREFIXES)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Index studentIndex = getStudentIndex(argMultimap);
        Index detailIndex = getDetailIndex(argMultimap);
        AdditionalDetail additionalDetail = ParserUtil.parseAdditionalDetail(argMultimap
                .getValue(PREFIX_DETAIL_TEXT).get());

        return new EditAdditionalDetailCommand(studentIndex, detailIndex, additionalDetail);
    }

    private boolean areRequiredPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private Index getStudentIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

    private Index getDetailIndex(ArgumentMultimap argMultimap) throws ParseException {
        try {
            return ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DETAIL_INDEX).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }

}
