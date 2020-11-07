package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.project.ViewTeammateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewTeammateCommandParser implements Parser<ViewTeammateCommand> {

    /**
     * Parses the given {@code args} of arguments in the context of the ViewTeammateCommand
     * and returns an ViewTeammateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTeammateCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        userInput.trim();

        try {
            GitUserIndex gitUserIndex = ParsePersonUtil.parseGitUserIndex(userInput);
            return new ViewTeammateCommand(gitUserIndex);
        } catch (ParseException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTeammateCommand.MESSAGE_USAGE), e);
        }
    }
}
