package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateCommand.EditExerciseDescriptor;
import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;
import seedu.address.model.exercise.Name;


/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditExerciseDescriptorBuilder {

    private EditExerciseDescriptor descriptor;

    public EditExerciseDescriptorBuilder() {
        descriptor = new EditExerciseDescriptor();
    }

    public EditExerciseDescriptorBuilder(EditExerciseDescriptor descriptor) {
        this.descriptor = new EditExerciseDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExerciseDescriptorBuilder} with fields containing {@code exercise}'s details
     */
    public EditExerciseDescriptorBuilder(Exercise exercise) {
        descriptor = new EditExerciseDescriptor();
        descriptor.setName(exercise.getName());
        descriptor.setDate(exercise.getDate());
        descriptor.setDescription(exercise.getDescription());
        descriptor.setCalories(exercise.getCalories());
        descriptor.setMuscleTags(exercise.getMuscleTags());
        descriptor.setTags(exercise.getExerciseTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Calories} of the {@code EditExerciseDescriptor} that we are building.
     */
    public EditExerciseDescriptorBuilder withCalories(String calories) throws CaloriesOverflow {
        descriptor.setCalories(new Calories(calories));
        return this;
    }

    /**
     * Parses the {@code muscleTags} into a {@code Set<MuscleTag>} and set it to the {@code EditExerciseDescriptor}
     * that we are building.
     */
    public EditExerciseDescriptorBuilder withMuscleTags(String... muscleTags) {
        Set<MuscleTag> tagSet = Stream.of(muscleTags).map(MuscleTag::new).collect(Collectors.toSet());
        descriptor.setMuscleTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<ExerciseTag>} and set it to the {@code EditExerciseDescriptor}
     * that we are building.
     */
    public EditExerciseDescriptorBuilder withTags(String... tags) {
        Set<ExerciseTag> tagSet = Stream.of(tags).map(ExerciseTag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditExerciseDescriptor build() {
        return descriptor;
    }
}
