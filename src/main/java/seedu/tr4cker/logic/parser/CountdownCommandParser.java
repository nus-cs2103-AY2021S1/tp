package seedu.tr4cker.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.*;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.CountdownCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;

import java.util.stream.Stream;

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
                PREFIX_COUNTDOWN_NEW, PREFIX_COUNTDOWN_DATE, PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DELETE);

        // should not have preamble whatsoever
        String string = argMultimap.getPreamble();
        if (!string.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CountdownCommand.MESSAGE_USAGE));
        }

        // user wants to go to Countdown tab
        if (!arePrefixesPresent(argMultimap, PREFIX_COUNTDOWN_NEW, PREFIX_DEADLINE,
                PREFIX_COUNTDOWN_TASK, PREFIX_COUNTDOWN_DELETE)) {
            return new CountdownCommand();
        }

        // user wants to add a new event
        if (argMultimap.getValue(PREFIX_COUNTDOWN_NEW).isPresent()
                && argMultimap.getValue(PREFIX_COUNTDOWN_DATE).isPresent()) {
            EventName eventName = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_COUNTDOWN_NEW).get());
            EventDate eventDate = ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_COUNTDOWN_DATE).get());
            return new CountdownCommand(eventName, eventDate);
        }

        // user wants to delete an event
        if (argMultimap.getValue(PREFIX_COUNTDOWN_DELETE).isPresent()) {
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_COUNTDOWN_DELETE).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CountdownCommand.MESSAGE_USAGE), pe);
            }
            return new CountdownCommand(index, true); // true since have delete prefix
        }

        // user wants to add an event from tasks list
        if (argMultimap.getValue(PREFIX_COUNTDOWN_TASK).isPresent()) {
            //TODO: conversion method
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_COUNTDOWN_DELETE).get());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CountdownCommand.MESSAGE_USAGE), pe);
            }
            return new CountdownCommand();
        }
        throw new ParseException(CountdownCommand.MESSAGE_USAGE);
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        requireAllNonNull(argumentMultimap, prefixes);
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
