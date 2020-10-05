package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.TutorialGroup;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a Student's Module in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleId(String)}
 */
public class Module {
    public static final String MESSAGE_CONSTRAINTS = "Modules can take any values, and it should not be blank";

    /*
     * The first character of the module must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final String moduleId;
    private List<TutorialGroup> tutorialGroups;
    //    private List<Tasks> tasks;

    /**
     * Constructs an {@code Module}.
     *
     * @param moduleId
     */
    public Module(String moduleId) {
        requireNonNull(moduleId);
        checkArgument(isValidModuleId(moduleId), MESSAGE_CONSTRAINTS);
        this.moduleId = moduleId;
        this.tutorialGroups = new ArrayList<>();
        // this.tasks = new ArrayList<>();
    }

    /**
     * Returns true if a given string is a valid module.
     * @param test input moduleId to test
     * @return boolean that indicates if string is valid
     */
    public static boolean isValidModuleId(String test) {
        return test.matches(VALIDATION_REGEX);
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && getModuleId().equals(((Module) other).getModuleId())); // state check
    }

    @Override
    public String toString() {
        return getModuleId();
    }
}
