package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exercise.ExerciseAddCommand;
import seedu.address.logic.commands.exercise.ExerciseDeleteCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand.EditExerciseDescriptor;
import seedu.address.logic.commands.exercise.ExerciseFindCommand;
import seedu.address.model.exercise.Exercise;

/**
 * A utility class for Exercise.
 */
public class ExerciseUtil {

    public static final String WHITESPACE = " ";

    /**
     * Returns an exercise add command string for adding the {@code exercise}.
     *
     * @param exercise The exercise to be added.
     * @return The exercise add command string.
     */
    public static String getExerciseAddCommand(Exercise exercise) {
        return ExerciseAddCommand.COMMAND_WORD + WHITESPACE + getExerciseDetails(exercise);
    }

    /**
     * Returns the part of command string for the given {@code exercise}'s details.
     */
    private static String getExerciseDetails(Exercise exercise) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_EXERCISE).append(exercise.getName().fullName).append(WHITESPACE);
        exercise.getTags().forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(WHITESPACE));
        return sb.toString();
    }

    /**
     * Returns an exercise edit command string for editing the {@code exercise}.
     *
     * @param index The index of the exercise.
     * @param descriptor The descriptor of the edited exercise.
     * @return The exercise edit command string.
     */
    public static String getExerciseEditCommand(Index index, EditExerciseDescriptor descriptor) {
        return ExerciseEditCommand.COMMAND_WORD + WHITESPACE
                + index.getOneBased() + WHITESPACE + getEditExerciseDescriptorDetails(descriptor);
    }

    /**
     * Returns the part of command string for the given {@code EditExerciseDescriptor}'s details.
     */
    private static String getEditExerciseDescriptorDetails(EditExerciseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_EXERCISE).append(name.fullName).append(WHITESPACE));
        descriptor.getTags().ifPresent(tags -> {
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(WHITESPACE));
            }
        });
        return sb.toString();
    }

    /**
     * Returns a exercise delete command string.
     *
     * @param index The index of the exercise.
     * @return The exercise delete command string.
     */
    public static String getExerciseDeleteCommand(Index index) {
        return ExerciseDeleteCommand.COMMAND_WORD + WHITESPACE + index.getOneBased();
    }

    /**
     * Returns an exercise find command string.
     *
     * @param keyword The keyword for the find command.
     * @return The exercise find command string.
     */
    public static String getExerciseFindCommand(String keyword) {
        return ExerciseFindCommand.COMMAND_WORD + WHITESPACE + keyword;
    }

}
