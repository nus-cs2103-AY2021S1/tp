package seedu.address.testutil;

import seedu.address.logic.commands.UpdateExerciseCommand.EditExerciseDescriptor;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
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
    public EditExerciseDescriptorBuilder withCalories(String calories) {
        descriptor.setCalories(new Calories(calories));
        return this;
    }

    public EditExerciseDescriptor build() {
        return descriptor;
    }
}
