package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.MainCatalogue;
import seedu.address.model.Model;

/**
 * Clears the main catalogue.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_EXTRA_ARGS = "Please do not provide extra arguments after \""
            + COMMAND_WORD + "\"";
    public static final String MESSAGE_SUCCESS = "ProjectDescription book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setProjectCatalogue(new MainCatalogue());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
