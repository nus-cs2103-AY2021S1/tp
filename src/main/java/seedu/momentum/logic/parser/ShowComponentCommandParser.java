//@@author claracheong4

package seedu.momentum.logic.parser;

import static seedu.momentum.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.momentum.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.momentum.logic.commands.ShowComponentCommand;
import seedu.momentum.logic.parser.exceptions.ParseException;
import seedu.momentum.model.Model;

/**
 * Parses input arguments and creates a new ShowComponentCommandParser object.
 */
public class ShowComponentCommandParser implements Parser<ShowComponentCommand> {
    /**
     * Represents all the possible components that can be operated on.
     */
    public enum ComponentType {
        TAGS;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ShowComponentCommand
     * and returns an ShowComponentCommand object for execution.
     *
     * @param args Arguments to parse.
     * @param model The current model, to provide context for parsing the arguments.
     * @return A new show component command with the parsed arguments.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public ShowComponentCommand parse(String args, Model model) throws ParseException {
        requireAllNonNull(args, model);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new ShowComponentCommand(ComponentType.TAGS);
        }
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowComponentCommand.MESSAGE_USAGE));
    }

}
