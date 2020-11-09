package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_POINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;

/**
 * Encapsulates methods and information to edits the details of an existing Module in the module list.
 */
public class EditModuleCommand extends Command {

    public static final String COMMAND_WORD = "editmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the module identified "
            + "by the index number used in the displayed module list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_MODULAR_CREDITS + "MODULAR CREDITS" + "] "
            + "[" + PREFIX_GRADE_POINT + "GRADE POINT" + "] "
            + "[" + PREFIX_TAG + "TAG" + "] " + "..."
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "CS2100 " + PREFIX_MODULAR_CREDITS + "4.0 "
            + PREFIX_GRADE_POINT + "5.0 "
            + PREFIX_TAG + "Coremodule ";

    public static final String MESSAGE_EDIT_MODULE_SUCCESS = "Edited module: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list.";

    private final Index index;
    private final EditModuleDescriptor editModuleDescriptor;

    /**
     * Creates and initialises a new EditModuleCommand to edit a module in the module list.
     *
     * @param index  object encapsulating the index of the module to edit from the filtered
     *               displayed module list.
     * @param editModuleDescriptor object that encapsulates details to edit the Module with.
     */
    public EditModuleCommand(Index index, EditModuleDescriptor editModuleDescriptor) {
        requireAllNonNull(index, editModuleDescriptor);

        this.index = index;
        this.editModuleDescriptor = new EditModuleDescriptor(editModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }
        Module module = lastShownList.get(index.getZeroBased());
        Module editedModule = createEditedModule(module, editModuleDescriptor);

        if (!module.isSameModule(editedModule) && model.hasModule(editedModule)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }
        if (model.getModuleListDisplay()) {
            model.setArchivedModule(module, editedModule);
        } else {
            model.setModule(module, editedModule);
        }
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_EDIT_MODULE_SUCCESS, editedModule));
    }

    /**
     * Creates and returns a {@code module} with the details of {@code moduleToEdit}
     * edited with {@code editModuleDescriptor}.
     */
    private static Module createEditedModule(Module moduleToEdit, EditModuleDescriptor editModuleDescriptor) {
        assert moduleToEdit != null;
        Set<Tag> updatedTags = editModuleDescriptor.getTags().orElse(moduleToEdit.getTags());
        ModuleName moduleName = editModuleDescriptor.getModuleName().orElse(moduleToEdit.getName());
        GradeTracker gradeTracker = moduleToEdit.getGradeTracker();
        Map<ModuleLesson, ZoomLink> zoomLinks = new HashMap<>();
        zoomLinks.putAll(editModuleDescriptor.getZoomLinks().orElse(moduleToEdit.getAllLinks()));
        if (editModuleDescriptor.getGradePoint().isPresent()) {
            gradeTracker.setGradePoint(editModuleDescriptor.getGradePoint().get());
        }
        ModularCredits modularCredits = editModuleDescriptor
                .getModularCredits().orElse((moduleToEdit.getModularCredits()));
        return new Module(moduleName, zoomLinks, gradeTracker, updatedTags, modularCredits);

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
        return index.equals(e.index)
                && editModuleDescriptor.equals(e.editModuleDescriptor);
    }

    /**
     * Stores the details to edit the module with. Each non-empty field value will replace the
     * corresponding field value of the module.
     */
    public static class EditModuleDescriptor {
        private Set<Tag> tags;
        private ModuleName moduleName;
        private Map<ModuleLesson, ZoomLink> zoomLinks;
        private GradeTracker gradeTracker;
        private ModularCredits modularCredits;
        private GradePoint gradePoint;

        public EditModuleDescriptor() {}
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditModuleDescriptor(EditModuleDescriptor toCopy) {
            setTags(toCopy.tags);
            setModuleName(toCopy.moduleName);
            setZoomLinks(toCopy.zoomLinks);
            setGradeTracker(toCopy.gradeTracker);
            setModularCredits(toCopy.modularCredits);
            setGradePoint(toCopy.gradePoint);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(moduleName, zoomLinks, modularCredits, gradePoint, tags);
        }

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

        /**
         * Sets {@code zoomLinks} to this object's {@code zoomLinks}.
         * A defensive copy of {@code zoomLinks} is used internally.
         */
        public void setZoomLinks(Map<ModuleLesson, ZoomLink> zoomLinks) {
            this.zoomLinks = (zoomLinks != null) ? new HashMap<>(zoomLinks) : null;
        }

        /**
         * Returns an unmodifiable zoomLinks map, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code zoomLinks} is null.
         */
        public Optional<Map<ModuleLesson, ZoomLink>> getZoomLinks() {
            return (zoomLinks != null) ? Optional.of(Collections.unmodifiableMap(zoomLinks)) : Optional.empty();
        }

        /**
         * Sets {@code moduleName} to this object's {@code moduleName}.
         * A defensive copy of {@code moduleName} is used internally.
         */
        public void setModuleName(ModuleName moduleName) {
            this.moduleName = moduleName;
        }

        /**
         * Sets {@code gradeTracker} to this object's {@code gradeTracker}.
         * A defensive copy of {@code gradeTracker} is used internally.
         */
        public void setGradeTracker(GradeTracker gradeTracker) {
            this.gradeTracker = gradeTracker;
        }
        /**
         * Sets {@code gradePoint} to this object's {@code gradePoint}.
         * A defensive copy of {@code gradePoint} is used internally.
         */
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

        public Optional<GradeTracker> getGradeTracker() {
            return Optional.ofNullable(gradeTracker);
        }
        public Optional<ModularCredits> getModularCredits() {
            return Optional.ofNullable(modularCredits);
        }
        public Optional<GradePoint> getGradePoint() {
            return Optional.ofNullable(gradePoint);
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

            return getModuleName().equals(e.getModuleName())
                    && getZoomLinks().equals(e.getZoomLinks())
                    && getGradeTracker().equals(e.getGradeTracker())
                    && getModularCredits().equals(e.getModularCredits())
                    && getTags().equals(e.getTags());
        }
    }
}
