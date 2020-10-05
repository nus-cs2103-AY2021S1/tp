package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.Task;

public class AddTaskCommandParser {
    /**
     * Interprets the user's input
     * @param args
     * @return
     */
    public AddTaskCommand parse(String args) /*throws ParseException*/ {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_TASK);

        /*
        try {
            id = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTGCommand.MESSAGE_USAGE), ive);
        }
        */

        String taskName = argMultimap.getValue(PREFIX_TASK).orElse("");

        return new AddTaskCommand(new Task(taskName));
    }
}
