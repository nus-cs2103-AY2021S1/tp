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
            + "\n\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + "\n\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 ";
    public static final String SHOW_COMMAND_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>";
    public static final String MESSAGE_SUCCESS = "%s's file path: %s";

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
        return new CommandResult(String.format(MESSAGE_SUCCESS, tag.getTagName(), tag.getFileAddress()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && predicate.equals(((ShowCommand) other).predicate));
    }
}
