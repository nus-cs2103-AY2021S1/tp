package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.stock.commons.core.Messages;
import seedu.stock.commons.core.index.Index;
import seedu.stock.commons.util.StringUtil;
import seedu.stock.logic.commands.DeleteCommand;
import seedu.stock.logic.commands.NoteCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.NoteIndex;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.QuantityAdder;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;

/**
 * Contains utility methods used for parsing strings in the various Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);

        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String quantity} into a {@code Quantity}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseQuantity(String quantity) throws ParseException {
        requireNonNull(quantity);

        String trimmedQuantity = quantity.trim();
        if (!Quantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(Quantity.MESSAGE_CONSTRAINTS);
        }

        return new Quantity(trimmedQuantity);
    }

    /**
     * Parses a {@code Optional<String> lowQuantity} into a {@code Quantity}.
     * Updates lowQuantity in quantity if lowQuantity is present and returns original
     * quantity otherwise.
     * Leading and trailing whitespaces will be trimmed if lowQuantity is present.
     *
     * @throws ParseException if the given {@code quantity} is invalid.
     */
    public static Quantity parseLowQuantity(Quantity quantity, Optional<String> lowQuantity) throws ParseException {
        if (lowQuantity.isEmpty()) {
            return quantity;
        }

        String trimmedLowQuantity = lowQuantity.get().trim();

        if (!Quantity.isValidQuantity(trimmedLowQuantity)) {
            throw new ParseException(Quantity.LOW_QUANTITY_MESSAGE_CONSTRAINTS);
        }

        return quantity.updateLowQuantity(trimmedLowQuantity);
    }

    /**
     * Parses a {@code String location} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);

        String trimmedLocation = location.trim();
        if (!Location.isValidLocation(trimmedLocation)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }

        return new Location(trimmedLocation);
    }

    /**
     * Parses a {@code String source} into an {@code Source}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code source} is invalid.
     */
    public static Source parseSource(String source) throws ParseException {
        requireNonNull(source);

        String trimmedSource = source.trim();
        if (!Source.isValidSource(trimmedSource)) {
            throw new ParseException(Source.MESSAGE_CONSTRAINTS);
        }

        return new Source(trimmedSource);
    }

    /**
     * Parses a {@code String serialNumbers} into an {@code Set<SerialNumber>}.
     * Parses a {@code String note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);

        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }

        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String serialNumber} into a {@code SerialNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code serialNumber} is invalid.
     */
    public static SerialNumber parseSerialNumber(String serialNumber) throws ParseException {
        requireNonNull(serialNumber);

        String trimmedSerialNumber = serialNumber.trim();
        if (!SerialNumber.isValidSerialNumber(trimmedSerialNumber)) {
            throw new ParseException(SerialNumber.MESSAGE_CONSTRAINTS);
        }

        return new SerialNumber(trimmedSerialNumber);
    }

    /**
     * Parses a {@code String noteIndex} into a {@code NoteIndex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code noteIndex} is invalid.
     */
    public static NoteIndex parseNoteIndex(String noteIndex) throws ParseException {
        requireNonNull(noteIndex);

        String trimmedNoteIndex = noteIndex.trim();
        if (!NoteIndex.isValidNoteIndex(trimmedNoteIndex)) {
            throw new ParseException(NoteIndex.MESSAGE_CONSTRAINTS);
        }

        return new NoteIndex(trimmedNoteIndex);
    }

    /**
     * Parses a {@code String serialNumbers} into an {@code Set<SerialNumber>}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code serialNumbers} is invalid.
     */
    public static Set<SerialNumber> parseSerialNumbers(String serialNumbers) throws ParseException {
        requireNonNull(serialNumbers);

        //invalid input if it does not start with "sn/" as it is a confirmed invalid header.
        if (!serialNumbers.trim().startsWith("sn/")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteCommand.MESSAGE_USAGE));
        }

        Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
        Prefix[] validPrefixesForDelete = {PREFIX_SERIAL_NUMBER };
        Prefix[] invalidPrefixesForDelete = ParserUtil.getInvalidPrefixesForCommand(validPrefixesForDelete);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(serialNumbers, allPossiblePrefixes);

        boolean areAllPrefixesPresent =
                Stream.of(validPrefixesForDelete).allMatch(prefix -> argMultimap.getValue(prefix).isPresent());

        boolean isAnyPrefixPresent =
                Stream.of(invalidPrefixesForDelete).anyMatch(prefix -> argMultimap.getValue(prefix).isPresent());

        // Check if command format is correct
        if (!areAllPrefixesPresent || isAnyPrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE));
        }

        List<String> values = argMultimap.getAllValues(PREFIX_SERIAL_NUMBER);

        if (values.isEmpty()) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        Set<SerialNumber> serialNumberSet = new LinkedHashSet<>();

        for (int i = 0; i < values.size(); i++) {
            String currentSerialNumberInString = values.get(i);
            serialNumberSet.add(parseSerialNumber(currentSerialNumberInString));
        }
        return serialNumberSet;
    }

    /**
     * Parses {@code valueToBeAdded} into a {@code QuantityAdder}.
     *
     * @param valueToBeAdded The value to be added into a certain quantity.
     * @return A new quantity adder containing the value to be added.
     * @throws ParseException If there are parsing errors.
     */
    public static QuantityAdder parseQuantityAdder(String valueToBeAdded) throws ParseException {
        requireNonNull(valueToBeAdded);

        String trimmedValue = valueToBeAdded.trim();
        if (!QuantityAdder.isValidValue(trimmedValue)) {
            throw new ParseException(QuantityAdder.MESSAGE_CONSTRAINTS);
        }

        return new QuantityAdder(trimmedValue);
    }

    /**
     * Returns a list containing all the prefixes provided by the input.
     *
     * @param prefixes The prefixes to be added into the list.
     * @return The list containing all the prefixes.
     */
    public static List<Prefix> generateListOfPrefixes(Prefix ... prefixes) {
        List<Prefix> listOfPrefixes = new ArrayList<>();
        for (Prefix prefix : prefixes) {
            listOfPrefixes.add(prefix);
        }
        return listOfPrefixes;
    }

    /**
     * Returns an array containing all invalid prefixes for the command.
     *
     * @param validPrefixes prefixes that are valid for the command
     * @return An array containing all invalid prefixes for the command.
     */
    public static Prefix[] getInvalidPrefixesForCommand(Prefix[] validPrefixes) {
        List<Prefix> allPossiblePrefixes = CliSyntax.getAllPossiblePrefixes();

        for (Prefix prefix : validPrefixes) {
            allPossiblePrefixes.remove(prefix);
        }

        Prefix[] allInvalidPrefixes = allPossiblePrefixes.toArray(new Prefix[0]);

        return allInvalidPrefixes;
    }

    /**
     * Returns true if an invalid prefix for the command is present in the argument multimap.
     * @param argumentMultimap the argument multimap to check
     * @param validPrefixesForCommand the valid prefixes for the command
     * @return boolean true if invalid prefix present
     */
    public static boolean isInvalidPrefixPresent(ArgumentMultimap argumentMultimap,
                                                 Prefix[] validPrefixesForCommand) {
        Prefix[] invalidPrefixesForCommand = getInvalidPrefixesForCommand(validPrefixesForCommand);
        return isAnyPrefixPresent(argumentMultimap, invalidPrefixesForCommand);
    }

    /**
     * Returns true if any one of the prefixes does not contain
     * an empty {@code Optional} value in the given {@code ArgumentMultimap}.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return boolean true if a prefix specified is present
     */
    public static boolean isAnyPrefixPresent(
            ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix ->
                argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if duplicate prefixes are present when parsing command.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return boolean true if duplicate prefix is present
     */
    public static boolean isDuplicatePrefixPresent(
            ArgumentMultimap argumentMultimap, Prefix... prefixes) {

        // Check for duplicate prefixes
        for (Prefix prefix: prefixes) {
            if (argumentMultimap.getAllValues(prefix).size() >= 2) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if all prefixes specified does not contain an empty {@code Optional} value
     * in the given {@code ArgumentMultimap}.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return boolean true if all prefixes specified is present
     */
    public static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
