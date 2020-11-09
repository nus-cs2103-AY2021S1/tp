package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_TAGS;
import static seedu.address.commons.core.Messages.MESSAGE_TAGS_SUCCESS;

import java.util.List;

import seedu.address.model.Model;

/**
 * Lists all tags in GradPad.
 */
public class TagsCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        List<String> tagNames = model.getGradPad().getTags().getTagNames();
        if (tagNames.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        String tags = String.join("\n", tagNames);
        return new CommandResult(MESSAGE_TAGS_SUCCESS + tags);
    }
}
