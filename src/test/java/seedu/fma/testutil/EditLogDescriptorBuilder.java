package seedu.fma.testutil;


import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.commands.EditCommand.EditLogDescriptor;
import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;
import seedu.fma.model.util.Name;


/**
 * A utility class to help with building EditLogDescriptor objects.
 */

public class EditLogDescriptorBuilder {

    private EditCommand.EditLogDescriptor descriptor;

    public EditLogDescriptorBuilder() {
        descriptor = new EditCommand.EditLogDescriptor();
    }

    public EditLogDescriptorBuilder(EditCommand.EditLogDescriptor descriptor) {
        this.descriptor = new EditLogDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLogDescriptor} with fields containing {@code log}'s details
     */
    public EditLogDescriptorBuilder(Log log) {
        descriptor = new EditCommand.EditLogDescriptor();
        descriptor.setExercise(log.getExercise());
        descriptor.setComment(log.getComment());
        descriptor.setRep(log.getReps());
        descriptor.setDatetime(log.getDateTime());
    }

    /**
     * Sets the {@code Exercise} of the {@code EditLogDescriptor} that we are building.
     */
    public EditLogDescriptorBuilder withExercise(Exercise exercise) {
        descriptor.setExercise(exercise);
        return this;
    }

    /**
     * Sets the {@code Exercise} of the {@code EditLogDescriptor} that we are building.
     */
    public EditLogDescriptorBuilder withExerciseName(String name, LogBook logBook) {
        descriptor.setExercise(logBook.getExercise(new Name(name)));
        return this;
    }

    /**
     * Sets the {@code Rep} of the {@code EditLogDescriptor} that we are building.
     */
    public EditLogDescriptorBuilder withReps(Rep reps) {
        descriptor.setRep(reps);
        return this;
    }

    /**
     * Sets the {@code Rep} of the {@code EditLogDescriptor} that we are building.
     */
    public EditLogDescriptorBuilder withReps(int reps) {
        descriptor.setRep(new Rep(reps));
        return this;
    }

    /**
     * Sets the {@code Comment} of the {@code EditLogDescriptor} that we are building.
     */
    public EditLogDescriptorBuilder withComment(String comment) {
        descriptor.setComment(new Comment(comment));
        return this;
    }

    /**
     * Builds the EditCommand
     */
    public EditCommand.EditLogDescriptor build() {
        return descriptor;
    }
}
