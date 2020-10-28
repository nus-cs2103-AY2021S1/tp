package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.label.Label;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

/**
 * Deletes a specified tag's label(s).
 */
public class UnlabelCommand extends Command {
    public static final String COMMAND_WORD = "unlabel";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes Label(s) in an existing Tag. "
            + "\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + PREFIX_LABEL_NAME + "LABEL_NAME "
            + "[" + PREFIX_LABEL_NAME + "LABEL_NAME" + "]...\n"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 "
            + PREFIX_LABEL_NAME + "notes "
            + PREFIX_LABEL_NAME + "CS2103T";

    public static final String UNLABEL_COMMAND_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>"
            + " " + PREFIX_LABEL_NAME + "<FILEPATH>";
    public static final String MESSAGE_SUCCESS = "Tag successfully updated: %1$s";
    public static final String MESSAGE_LABEL_MISSING = "All of this label can't be found in %s:\n";
    public static final String MESSAGE_DASH = "- %s\n";
    public static final String MESSAGE_INVALID_LABEL = "The remaining labels that are not "
            + "listed above have been deleted.";
    public static final String MESSAGE_TAG_NOT_FOUND = "Tag: %s not found!"
            + " Please make sure that Tag is present before deleting its label.";

    private final TagName tagName;
    private final Set<Label> labels;

    /**
     * Creates an UnlabelCommand to delete labels to the specified {@code tagName}
     */
    public UnlabelCommand(TagName tagName, Set<Label> labels) {
        this.tagName = tagName;
        this.labels = labels;
    }

    /**
     * Executes the command and deletes the specified tag's labels according to the arguments.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A Command result of executing unlabel command.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {

        // Check if tag exists
        List<Tag> tagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(tagName));
        if (tagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND, tagName.toString()));
        }

        // Get the tag
        Tag tagToChange = tagList.get(0);
        FileAddress fileAddress = tagToChange.getFileAddress();

        // Deletes the current tag from the model
        model.deleteTag(tagToChange);

        // Deletes all the matching labels
        Set<Label> editedLabel = tagToChange.getLabels().stream()
                .filter(label -> {
                    if (labels.stream().anyMatch(label::equals)) {
                        labels.remove(label);
                        return false;
                    }

                    return true;
                })
                .collect(Collectors.toSet());

        Tag editedTag = new Tag(tagName, fileAddress, editedLabel);

        // Adds the edited tag to the model
        model.addTag(editedTag);

        model.commitAddressBook();

        if (!labels.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format(MESSAGE_LABEL_MISSING, tagName));

            labels.forEach(label -> builder.append(String.format(MESSAGE_DASH, label)));

            builder.append(MESSAGE_INVALID_LABEL);
            return new CommandResult(builder.toString());
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedTag));

    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnlabelCommand // instanceof handles nulls
                && (this.labels.equals(((UnlabelCommand) other).labels))
                && this.tagName.equals(((UnlabelCommand) other).tagName));
    }
}
