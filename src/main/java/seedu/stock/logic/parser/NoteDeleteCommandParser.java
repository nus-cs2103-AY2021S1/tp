package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.NoteDeleteCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.NoteIndex;
import seedu.stock.model.stock.SerialNumber;

public class NoteDeleteCommandParser implements Parser<NoteDeleteCommand> {

    private static final Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
    private static final Prefix[] validPrefixesForNoteDelete = { PREFIX_SERIAL_NUMBER, PREFIX_NOTE_INDEX };
    private static final Logger logger = LogsCenter.getLogger(NoteDeleteCommandParser.class);

    @Override
    public NoteDeleteCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse note delete command");
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPossiblePrefixes);

        // Check if command format is correct
        if (!ParserUtil.areAllPrefixesPresent(argMultimap, validPrefixesForNoteDelete)
                || ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixesForNoteDelete)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteDeleteCommand.MESSAGE_USAGE));
        }

        if (ParserUtil.isDuplicatePrefixPresent(argMultimap, validPrefixesForNoteDelete)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                    NoteDeleteCommand.MESSAGE_USAGE));
        }

        String serialNumberInput = argMultimap.getValue(PREFIX_SERIAL_NUMBER).get();
        SerialNumber serialNumber = ParserUtil.parseSerialNumber(serialNumberInput);
        String noteIndexInput = argMultimap.getValue(PREFIX_NOTE_INDEX).get();
        NoteIndex noteIndex = ParserUtil.parseNoteIndex(noteIndexInput);

        logger.log(Level.INFO, "Finished parsing note delete command successfully");
        return new NoteDeleteCommand(serialNumber, noteIndex);
    }

}
