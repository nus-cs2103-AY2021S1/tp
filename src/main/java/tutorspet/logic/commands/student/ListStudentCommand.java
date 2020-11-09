package tutorspet.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static tutorspet.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import tutorspet.logic.commands.Command;
import tutorspet.logic.commands.CommandResult;
import tutorspet.logic.commands.exceptions.CommandException;
import tutorspet.model.Model;

/**
 * Lists all students to the user.
 */
public class ListStudentCommand extends Command {

    public static final String COMMAND_WORD = "list-student";

    public static final String MESSAGE_SUCCESS = "Listed all students.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ListStudentCommand);
    }
}
