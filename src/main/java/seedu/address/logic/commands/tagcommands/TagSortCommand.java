package seedu.address.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.tag.TagNameComparator;

/**
 *  Sort all the tags in Projact by their names in alphabetical order.
 */
public class TagSortCommand extends Command {
    public static final String COMMAND_WORD = "tagsort";

    public static final String MESSAGE_SUCCESS = "These are the tags in your tag list sorted in "
                                            + "alphanumerical order.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedTagList(new TagNameComparator());
        return new CommandResult(MESSAGE_SUCCESS,
                false, false, true, false, true, false);
    }
}
