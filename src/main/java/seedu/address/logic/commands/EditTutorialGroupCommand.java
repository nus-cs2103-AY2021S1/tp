package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;
import java.time.LocalTime;
import java.util.List;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.person.UniqueStudentList;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.TutorialGroupId;

public class EditTutorialGroupCommand extends Command {

    public static final String COMMAND_WORD = "editTG";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specifics of the Tutorial Group specified "
        + "by the index number used in the displayed Tutorial Group list. "
        + "\nExisting values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "TUTORIAL_GROUP_INDEX "
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_TUTORIAL_GRP + "B015";

    public static final String MESSAGE_EDIT_TUTORIAL_SUCCESS = "Edited Tutorial Group: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This Tutorial Group already exists in this Module.";
    public static final String MESSAGE_NOT_IN_TUTORIAL_VIEW = "You are currently not in the Module view. Run listMod to go back to the module view.";

    private final Index index;
    private final TutorialGroupId tutorialGroupId;
    private final String dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public EditTutorialGroupCommand(Index index, TutorialGroupId tutorialGroupId, String dayOfWeek,
                                    LocalTime startTime, LocalTime endTime) {
        requireNonNull(index);
        requireNonNull(tutorialGroupId);

        this.index = index;
        this.tutorialGroupId = tutorialGroupId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (!model.isInTutorialGroupView()) {
            throw new CommandException(MESSAGE_NOT_IN_TUTORIAL_VIEW);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        TutorialGroup tutorialGroupToEdit = model.getFilteredTutorialGroupList().get(index.getZeroBased());
        UniqueStudentList originalStudentList = tutorialGroupToEdit.getUniqueStudentList();
        TutorialGroup editedTutorialGroup = new TutorialGroup(tutorialGroupId, originalStudentList,
            dayOfWeek, startTime, endTime);

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        if (!tutorialGroupToEdit.isSame(editedTutorialGroup) && model.hasTutorialGroup(editedTutorialGroup)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.setTutorialGroup(tutorialGroupToEdit, editedTutorialGroup);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTORIAL_SUCCESS, editedTutorialGroup), false, false, true, false, false);
    }
}
