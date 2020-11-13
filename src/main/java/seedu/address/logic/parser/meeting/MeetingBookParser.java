package seedu.address.logic.parser.meeting;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.meeting.ClearCommand;
import seedu.address.logic.commands.meeting.Command;
import seedu.address.logic.commands.meeting.DeleteCommand;
import seedu.address.logic.commands.meeting.EditCommand;
import seedu.address.logic.commands.meeting.FindCommand;
import seedu.address.logic.commands.meeting.ListCommand;
import seedu.address.logic.commands.meeting.ViewCommand;
import seedu.address.logic.commands.person.AddCommand;
import seedu.address.logic.parser.TokenizedUserInput;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for meeting.
 */
public class MeetingBookParser {

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        TokenizedUserInput tokenizedUserInput = TokenizedUserInput.getCommandWordArgumentsFromUserInput(userInput);
        String commandWord = tokenizedUserInput.getCommandWord();
        String arguments = tokenizedUserInput.getArguments();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }

    }

    /**
     * Checks if user input is a mode command.
     *
     * @param userInput full user input string
     * @return whether the user input is a mode command
     */
    public boolean isMeetingCommand(String userInput) {
        return userInput.startsWith(Command.COMMAND_WORD);
    }

}
