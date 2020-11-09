package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exercise.Template;
import seedu.address.model.exercise.TemplateList;

public class AddTemplateCommand extends Command {
    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an exercise template. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_CALORIES + "CALORIES "
            + "[" + PREFIX_MUSCLE + "MUSCLES] "
            + "[" + PREFIX_TAG + "TAGS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "running "
            + PREFIX_CALORIES + "100";

    public static final String MESSAGE_SUCCESS = "New template created: %1$s";

    private final Template toCreate;

    public AddTemplateCommand(Template toCreate) {
        this.toCreate = toCreate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException, IOException {
        requireNonNull(model);

        if (TemplateList.checkEqual(toCreate)) {
            throw new CommandException("The template already exists.");
        }

        model.addTemplate(toCreate);
        Template.writeToFile(TemplateList.getList());

        return new CommandResult(String.format(MESSAGE_SUCCESS, toCreate));
    }

}
