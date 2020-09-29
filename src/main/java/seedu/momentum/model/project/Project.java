package seedu.momentum.model.project;

import static seedu.momentum.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.momentum.model.tag.Tag;
import seedu.momentum.model.timer.Timer;
import seedu.momentum.model.timer.UniqueDurationList;
import seedu.momentum.model.timer.WorkDuration;

/**
 * Represents a Project in the project book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Project {

    // Identity fields
    private final Name name;

    private final Set<Tag> tags = new HashSet<>();

    private final Timer timer;

    private final UniqueDurationList durations;

    /**
     * Constructs a {@code Project}.
     *
     * @param name A valid name.
     * @param tags A set of tags associated to the project.
     * @param durations A list of {@code WorkDuration} associated with the project
     */
    public Project(Name name, Set<Tag> tags, UniqueDurationList durations, Timer timer) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.tags.addAll(tags);
        this.durations = durations;
        this.timer = timer;
    }

    /**
     * Constructs a new {@code Project}
     *
     * @param name A valid name.
     * @param tags A set of tags associated to the project.
     */
    public Project(Name name, Set<Tag> tags) {
        requireAllNonNull(name, tags);
        this.name = name;
        this.tags.addAll(tags);
        this.durations = new UniqueDurationList();
        this.timer = new Timer();
    }

    public Name getName() {
        return name;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an unmodifiable view of the durations list.
     */
    public ObservableList<WorkDuration> getDurationList() {
        return durations.asUnmodifiableObservableList();
    }

    /**
     * Returns a copy of this project with its timer started.
     *
     * @return A copy of this project, but with its timer started
     */
    public Project startTimer() {
        Timer newTimer = timer.start();
        return new Project(name, tags, durations, newTimer);
    }

    /**
     * Returns a copy of this project with its timer stopped, then adds the timed duration into
     * the list.
     *
     * @return A copy of this project, but with its timer stopped
     */
    public Project stopTimer() {
        Timer newTimer = timer.stop();
        WorkDuration duration = new WorkDuration(newTimer.getStartTime(), newTimer.getStopTime());
        UniqueDurationList newDurations = new UniqueDurationList();
        newDurations.setDurations(durations);
        newDurations.add(duration);
        return new Project(name, tags, newDurations, newTimer);
    }

    public Timer getTimer() {
        return timer;
    }

    /**
     * Checks if the project's timer is currently running.
     */
    public boolean isRunning() {
        return timer.isRunning();
    }

    /**
     * Returns true if both projects of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two projects.
     */
    public boolean isSameProject(Project otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName());
    }

    /**
     * Returns true if both projects have the same identity and data fields.
     * This defines a stronger notion of equality between two projects.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Project)) {
            return false;
        }

        Project otherProject = (Project) other;
        return otherProject.getName().equals(getName())
                && otherProject.getTags().equals(getTags())
                && durations.equals(((Project) other).durations);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
