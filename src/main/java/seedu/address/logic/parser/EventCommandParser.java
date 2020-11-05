package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.parser.exceptions.MultipleAttributesException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class EventCommandParser implements Parser<EventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EventCommand parse(String args) throws ParseException, MultipleAttributesException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME,
                    PREFIX_DESCRIPTION, PREFIX_TAG);

        if (!Parser.arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, "", EventCommand.MESSAGE_USAGE));
        }

        if (argMultimap.hasMultipleValues(PREFIX_TITLE)
                || argMultimap.hasMultipleValues(PREFIX_DATE)
                || argMultimap.hasMultipleValues(PREFIX_START_TIME)
                || argMultimap.hasMultipleValues(PREFIX_END_TIME)
                || argMultimap.hasMultipleValues(PREFIX_DESCRIPTION)
                || argMultimap.hasMultipleValues(PREFIX_TAG)) {
            throw new MultipleAttributesException(MESSAGE_MULTIPLE_ATTRIBUTES);
        }

        Description description = Description.defaultDescription();
        Tag tag = Tag.defaultTag();
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        StartDateTime startDateTime = ParserUtil.parseStartDateTime(
                argMultimap.getValue(PREFIX_DATE).get(), argMultimap.getValue(PREFIX_START_TIME).get());
        EndDateTime endDateTime = ParserUtil.parseEndDateTime(
                argMultimap.getValue(PREFIX_DATE).get(), argMultimap.getValue(PREFIX_END_TIME).get());

        if (!DateTimeUtil.isStartDateTimeBeforeEndDateTime(startDateTime.getValue(), endDateTime.getValue())) {
            throw new ParseException(Time.RANGE_CONSTRAINTS);
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        }
        Event event = Event.createUserEvent(title, startDateTime, endDateTime, description, tag);

        return new EventCommand(event);
    }

}
