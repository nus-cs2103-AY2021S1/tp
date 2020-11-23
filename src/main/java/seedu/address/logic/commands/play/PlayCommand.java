package seedu.address.logic.commands.play;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.view.View;

public class PlayCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Playmode Started";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);
            //switch to playmode
            model.newGame();
            model.setCurrentView(View.QUIZ_VIEW);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (Exception e) {
            throw new CommandException(e.toString());
        }
    }
}
