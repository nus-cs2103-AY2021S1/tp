package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.Showable;
import seedu.address.model.TaskList;
import seedu.address.model.tutorial_group.TutorialGroup;


public class Module implements Showable<Module> {

    private final ModuleId moduleId;
    private List<TutorialGroup> tutorialGroups;
    private TaskList taskList;
    private int totalStudents = 0;
    private int totalGroups = 0;

    /**
     * Constructs an {@code Module}.
     *
     * @param moduleId
     */
    public Module(ModuleId moduleId) {
        requireNonNull(moduleId);
        this.moduleId = moduleId;
        this.tutorialGroups = new ArrayList<>();
        this.taskList = new TaskList();
    }

    public Module(ModuleId moduleId, List<TutorialGroup> tutorialGroups) {
        requireNonNull(moduleId);
        requireNonNull(tutorialGroups);
        this.moduleId = moduleId;
        this.tutorialGroups = tutorialGroups;
        this.taskList = new TaskList();
    }


    public ModuleId getModuleId() {
        return this.moduleId;
    }

    public int getTotalStudents() {
        return this.totalStudents;
    }

    public int getTotalGroups() {
        return this.tutorialGroups.size();
    }

    public List<TutorialGroup> getTutorialGroups() {
        return Collections.unmodifiableList(tutorialGroups);
    }

    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.add(tutorialGroup);
    }

    public void removeTutorialGroup(TutorialGroup tutorialGroup) {
        tutorialGroups.remove(tutorialGroup);
    }

    //    public void addTask(Task task) {
    //        tasks.add(task);
    //    }
    //
    //    public void removeTask(Task task) {
    //        tasks.remove(task);
    //    }

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
