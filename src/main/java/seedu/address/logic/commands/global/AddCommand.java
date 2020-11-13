package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPOURL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Adds a project to the main catalogue.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a project to the main catalogue. "
            + "Parameters: "
            + PREFIX_PROJECT_NAME + "PROJECT NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_REPOURL + "REPOURL "
            + PREFIX_DESCRIPTION + "PROJECT DESCRIPTION "
            + "[" + PREFIX_PROJECT_TAG + "TAG]...\n"
            + "[" + PREFIX_TASK + "TASK]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PROJECT_NAME + "The Blair project "
            + PREFIX_DEADLINE + "29-02-2020 00:00:00"
            + PREFIX_REPOURL + "http://github.com/a/b.git "
            + PREFIX_DESCRIPTION + "Coding in Greenwich "
            + PREFIX_PROJECT_TAG + "challenging "
            + PREFIX_PROJECT_TAG + "WFH"
            + PREFIX_TASK + "Write User Guide";

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the main catalogue";

    private final Project toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Project}
     */
    public AddCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        model.addProject(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
