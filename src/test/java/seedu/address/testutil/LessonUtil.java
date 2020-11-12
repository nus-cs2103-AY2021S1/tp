package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.lessons.LessonAddCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand.EditLessonDescriptor;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Lesson.
 */
public class LessonUtil {

    /**
     * Returns a lesson add command string for adding the {@code lesson}.
     */
    public static String getLessonAddCommand(Lesson lesson) {
        return LessonAddCommand.COMMAND_WORD + " " + getLessonDetails(lesson);
    }

    /**
     * Returns the part of command string for the given {@code lesson}'s details.
     */
    public static String getLessonDetails(Lesson lesson) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_LESSON + lesson.getName().fullName + " ");
        lesson.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditLessonDescriptor}'s details.
     */
    public static String getEditLessonDescriptorDetails(EditLessonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_LESSON).append(name.fullName).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
