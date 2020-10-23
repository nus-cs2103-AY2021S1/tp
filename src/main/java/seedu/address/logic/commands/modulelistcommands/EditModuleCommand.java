package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ZOOM_LINK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing Module in the address book.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "editmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ZOOM_LINK + "www.zoom-link3.com";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list.";

    private final String moduleToEdit;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * @param moduleToEdit  the name of the module to edit
     * @param editModuleDescriptor details to edit the Module with
     */
    public EditModuleCommand(String moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        requireNonNull(moduleToEdit);
        requireNonNull(editModuleDescriptor);

        this.moduleToEdit = moduleToEdit;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module module = null;
        List<Module> lastShownList = model.getFilteredModuleList();
        for (Module eachModule : lastShownList) {
            if (eachModule.getName().fullName.equals(moduleToEdit)) {
                module = eachModule;
                break;
            }
        }

        if (module == null) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Module editedModule = createEditedModule(module, editModuleDescriptor);

        if (!module.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.setModule(module, editedModule);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;
        // Name updatedName = editModuleDescriptor.getName().orElse(ModuleToEdit.getName());
        // Email updatedEmail = editModuleDescriptor.getEmail().orElse(ModuleToEdit.getEmail());
        // Address updatedAddress = editModuleDescriptor.getAddress().orElse(ModuleToEdit.getAddress());
        Set<Tag> updatedTags = editModuleDescriptor.getTags().orElse(moduleToEdit.getTags());
        // return new Module(updatedName, updatedEmail, updatedAddress, updatedTags);
        ModuleName moduleName = editModuleDescriptor.getModuleName().orElse(moduleToEdit.getName());
        ZoomLink zoomLink = editModuleDescriptor.getZoomLink().orElse(moduleToEdit.getLink());
        GradeTracker gradeTracker = gradeTracker = moduleToEdit.getGradeTracker();
        if (editModuleDescriptor.gradePoint != null) {
            gradeTracker.setGradePoint(Optional.of(editModuleDescriptor.gradePoint));
        }
        ModularCredits modularCredits = editModuleDescriptor
                .getModularCredits().orElse((moduleToEdit.getModularCredits()));
        return new Module(moduleName, zoomLink, gradeTracker, updatedTags, modularCredits);

    }

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
        return moduleToEdit.equals(e.moduleToEdit)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        //private Name name;
        //private Email email;
        private Set<Tag> tags;
        private ModuleName moduleName;
        private ZoomLink zoomLink;
        private GradeTracker gradeTracker;
        private ModularCredits modularCredits;
        private GradePoint gradePoint;

        public EditModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            //setName(toCopy.name);
            //setEmail(toCopy.email);
            setTags(toCopy.tags);
            setModuleName(toCopy.moduleName);
            setZoomLink(toCopy.zoomLink);
            setGradeTracker(toCopy.gradeTracker);
            setModularCredits(toCopy.modularCredits);
            setGradePoint(toCopy.gradePoint);
            //this.gradeTracker.setGradePoint(toCopy.gradeTracker.getGradePoint());
        }

        /*
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleName, zoomLink, modularCredits, gradePoint);
        }

        /*public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }*/

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }


        public void setModuleName(ModuleName moduleName) {
            this.moduleName = moduleName;
        }

        public void setZoomLink(ZoomLink zoomLink) {
            this.zoomLink = zoomLink;
        }

        public void setGradeTracker(GradeTracker gradeTracker) {
            this.gradeTracker = gradeTracker;
        }

        public void setGradePoint(GradePoint gradePoint) {
            this.gradePoint = gradePoint;
        }

        /**
         * Sets {@code modularCredits} to this object's {@code modularCredits}.
         * A defensive copy of {@code modularCredits} is used internally.
         */
        public void setModularCredits(ModularCredits modularCredits) {
            this.modularCredits = modularCredits;
        }

        public Optional<ModuleName> getModuleName() {
            return Optional.ofNullable(moduleName);
        }

        public Optional<ZoomLink> getZoomLink() {
            return Optional.ofNullable(zoomLink);
        }

        public Optional<GradeTracker> getGradeTracker() {
            return Optional.ofNullable(gradeTracker);
        }
        public Optional<ModularCredits> getModularCredits() {
            return Optional.ofNullable(modularCredits);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditModuleDescriptor)) {
                return false;
            }

            // state check
            EditModuleDescriptor e = (EditModuleDescriptor) other;

            //return getName().equals(e.getName())
            //       && getEmail().equals(e.getEmail())
            //       && getTags().equals(e.getTags());
            return getModuleName().equals(e.getModuleName())
                    && getZoomLink().equals(e.getZoomLink())
                    && getGradeTracker().equals(e.getGradeTracker())
                    && getModularCredits().equals(e.getModularCredits());
        }
    }
}
