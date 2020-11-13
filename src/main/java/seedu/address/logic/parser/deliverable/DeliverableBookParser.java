package seedu.address.logic.parser.deliverable;

import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import seedu.address.logic.commands.deliverable.AddCommand;
import seedu.address.logic.commands.deliverable.ClearCommand;
import seedu.address.logic.commands.deliverable.Command;
import seedu.address.logic.commands.deliverable.DeleteCommand;
import seedu.address.logic.commands.deliverable.EditCommand;
import seedu.address.logic.commands.deliverable.ListCommand;
import seedu.address.logic.commands.deliverable.MarkDoneCommand;
import seedu.address.logic.commands.deliverable.MarkUndoneCommand;
import seedu.address.logic.commands.deliverable.ViewCommand;
import seedu.address.logic.commands.person.FindCommand;
import seedu.address.logic.parser.TokenizedUserInput;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input for deliverable.
 */
public class DeliverableBookParser {

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
        case MarkDoneCommand.COMMAND_WORD:
            return new MarkDoneCommandParser().parse(arguments);
        case MarkUndoneCommand.COMMAND_WORD:
            return new MarkUndoneCommandParser().parse(arguments);
        case ViewCommand.COMMAND_WORD:
            return new ViewCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
