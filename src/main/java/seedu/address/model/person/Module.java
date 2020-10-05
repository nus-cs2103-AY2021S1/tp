package seedu.address.model.person;


import seedu.address.model.TutorialGroup;

import java.util.List;
import java.util.ArrayList;

public class Module {
    private final String moduleId;
    private List<TutorialGroup> tutorialGroups;
//    private List<Tasks> tasks;

    public Module(String moduleId) {
        this.moduleId = moduleId;
        this.tutorialGroups = new ArrayList<>();
        // this.tasks = new ArrayList<>();
    }

    public String getModuleId() {
        return this.moduleId;
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

}
