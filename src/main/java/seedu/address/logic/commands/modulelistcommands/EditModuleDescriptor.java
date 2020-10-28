package seedu.address.logic.commands.modulelistcommands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.module.ModularCredits;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.ZoomLink;
import seedu.address.model.module.grade.GradePoint;
import seedu.address.model.module.grade.GradeTracker;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the module with. Each non-empty field value will replace the
 * corresponding field value of the module.
 */
public class EditModuleDescriptor {
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
        setTags(toCopy.tags);
        setModuleName(toCopy.moduleName);
        setZoomLink(toCopy.zoomLink);
        setGradeTracker(toCopy.gradeTracker);
        setModularCredits(toCopy.modularCredits);
        setGradePoint(toCopy.gradePoint);
        //this.gradeTracker.setGradePoint(toCopy.gradeTracker.getGradePoint());
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(moduleName, zoomLink, modularCredits, gradePoint);
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
                && getZoomLink().equals(e.getZoomLink())
                && getGradeTracker().equals(e.getGradeTracker())
                && getModularCredits().equals(e.getModularCredits())
                && getTags().equals(e.getTags());
    }
}
