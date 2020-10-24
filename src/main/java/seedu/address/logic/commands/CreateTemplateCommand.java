package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ExerciseModel;
import seedu.address.model.exercise.Exercise;
import seedu.address.ui.Template;

public class CreateTemplateCommand extends CommandForExercise {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an exercise template. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DESCRIPTION + "DESCRIPTION"
            + PREFIX_CALORIES + "CALORIES]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "running "
            + PREFIX_DESCRIPTION + "10 mins "
            + PREFIX_CALORIES + "100";

    public static final String MESSAGE_SUCCESS = "New template created: %1$s";

    private final Template toCreate;

    public CreateTemplateCommand(Template toCreate) {
        this.toCreate = toCreate;
    }

    @Override
    public CommandResult execute(ExerciseModel model) throws CommandException {
        requireNonNull(model);

        model.createTemplate(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
    }

//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof AddCommand // instanceof handles nulls
//                && toCreate.equals(((AddCommandForExercise) other).toAdd)); // state check
//    }
}
