package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TAG_INPUT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

/**
 * Shows a Tag's file path identified using it's tag name.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows a tag's file address. "
            + "\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 ";
    public static final String SHOW_MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>";
    public static final String MESSAGE_SUCCESS = "%s's file path: %s";
    public static final String NO_LABEL_IN_TAG = "\nYou haven't add any label to this tag!";
    private final TagNameEqualsKeywordPredicate predicate;

    /**
     * Creates an ShowCommand to show the specified {@code Tag}'s file path
     */
    public ShowCommand(TagNameEqualsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> tags = model.findFilteredTagList(predicate);

        if (tags.isEmpty()) {
            throw new CommandException(MESSAGE_INVALID_TAG_INPUT);
        }

        Tag tag = tags.get(0);

        StringBuilder builder = new StringBuilder();
        builder.append(String.format(MESSAGE_SUCCESS, tag.getTagName(), tag.getFileAddress()));

        if (tag.getLabels().isEmpty()) {
            builder.append(NO_LABEL_IN_TAG);
        } else {
            builder.append(tag.getLabels().size() == 1 ? "\nLabel: " : "\nLabels: ");
            tag.getLabels().forEach(builder::append);
        }

        return new CommandResult(builder.toString().trim());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && predicate.equals(((ShowCommand) other).predicate));
    }
}
