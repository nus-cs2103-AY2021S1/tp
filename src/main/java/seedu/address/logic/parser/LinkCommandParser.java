package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.link.LinkCollaborativeCommand;
import seedu.address.logic.commands.link.LinkMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.MeetingLink;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class LinkCommandParser implements Parser<LinkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LinkCommand
     * and returns an LinkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LinkCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs[0].trim().split(" ")[0].trim().equals("meeting")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkMeetingCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION, PREFIX_URL, PREFIX_INDEX,
                            PREFIX_DATE, PREFIX_TIME);

            if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_URL, PREFIX_INDEX, PREFIX_DATE, PREFIX_TIME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        LinkMeetingCommand.MESSAGE_USAGE));
            }
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            String date = argMultimap.getValue(PREFIX_DATE).get().trim();
            String time = argMultimap.getValue(PREFIX_TIME).get().trim();
            ParserUtil.checkDateValidity(date);
            ParserUtil.checkTimeValidity(time);
            String url = argMultimap.getValue(PREFIX_URL).get().trim();
            String meetingTime = date + " " + time;
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get().trim());
            ParserUtil.validateLink(url);

            MeetingLink meetingLink = ParserUtil.parseMeetingLink(description, url, meetingTime);

            return new LinkMeetingCommand(index, meetingLink);
        } else if (splitArgs[0].trim().split(" ")[0].trim().equals("doc")) {
            if (splitArgs.length < 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    LinkCollaborativeCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION, PREFIX_URL, PREFIX_INDEX);

            if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_URL, PREFIX_INDEX)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        LinkCollaborativeCommand.MESSAGE_USAGE));
            }
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            String url = argMultimap.getValue(PREFIX_URL).get().trim();
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get().trim());
            ParserUtil.validateLink(url);

            CollaborativeLink collaborativeLink = ParserUtil.parseCollaborativeLink(description, url);

            return new LinkCollaborativeCommand(index, collaborativeLink);
        } else {
            throw new ParseException(LinkCommand.MESSAGE_USAGE);
        }

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
