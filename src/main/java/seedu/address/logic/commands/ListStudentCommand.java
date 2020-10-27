package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import static java.util.Objects.requireNonNull;

public class ListStudentCommand extends Command {
    public static final String COMMAND_WORD = "listStudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views all the students.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS = "Viewing All Students";

    public ListStudentCommand() { }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_VIEWING_TUTORIALGROUPS_SUCCESS), false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListStudentCommand); // instanceof handles nulls
    }
}

