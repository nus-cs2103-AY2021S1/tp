package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.catalogue.AddCommand;
import seedu.address.logic.commands.catalogue.ClearCommand;
import seedu.address.logic.commands.catalogue.DeleteCommand;
import seedu.address.logic.commands.catalogue.EditCommand;
import seedu.address.logic.commands.catalogue.FindCommand;
import seedu.address.logic.commands.catalogue.ListCommand;
import seedu.address.logic.commands.global.ExitCommand;
import seedu.address.logic.commands.global.HelpCommand;
import seedu.address.logic.commands.project.LeaveCommand;
import seedu.address.logic.commands.catalogue.StartCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exceptions.InvalidScopeException;
import seedu.address.model.project.Status;

/**
 * Parses user input.
 */
public class MainCatalogueParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param status the status of the current scoping
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Status status) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        if (status == Status.CATALOGUE) {
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

            case StartCommand.COMMAND_WORD:
                return new StartCommandParser().parse(arguments);

            case LeaveCommand.COMMAND_WORD:
                throw new InvalidScopeException(Status.PROJECT, Status.CATALOGUE);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } else if (status == Status.PROJECT) {
            switch (commandWord) {

            case LeaveCommand.COMMAND_WORD:
                return new LeaveCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case AddCommand.COMMAND_WORD:
            case EditCommand.COMMAND_WORD:
            case DeleteCommand.COMMAND_WORD:
            case ClearCommand.COMMAND_WORD:
            case FindCommand.COMMAND_WORD:
            case ListCommand.COMMAND_WORD:
            case StartCommand.COMMAND_WORD:
                throw new InvalidScopeException(Status.CATALOGUE, Status.PROJECT);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } else {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
