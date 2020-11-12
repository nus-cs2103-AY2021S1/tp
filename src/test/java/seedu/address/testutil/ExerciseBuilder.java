package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.exercise.Exercise;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Exercise objects.
 */
public class ExerciseBuilder {

    public static final String DEFAULT_NAME = "Squats";

    private Name name;
    private Set<Tag> tags;

    /**
     * Creates a {@code ExerciseBuilder} with the default details.
     */
    public ExerciseBuilder() {
        name = new Name(DEFAULT_NAME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the ExerciseBuilder with the data of {@code exerciseToCopy}.
     */
    public ExerciseBuilder(Exercise exerciseToCopy) {
        name = exerciseToCopy.getName();
        tags = new HashSet<>(exerciseToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code exercise} that we are building.
     */
    public ExerciseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Exercise} that we are building.
     */
    public ExerciseBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Exercise build() {
        return new Exercise(name, tags);
    }
}
