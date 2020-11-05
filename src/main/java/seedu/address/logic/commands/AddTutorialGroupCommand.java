package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroup;


public class AddTutorialGroupCommand extends Command {

    public static final String COMMAND_WORD = "addTG";
    public static final String MESSAGE_SUCCESS = "New tutorial group added: %s";
    public static final String MESSAGE_IN_MODULE_VIEW = "You are currently in the Module View. "
        + "Use viewTG MODULE_INDEX to view the Tutorial Groups of the Module you want";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial group already exists in the current module.";
    public static final String MESSAGE_NOT_IN_TUTORIAL_VIEW = "You are currently not in the Tutorial Group view. "
        + "Use listTG to go back to the tutorial group view.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Tutorial Group to a Module. \n"
        + "Parameters: "
        + PREFIX_TUTORIAL_GRP + "TUTORIAL_GROUP_CODE "
        + PREFIX_TUTORIAL_GRP_DAY + "DAY_OF_WEEK "
        + PREFIX_TUTORIAL_GRP_START_TIME + "START_TIME "
        + PREFIX_TUTORIAL_GRP_END_TIME + "END_TIME \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TUTORIAL_GRP + "T03 "
        + PREFIX_TUTORIAL_GRP_DAY + "MON "
        + PREFIX_TUTORIAL_GRP_START_TIME + "11:00 "
        + PREFIX_TUTORIAL_GRP_END_TIME + "13:00";

    private final TutorialGroup toAdd;

    /**
     * Main constructor, called by the AddTutorialGroupCommand Parser
     * @param tutorialGroup
     */
    public AddTutorialGroupCommand(TutorialGroup tutorialGroup) {
        requireNonNull(tutorialGroup);
        toAdd = tutorialGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        if (model.isInModuleView()) {
            throw new CommandException(MESSAGE_IN_MODULE_VIEW);
        } else if (model.isInStudentView()) {
            throw new CommandException(MESSAGE_NOT_IN_TUTORIAL_VIEW);
        } else if (lastShownList.stream().anyMatch(tg -> tg.isSame(toAdd))) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.addTutorialGroup(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTutorialGroupCommand // instanceof handles nulls
                && toAdd.equals(((AddTutorialGroupCommand) other).toAdd));
    }
}
