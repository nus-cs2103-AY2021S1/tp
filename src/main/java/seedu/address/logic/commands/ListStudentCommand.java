package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class ListStudentCommand extends Command {
    public static final String COMMAND_WORD = "listStudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all the students.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEWING_TUTORIAL_GROUPS_SUCCESS = "Viewing all students of: %1$s";
    public static final String MESSAGE_WRONG_VIEW = "You are currently not in the Student view";

    public ListStudentCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (!model.isInStudentView()) {
            throw new CommandException(MESSAGE_WRONG_VIEW);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        TutorialGroup tg = model.getCurrentTgInView();
        return new CommandResult(String.format(MESSAGE_VIEWING_TUTORIAL_GROUPS_SUCCESS, tg),
                false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListStudentCommand); // instanceof handles nulls
    }
}

