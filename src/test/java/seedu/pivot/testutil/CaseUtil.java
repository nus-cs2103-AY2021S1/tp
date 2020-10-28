package seedu.pivot.testutil;

import static seedu.pivot.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.pivot.logic.commands.AddCommand;
import seedu.pivot.logic.commands.EditCommand.EditCaseDescriptor;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.model.tag.Tag;

/**
 * A utility class for Case.
 */
public class CaseUtil {

    /**
     * Returns an add command string for adding the {@code Case}.
     */
    public static String getAddCommand(Case investigationCase) {
        return AddCommand.COMMAND_WORD + " " + AddCommand.TYPE_CASE + " " + getCaseDetails(investigationCase);
    }

    /**
     * Returns the part of command string for the given {@code Case}'s details.
     */
    public static String getCaseDetails(Case investigationCase) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + investigationCase.getTitle().getAlphaNum() + " ");
        sb.append(PREFIX_STATUS + investigationCase.getStatus().name() + " ");
        investigationCase.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCaseDescriptor}'s details.
     */
    public static String getEditCaseDescriptorDetails(EditCaseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.getAlphaNum()).append(" "));
        descriptor.getStatus().ifPresent(status -> sb.append(PREFIX_STATUS).append(status.name()).append(" "));
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
