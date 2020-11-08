package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.UniqueTutorialGroupList;

public class Module {

    private ModuleId moduleId;

    private UniqueTutorialGroupList tutorialGroups;

    /**
     * Constructs an {@code Module}.
     *
     * @param moduleId
     */
    public Module(ModuleId moduleId) {
        requireNonNull(moduleId);
        this.moduleId = moduleId;
        this.tutorialGroups = new UniqueTutorialGroupList();
    }

    /**
     * Constructs an {@code Module}.
     * @param moduleId
     * @param tutorialGroups
     */
    public Module(ModuleId moduleId, UniqueTutorialGroupList tutorialGroups) {
        requireNonNull(moduleId);
        requireNonNull(tutorialGroups);
        this.moduleId = moduleId;
        this.tutorialGroups = tutorialGroups;
    }


    public ModuleId getModuleId() {
        return this.moduleId;
    }

    public int getTotalStudents() {
        return this.tutorialGroups.stream().map(TutorialGroup::getStudents)
                .map(List::size).reduce(Integer::sum).orElse(0);
    }

    public int getTotalGroups() {
        return this.tutorialGroups.size();
    }

    public ObservableList<TutorialGroup> getTutorialGroups() {
        return tutorialGroups.asUnmodifiableObservableList();
    }

    public UniqueTutorialGroupList getUniqueTutorialGroupList() {
        return tutorialGroups;
    }

    /**
     * Adds tutorial group to the UniqueTutorialGroupList
     * @param tutorialGroup to be added
     */
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.addTutorialGroup(tutorialGroup);
    }

    public void removeTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.removeTutorialGroup(tutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && getModuleId().equals(((Module) other).getModuleId())); // state check
    }

    /**
     * Returns true if both modules of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two modules.
     */
    public boolean isSame(Module otherModule) {
        if (otherModule == this) {
            return true;
        }

        return otherModule != null
                && otherModule.getModuleId().equals(getModuleId());
    }

    @Override
    public String toString() {
        return getModuleId().toString();
    }
}
