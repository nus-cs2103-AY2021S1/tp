package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class EnterQuizCommand extends Command {
    public static final String COMMAND_WORD = "enter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " quiz : Enters quiz interface \n"
        + "Example: " + COMMAND_WORD + " quiz (case sensitive)";
    public static final String MESSAGE_ENTER_ACKNOWLEDGEMENT = "Switching from flashcard mode to quiz mode.\n"
        + "Only Quiz interface commands are workable.";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.flipQuizMode();
        return new CommandResult(MESSAGE_ENTER_ACKNOWLEDGEMENT, true, false, false, false, false);
    }

}
