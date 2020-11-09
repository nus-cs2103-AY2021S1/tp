package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.logic.commands.contactlistcommands.ClearContactCommand;
import seedu.address.logic.commands.contactlistcommands.DeleteContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.logic.commands.contactlistcommands.ImportantContactCommand;
import seedu.address.logic.commands.contactlistcommands.ListContactCommand;
import seedu.address.logic.commands.contactlistcommands.ResetContactCommand;
import seedu.address.logic.commands.contactlistcommands.SortContactCommand;
import seedu.address.logic.parser.contactlistparsers.AddContactParser;
import seedu.address.logic.parser.contactlistparsers.DeleteContactParser;
import seedu.address.logic.parser.contactlistparsers.EditContactParser;
import seedu.address.logic.parser.contactlistparsers.FindContactParser;
import seedu.address.logic.parser.contactlistparsers.ImportantContactParser;
import seedu.address.logic.parser.contactlistparsers.ResetContactParser;
import seedu.address.logic.parser.contactlistparsers.SortContactParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Encapsulates method and information to parse the user input.
 */
public class ContactListParser implements FeatureParser {

    /**
     * Used for initial separation of command word and arguments.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses the user input into a command for execution.
     *
     * @param userInput Full user input string.
     * @return Command object based on the user input.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddContactCommand.COMMAND_WORD:
            return new AddContactParser().parse(arguments);

        case EditContactCommand.COMMAND_WORD:
            return new EditContactParser().parse(arguments);

        case DeleteContactCommand.COMMAND_WORD:
            return new DeleteContactParser().parse(arguments);

        case ClearContactCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ClearContactCommand.COMMAND_WORD, arguments);

        case FindContactCommand.COMMAND_WORD:
            return new FindContactParser().parse(arguments);

        case ListContactCommand.COMMAND_WORD:
            return singleWordCommandsChecker(ListContactCommand.COMMAND_WORD, arguments);

        case ImportantContactCommand.COMMAND_WORD:
            return new ImportantContactParser().parse(arguments);

        case ResetContactCommand.COMMAND_WORD:
            return new ResetContactParser().parse(arguments);

        case SortContactCommand.COMMAND_WORD:
            return new SortContactParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private Command singleWordCommandsChecker(String commandWord, String argument) throws ParseException {
        if (!argument.equals("")) {
            throw new ParseException("Invalid input format, extra string after the command word.");
        }

        switch (commandWord) {
        case ClearContactCommand.COMMAND_WORD:
            return new ClearContactCommand();

        case ListContactCommand.COMMAND_WORD:
            return new ListContactCommand();

        default:
            throw new ParseException("Invalid command");
        }
    }
}
