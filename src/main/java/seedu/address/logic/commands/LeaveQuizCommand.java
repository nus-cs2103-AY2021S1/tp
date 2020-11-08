package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class LeaveQuizCommand extends Command {
    public static final String COMMAND_WORD = "leave";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " quiz : Leaves quiz interface \n"
        + "Example: " + COMMAND_WORD + " quiz (case sensitive)";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Switching from quiz mode to flashcard mode.\n"
        + "Only Flashcard interface commands are workable.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.flipQuizMode();
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false, false, false);
    }
}
