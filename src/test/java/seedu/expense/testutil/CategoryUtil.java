package seedu.expense.testutil;

import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.logic.commands.AddCategoryCommand;
import seedu.expense.model.tag.Tag;

/**
 * A utility class for Tag.
 */
public class CategoryUtil {

    /**
     * Returns an add-category command for adding the {@code tag}.
     */
    public static String getAddCategoryCommand(Tag tag) {
        return AddCategoryCommand.COMMAND_WORD + " " + getCategoryDetails(tag);
    }

    /**
     * Returns the part of the command string for the given {@code tag}'s details.
     */
    public static String getCategoryDetails(Tag tag) {
        return PREFIX_TAG + tag.tagName;
    }
}
