//@@author

package seedu.momentum.logic.parser;

import static seedu.momentum.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;

import seedu.momentum.commons.core.index.Index;
import seedu.momentum.logic.commands.DeleteCommand;
import seedu.momentum.logic.commands.DeleteProjectCommand;
import seedu.momentum.logic.commands.DeleteTaskCommand;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.model.Model;
import seedu.momentum.model.ViewMode;

/**
 * Parses input arguments and creates an appropriate DeleteCommand object.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand and current model,
     * and returns the corresponding DeleteCommand object for execution.
     *
     * @param model The current model, to provide context for parsing the arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public DeleteCommand parse(String args, Model model) throws ParseException {
        requireAllNonNull(args, model);
        try {
            Index index = ParserUtil.parseIndex(args);

            if (model.getViewMode() == ViewMode.PROJECTS) {
                return new DeleteProjectCommand(index);
            }
            return new DeleteTaskCommand(index, model.getCurrentProject());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
