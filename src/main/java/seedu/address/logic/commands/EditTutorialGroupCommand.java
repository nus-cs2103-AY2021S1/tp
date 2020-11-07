package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GRP_START_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;
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
        + PREFIX_TUTORIAL_GRP + "B015"
        + PREFIX_TUTORIAL_GRP_DAY + "DAY_OF_WEEK "
        + PREFIX_TUTORIAL_GRP_START_TIME + "START_TIME (24HR HH:MM FORMAT) "
        + PREFIX_TUTORIAL_GRP_END_TIME + "END_TIME (24HR HH:MM FORMAT) \n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TUTORIAL_GRP + "T03 "
        + PREFIX_TUTORIAL_GRP_DAY + "MON "
        + PREFIX_TUTORIAL_GRP_START_TIME + "11:00 "
        + PREFIX_TUTORIAL_GRP_END_TIME + "13:00";;

    public static final String MESSAGE_EDIT_TUTORIAL_SUCCESS = "Edited Tutorial Group: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This Tutorial  already exists in this Module.";
    public static final String MESSAGE_NOT_IN_TUTORIAL_VIEW =
            "You are currently not in the Module view. Run listMod to go back to the module view.";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private EditTutorialGroupDescriptor editTutorialGroupDescriptor;


    /**
     * Constructor for EditTutorialGroupCommand
     * @param index
     * @param editTutorialGroupDescriptor
     */
    public EditTutorialGroupCommand(Index index, EditTutorialGroupDescriptor editTutorialGroupDescriptor) {
        this.index = index;
        this.editTutorialGroupDescriptor = editTutorialGroupDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        if (!model.isInTutorialGroupView()) {
            throw new CommandException(MESSAGE_NOT_IN_TUTORIAL_VIEW);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX);
        }

        TutorialGroup tutorialGroupToEdit = lastShownList.get(index.getZeroBased());
        TutorialGroup editedTutorialGroup;
        editedTutorialGroup = createEditedTutorialGroup(tutorialGroupToEdit, editTutorialGroupDescriptor);

        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);

        if (!tutorialGroupToEdit.isSame(editedTutorialGroup) && model.hasTutorialGroup(editedTutorialGroup)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.setTutorialGroup(tutorialGroupToEdit, editedTutorialGroup);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTORIAL_SUCCESS, editedTutorialGroup));
    }

    private static TutorialGroup createEditedTutorialGroup(TutorialGroup tutorialGroupToEdit,
                                                           EditTutorialGroupDescriptor editTutorialGroupDescriptor) {
        assert tutorialGroupToEdit != null;

        TutorialGroupId updatedId = editTutorialGroupDescriptor.getId()
            .orElse(tutorialGroupToEdit.getId());
        DayOfWeek updatedDayOfWeek = editTutorialGroupDescriptor.getDayOfWeek()
            .orElse(tutorialGroupToEdit.getDayOfWeek());
        TimeOfDay updatedStartTime = editTutorialGroupDescriptor.getStartTime()
            .orElse(tutorialGroupToEdit.getStartTime());
        TimeOfDay updatedEndTime = editTutorialGroupDescriptor.getEndTime()
            .orElse(tutorialGroupToEdit.getEndTime());

        return new TutorialGroup(updatedId, updatedDayOfWeek, updatedStartTime,
            updatedEndTime, tutorialGroupToEdit.getUniqueStudentList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof EditTutorialGroupCommand // instanceof handles nulls
            && index.equals(((EditTutorialGroupCommand) other).index)
            && editTutorialGroupDescriptor.equals(((EditTutorialGroupCommand) other)
            .editTutorialGroupDescriptor)); // state check
    }

    public static class EditTutorialGroupDescriptor {
        private TutorialGroupId tutorialGroupId;
        private DayOfWeek dayOfWeek;
        private TimeOfDay startTime;
        private TimeOfDay endTime;

        public EditTutorialGroupDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTutorialGroupDescriptor(EditTutorialGroupDescriptor toCopy) {
            setId(toCopy.tutorialGroupId);
            setDayOfWeek(toCopy.dayOfWeek);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
        }

        /**
         * Constructor for EditTutorialGroupDescriptor
         * @param tutorialGroup
         */
        public EditTutorialGroupDescriptor(TutorialGroup tutorialGroup) {
            setId(tutorialGroup.getId());
            setDayOfWeek(tutorialGroup.getDayOfWeek());
            setStartTime(tutorialGroup.getStartTime());
            setEndTime(tutorialGroup.getEndTime());
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tutorialGroupId, dayOfWeek, startTime, endTime);
        }

        public void setId(TutorialGroupId tutorialGroupId) {
            this.tutorialGroupId = tutorialGroupId;
        }

        public Optional<TutorialGroupId> getId() {
            return Optional.ofNullable(tutorialGroupId);
        }

        public void setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

        public Optional<DayOfWeek> getDayOfWeek() {
            return Optional.ofNullable(dayOfWeek);
        }

        public void setStartTime(TimeOfDay startTime) {
            this.startTime = startTime;
        }

        public Optional<TimeOfDay> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(TimeOfDay endTime) {
            this.endTime = endTime;
        }

        public Optional<TimeOfDay> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTutorialGroupDescriptor)) {
                return false;
            }

            // state check
            EditTutorialGroupDescriptor e = (EditTutorialGroupDescriptor) other;

            return getId().equals(e.getId())
                && getDayOfWeek().equals(e.getDayOfWeek())
                && getStartTime().equals(e.getStartTime())
                && getEndTime().equals(e.getEndTime());
        }
    }
}
