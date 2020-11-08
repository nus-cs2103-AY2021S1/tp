package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INCORRECT_MAIN_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.commands.Command.TYPE_CASE;
import static seedu.pivot.logic.commands.Command.TYPE_DESC;
import static seedu.pivot.logic.commands.Command.TYPE_DOC;
import static seedu.pivot.logic.commands.Command.TYPE_SUSPECT;
import static seedu.pivot.logic.commands.Command.TYPE_VICTIM;
import static seedu.pivot.logic.commands.Command.TYPE_WITNESS;
import static seedu.pivot.logic.parser.PivotParser.BASIC_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.stream.Stream;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());

        if (StateManager.atMainPage()) {
            return parseMainPage(matcher);
        }

        if (StateManager.atCasePage()) {
            return parseCasePage(matcher);
        }

        throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns relevant AddCommand by calling specific parser for main page.
     *
     * @param matcher
     * @return AddCommand
     * @throws ParseException if matcher does not match or incorrect type
     */
    private static AddCommand parseMainPage(Matcher matcher) throws ParseException {
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_MAIN_PAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case TYPE_CASE:
            return new AddCaseCommandParser().parse(arguments);
        case TYPE_DESC:
        case TYPE_DOC:
        case TYPE_SUSPECT:
        case TYPE_WITNESS:
        case TYPE_VICTIM:
            throw new ParseException(MESSAGE_INCORRECT_CASE_PAGE);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_MAIN_PAGE));
        }
    }

    /**
     * Returns relevant AddCommand by calling specific parser for case page.
     *
     * @param matcher
     * @return AddCommand
     * @throws ParseException if matcher does not match or incorrect type
     */
    private static AddCommand parseCasePage(Matcher matcher) throws ParseException {
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_CASE_PAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case TYPE_CASE:
            throw new ParseException(MESSAGE_INCORRECT_MAIN_PAGE);
        case TYPE_DESC:
            return new AddDescriptionCommandParser().parse(arguments);
        case TYPE_DOC:
            return new AddDocumentCommandParser().parse(arguments);
        case TYPE_SUSPECT:
            return new AddSuspectCommandParser().parse(arguments);
        case TYPE_WITNESS:
            return new AddWitnessCommandParser().parse(arguments);
        case TYPE_VICTIM:
            return new AddVictimCommandParser().parse(arguments);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE_CASE_PAGE));
        }
    }

}
