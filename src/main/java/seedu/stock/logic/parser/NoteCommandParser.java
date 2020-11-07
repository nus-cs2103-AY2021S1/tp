package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.NoteCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.SerialNumber;

public class NoteCommandParser implements Parser<NoteCommand> {

    private static final Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
    private static final Prefix[] validPrefixesForNote = { PREFIX_NOTE, PREFIX_SERIAL_NUMBER };
    private static final Logger logger = LogsCenter.getLogger(NoteCommandParser.class);

    @Override
    public NoteCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse note command");
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPossiblePrefixes);

        // Check if command format is correct
        if (!areAllPrefixesPresent(argMultimap, validPrefixesForNote)
                || ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixesForNote)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE));
        }

        if (isDuplicatePrefixPresent(argMultimap, validPrefixesForNote)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                    NoteCommand.MESSAGE_USAGE));
        }

        String serialNumberInput =
                argMultimap.getValue(PREFIX_SERIAL_NUMBER).get().toLowerCase();
        SerialNumber serialNumber = ParserUtil.parseSerialNumber(serialNumberInput);
        String noteInput = argMultimap.getValue(PREFIX_NOTE).get();
        Note noteToAdd = ParserUtil.parseNote(noteInput);

        logger.log(Level.INFO, "Finished parsing note command successfully");
        return new NoteCommand(serialNumber, noteToAdd);
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
