package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIXES;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PREAMBLE_NOT_EMPTY;
import static seedu.address.commons.core.Messages.MESSAGE_PREFIX_MISSING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatricNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM,
                        PREFIX_MATRIC_NUMBER, PREFIX_TAG);
        // all argument checks
        boolean areAllPrefixesPresent = arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MATRIC_NUMBER);
        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();
        boolean areDuplicatePrefixesPresent = checkDuplicatePrefixes(argMultimap);

        if (!areAllPrefixesPresent) {
            Optional<Prefix> missingPrefix = getMissingPrefix(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                    PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_MATRIC_NUMBER);
            if (missingPrefix.isPresent()) {
                throw new ParseException(String.format(MESSAGE_PREFIX_MISSING, missingPrefix.get().toString(),
                        AddCommand.MESSAGE_USAGE));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
        } else if (!isPreambleEmpty) {
            throw new ParseException(MESSAGE_PREAMBLE_NOT_EMPTY);
        } else if (areDuplicatePrefixesPresent) {
            Optional<Prefix> duplicatePrefix = getDuplicatePrefix(argMultimap);
            if (duplicatePrefix.isPresent()) {
                throw new ParseException(String.format(MESSAGE_DUPLICATE_PREFIXES, duplicatePrefix.get()));
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            }
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Telegram telegram = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        MatricNumber matricNumber = ParserUtil.parseMatricNumber(
                argMultimap.getValue(PREFIX_MATRIC_NUMBER).get()
        );
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        SortedSet<Attendance> attendances = new TreeSet<>();


        Person person = new Person(name, phone, email, telegram, matricNumber, tagList, attendances);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static Optional<Prefix> getMissingPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isEmpty())
                .findFirst();
    }

    /**
     * Returns true if there are duplicate prefixes(except for tag) in the given {@code ArgumentMultimap}.
     */
    private static boolean checkDuplicatePrefixes(ArgumentMultimap argumentMultimap) {
        Stream<Prefix> prefixes = Stream.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM,
                PREFIX_MATRIC_NUMBER);
        return prefixes.anyMatch(prefix -> argumentMultimap.getAllValues(prefix).size() > 1);
    }

    private static Optional<Prefix> getDuplicatePrefix(ArgumentMultimap argumentMultimap) {
        Stream<Prefix> prefixes = Stream.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM,
                PREFIX_MATRIC_NUMBER);
        return prefixes.filter(prefix -> argumentMultimap.getAllValues(prefix).size() > 1).findFirst();
    }

}
