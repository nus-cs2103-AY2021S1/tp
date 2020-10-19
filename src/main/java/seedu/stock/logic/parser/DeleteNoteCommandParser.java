package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE_INDEX;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import seedu.stock.logic.commands.DeleteNoteCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.SerialNumber;

import java.util.stream.Stream;

public class DeleteNoteCommandParser implements Parser<DeleteNoteCommand> {

    private final static String MESSAGE_INVALID_NOTE_INDEX = "Note index must be a valid integer.";

    private final static Prefix[] validPrefixesForDeleteNote = { PREFIX_SERIAL_NUMBER, PREFIX_NOTE_INDEX };
    private final static Prefix[] invalidPrefixesForDeleteNote =
            ParserUtil.getInvalidPrefixesForCommand(validPrefixesForDeleteNote);

    @Override
    public DeleteNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, validPrefixesForDeleteNote);

        // Check if command format is correct
        if (!areAllPrefixesPresent(argMultimap, validPrefixesForDeleteNote)
                || isAnyPrefixPresent(argMultimap, invalidPrefixesForDeleteNote)
                || isDuplicatePrefixPresent(argMultimap, validPrefixesForDeleteNote)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteNoteCommand.MESSAGE_USAGE));
        }

        String serialNumberInput = argMultimap.getValue(PREFIX_SERIAL_NUMBER).get();
        SerialNumber serialNumber = ParserUtil.parseSerialNumber(serialNumberInput);
        String noteInput = argMultimap.getValue(PREFIX_NOTE_INDEX).get();
        if (!noteInput.matches("[0-9]+")) {
            throw new ParseException(String.format(MESSAGE_INVALID_NOTE_INDEX,
                    DeleteNoteCommand.MESSAGE_USAGE));
        }
        int noteIndex = Integer.parseInt(noteInput);

        return new DeleteNoteCommand(serialNumber, noteIndex);
    }

    /**
     * Returns true if any one of the prefixes does not contain an empty {@code Optional} value
     * in the given {@code ArgumentMultimap}.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return boolean true if a prefix specified is present
     */
    private static boolean isAnyPrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
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
