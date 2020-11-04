package seedu.fma.testutil;

import seedu.fma.logic.commands.EditExCommand;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;


/**
 * A utility class to help with building EditExDescriptor objects.
 */
public class EditExDescriptorBuilder {

    private EditExCommand.EditExDescriptor descriptor;

    public EditExDescriptorBuilder() {
        descriptor = new EditExCommand.EditExDescriptor();
    }

    public EditExDescriptorBuilder(EditExCommand.EditExDescriptor descriptor) {
        this.descriptor = new EditExCommand.EditExDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditExDescriptor} with fields containing {@code exercise}'s details
     */
    public EditExDescriptorBuilder(Exercise exercise) {
        descriptor = new EditExCommand.EditExDescriptor();
        descriptor.setExerciseName(exercise.getName());
        descriptor.setCaloriesPerRep(exercise.getCaloriesPerRep());
    }

    /**
     * Sets the {@code Name} of the {@code EditExDescriptor} that we are building.
     */
    public EditExDescriptorBuilder withExerciseName(Name name) {
        descriptor.setExerciseName(name);
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code EditLogDescriptor} that we are building.
     */
    public EditExDescriptorBuilder withCaloriesPerRep(Calories caloriesPerRep) {
        descriptor.setCaloriesPerRep(caloriesPerRep);
        return this;
    }

    /**
     * Builds the EditCommand
     */
    public EditExCommand.EditExDescriptor build() {
        return descriptor;
    }
}
