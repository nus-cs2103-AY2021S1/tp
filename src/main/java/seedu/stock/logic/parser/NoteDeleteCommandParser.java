package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.NoteDeleteCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.NoteIndex;
import seedu.stock.model.stock.SerialNumber;

public class NoteDeleteCommandParser implements Parser<NoteDeleteCommand> {

    private static final String MESSAGE_INVALID_NOTE_INDEX =
            "Note index must be a valid positive integer.";

    private static final Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
    private static final Prefix[] validPrefixesForNoteDelete = { PREFIX_SERIAL_NUMBER, PREFIX_NOTE_INDEX };
    private static final Logger logger = LogsCenter.getLogger(NoteDeleteCommandParser.class);

    @Override
    public NoteDeleteCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse note delete command");
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPossiblePrefixes);

        // Check if command format is correct
        if (!areAllPrefixesPresent(argMultimap, validPrefixesForNoteDelete)
                || ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixesForNoteDelete)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteDeleteCommand.MESSAGE_USAGE));
        }

        if (isDuplicatePrefixPresent(argMultimap, validPrefixesForNoteDelete)) {
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

    /**
     * Returns true if all prefixes specified does not contain an empty {@code Optional} value
     * in the given {@code ArgumentMultimap}.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return boolean true if all prefixes specified is present
     */
    private static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if duplicate prefixes are present when parsing command.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return boolean true if duplicate prefix is present
     */
    private static boolean isDuplicatePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {

        // Check for duplicate prefixes
        for (Prefix prefix: prefixes) {
            if (argumentMultimap.getAllValues(prefix).size() >= 2) {
                return true;
            }
        }
        return false;
    }

}
