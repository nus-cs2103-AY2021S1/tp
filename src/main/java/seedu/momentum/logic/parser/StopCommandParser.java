//@@author boundtotheearth

package seedu.momentum.logic.parser;

import static seedu.momentum.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;

import seedu.momentum.commons.core.index.Index;
import seedu.momentum.logic.commands.StopCommand;
import seedu.momentum.logic.commands.StopProjectCommand;
import seedu.momentum.logic.commands.StopTaskCommand;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.model.Model;
import seedu.momentum.model.ViewMode;

/**
 * Parses input arguments and creates an appropriate StopCommand object.
 */
public class StopCommandParser implements Parser<StopCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StopCommand and current model,
     * and returns the corresponding StopCommand object for execution.
     *
     * @param args Arguments to parse.
     * @param model The current model, to provide context for parsing the arguments.
     * @return A new stop command with the parsed arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public StopCommand parse(String args, Model model) throws ParseException {
        requireAllNonNull(args, model);
        try {
            Index index = ParserUtil.parseIndex(args);

            if (model.getViewMode() == ViewMode.PROJECTS) {
                return new StopProjectCommand(index);
            }
            return new StopTaskCommand(index, model.getCurrentProject());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StopCommand.MESSAGE_USAGE), pe);
        }
    }

}
