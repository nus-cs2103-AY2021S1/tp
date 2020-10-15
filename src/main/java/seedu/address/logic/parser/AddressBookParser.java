package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddBidCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddMeetingCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteMeetingCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBidCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.commands.biddercommands.DeleteBidderCommand;
import seedu.address.logic.commands.biddercommands.ListBidderCommand;
import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.logic.commands.property.DeletePropertyCommand;
import seedu.address.logic.commands.property.ListPropertyCommand;
import seedu.address.logic.commands.sellercommands.AddSellerCommand;
import seedu.address.logic.commands.sellercommands.DeleteSellerCommand;
import seedu.address.logic.commands.sellercommands.ListSellerCommand;
import seedu.address.logic.parser.bidderparser.AddBidderCommandParser;
import seedu.address.logic.parser.bidderparser.DeleteBidderCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.property.AddPropertyCommandParser;
import seedu.address.logic.parser.property.DeletePropertyCommandParser;
import seedu.address.logic.parser.sellerparser.AddSellerCommandParser;
import seedu.address.logic.parser.sellerparser.DeleteSellerCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddBidCommand.COMMAND_WORD:
            return new AddBidCommandParser().parse(arguments);

        case ListBidCommand.COMMAND_WORD:
            return new ListBidCommand();

        case AddMeetingCommand.COMMAND_WORD:
            return new AddMeetingCommandParser().parse(arguments);

        case DeleteMeetingCommand.COMMAND_WORD:
            return new DeleteMeetingCommandParser().parse(arguments);

        case AddPropertyCommand.COMMAND_WORD:
            return new AddPropertyCommandParser().parse(arguments);

        case DeletePropertyCommand.COMMAND_WORD:
            return new DeletePropertyCommandParser().parse(arguments);

        case ListPropertyCommand.COMMAND_WORD:
            return new ListPropertyCommand();

        case AddBidderCommand.COMMAND_WORD:
            return new AddBidderCommandParser().parse(arguments);

        case ListBidderCommand.COMMAND_WORD:
            return new ListBidderCommand();

        case DeleteBidderCommand.COMMAND_WORD:
            return new DeleteBidderCommandParser().parse(arguments);

        case AddSellerCommand.COMMAND_WORD:
            return new AddSellerCommandParser().parse(arguments);

        case ListSellerCommand.COMMAND_WORD:
            return new ListSellerCommand();

        case DeleteSellerCommand.COMMAND_WORD:
            return new DeleteSellerCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
