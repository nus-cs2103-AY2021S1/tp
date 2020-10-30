package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleId;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "editMod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the module id of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[MODULE_ID] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MODULE + "CS2100";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited Module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in Trackr.";
    public static final String MESSAGE_NOT_IN_MODULE_VIEW = "You are currently not in the Module view. "
            + "Run listMod to go back to the Module view.";

    private final Index index;
    private final ModuleId newModuleId;

    /**
     * @param index of the person in the filtered person list to edit
     * @param newModuleId details to edit the person with
     */
    public EditModuleCommand(Index index, ModuleId newModuleId) {
        requireNonNull(index);
        requireNonNull(newModuleId);

        this.index = index;
        this.newModuleId = newModuleId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (!model.isInModuleView()) {
            throw new CommandException(MESSAGE_NOT_IN_MODULE_VIEW);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToEdit = lastShownList.get(index.getZeroBased());
        Module editedModule = new Module(newModuleId);

        if (!moduleToEdit.isSame(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(moduleToEdit, newModuleId);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule),
                false, false, false, false, true);
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    // private static Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
    //     assert moduleToEdit != null;
    //
    //     String updatedModuleId = editModuleDescriptor.getModuleId().orElse(moduleToEdit.getModuleId().toString());
    //
    //     return new Module(new ModuleId(updatedModuleId));
    // }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditModuleCommand)) {
            return false;
        }

        // state check
        EditModuleCommand e = (EditModuleCommand) other;
        return index.equals(e.index)
                && newModuleId.equals(e.newModuleId);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditModuleDescriptor {
        private String moduleId;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setModuleId(toCopy.moduleId);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleId);
        }

        public void setModuleId(String moduleId) {
            this.moduleId = moduleId;
        }

        public Optional<String> getModuleId() {
            return Optional.ofNullable(moduleId);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            return getModuleId().equals(e.getModuleId());
        }
    }
}
