package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DATE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DAYS;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_NEW;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_COUNTDOWN_TASK;

import java.util.stream.Stream;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;

/**
 * Parses input arguments and creates a new CountdownCommand object.
 */
public class CountdownCommandParser implements Parser<CountdownCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CountdownCommand
     * and returns an CountdownCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public CountdownCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE, PREFIX_COUNTDOWN_TASK,
                PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_DAYS);

        // should not have preamble whatsoever
        String string = argMultimap.getPreamble();
        if (!string.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_SWITCH_TAB_USAGE));
        }

        // user wants to go to Countdown tab
        if (areNonePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE,
                PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_DAYS)) {
            return new CountdownCommand();
        }

        // user wants to add a new event
        if (areAllPrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE)
                && areNonePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DELETE,
                PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DAYS)) {
            return parseCountdownCommandAdd(argMultimap);
        }

        // user wants to delete an event
        if (areAllPrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DELETE)
                && areNonePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW,
                PREFIX_COUNTDOWN_DATE, PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DAYS)) {
            return parseCountdownCommandDelete(argMultimap);
        }

        // user wants to add an event from tasks list
        if (areAllPrefixesPresent(argMultimap, PREFIX_COUNTDOWN_TASK)
                && areNonePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DATE,
                PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DAYS)) {
            return parseCountdownCommandAddFromTask(argMultimap);
        }

        // user wants to count number of events in x days
        if (areNonePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_DATE,
                PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_TASK)
                && areAllPrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DAYS)) {
            return parseCountdownCommandDays(argMultimap);
        }

        // insufficient params for add
        if (areAnyPrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE)
                && !areAnyPrefixesPresent(argMultimap, PREFIX_COUNTDOWN_DELETE, PREFIX_COUNTDOWN_TASK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_ADD_COUNTDOWN_USAGE));
        }
        throw new ParseException(CountdownCommand.MESSAGE_GENERIC_COUNTDOWN_USAGE);
    }

    private CountdownCommand parseCountdownCommandDays(ArgumentMultimap argMultimap) throws ParseException {
        int numDays;
        numDays = ParserUtil.parseQueryDays(argMultimap.getValue(PREFIX_COUNTDOWN_DAYS).get());
        return new CountdownCommand(numDays);
    }

    private CountdownCommand parseCountdownCommandAddFromTask(ArgumentMultimap argMultimap) throws ParseException {
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_COUNTDOWN_TASK).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_SWITCH_TAB_USAGE), pe);
        }
        return new CountdownCommand(index, false); // false since has task prefix
    }

    private CountdownCommand parseCountdownCommandDelete(ArgumentMultimap argMultimap) throws ParseException {
        Index index;
        index = ParserUtil.parseEventIndex(argMultimap.getValue(PREFIX_COUNTDOWN_DELETE).get(),
                CountdownCommand.MESSAGE_DELETE_COUNTDOWN_USAGE);
        return new CountdownCommand(index, true); // true since have delete prefix
    }

    private CountdownCommand parseCountdownCommandAdd(ArgumentMultimap argMultimap) throws ParseException {
        EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_COUNTDOWN_NEW).get());
        EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_COUNTDOWN_DATE).get());
        return new CountdownCommand(eventName, eventDate);
    }

    /**
     * Returns true if any of the prefixes contains non empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes contain non empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean areAllPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean areNonePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).noneMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
