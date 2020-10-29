package seedu.address.testutil;

import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * An utility class to help with building Trackr objects.
 */
public class TrackrBuilder {

    private Trackr trackr;

    public TrackrBuilder() {
        this.trackr = new Trackr();
    }

    public TrackrBuilder(Trackr trackr) {
        this.trackr = trackr;
    }

    /**
     * Adds a new {@code Module} to the {@code Trackr} that we are building.
     */
    public TrackrBuilder withModule(Module module) {
        trackr.addModule(module);
        return this;
    }

    /**
     * Adds a new {@code TutorialGroup} to the {@code Trackr} that we are building.
     */
    public TrackrBuilder withTutorialGroup(TutorialGroup tutorialGroup, Module module) {
        trackr.addTutorialGroup(tutorialGroup, module);
        return this;
    }

    /**
     * Adds a new {@code Student} to the {@code Trackr} that we are building.
     */
    public TrackrBuilder withStudent(Student student, TutorialGroup tutorialGroup, Module module) {
        trackr.addStudent(module, tutorialGroup, student);
        return this;
    }

    public Trackr build() {
        return trackr;
    }
}
