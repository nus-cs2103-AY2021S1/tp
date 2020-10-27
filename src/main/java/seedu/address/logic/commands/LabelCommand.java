package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.FileUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.label.Label;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

/**
 * Adds label(s) to a specified tag.
 */
public class LabelCommand extends Command {
    public static final String COMMAND_WORD = "label";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds Label(s) to an existing Tag. "
            + "\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + PREFIX_LABEL_NAME + "LABEL_NAME "
            + "[" + PREFIX_LABEL_NAME + "LABEL_NAME" + "]...\n"
            + "\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 "
            + PREFIX_LABEL_NAME + "notes "
            + PREFIX_LABEL_NAME + "CS2103T";
    public static final String LABEL_COMMAND_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>"
            + " " + PREFIX_LABEL_NAME + "<FILEPATH>";
    public static final String MESSAGE_SUCCESS = "Tag successfully updated: %1$s!";
    public static final String MESSAGE_TAG_NOT_FOUND = "Tag: %s not found!"
            + " Please make sure that Tag is present before labeling.";
    public static final String MESSAGE_DUPLICATE_LABEL = "Duplicate Label!";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found at %s!"
            + " Please make sure that file is present before adding a label.";

    private final TagName tagName;
    private final Set<Label> labels;

    /**
     * Creates a LabelCommand to add labels to the specified {@code tagName}
     */
    public LabelCommand(TagName tagName, Set<Label> labels) {
        this.tagName = tagName;
        this.labels = labels;
    }

    /**
     * Executes the command and adds the labels to a specified tag in the model
     *
     * @param model {@code Model} which the command should operate on.
     * @return A Command result of executing LabelCommand.
     * @throws CommandException
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check if tag exists
        List<Tag> tagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(tagName));
        if (tagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND, tagName.toString()));
        }

        // Get the tag
        Tag tagToChange = tagList.get(0);
        // Get file address, previous labels
        FileAddress fileAddress = tagToChange.getFileAddress();

        if (!FileUtil.isFileExists(Paths.get(fileAddress.value))) {
            throw new CommandException(String.format(MESSAGE_FILE_NOT_FOUND, fileAddress));
        }

        Set<Label> previousLabels = tagToChange.getLabels();
        // Delete old tag
        model.deleteTag(tagToChange);

        // Append new labels into previous labels
        labels.addAll(previousLabels);
        // Create new tag
        Tag newTag = new Tag(tagName, fileAddress, labels);
        // Place tag into AB3
        model.addTag(newTag);

        // Save commit for undo
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, newTag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LabelCommand // instanceof handles nulls
                && (this.labels.equals(((LabelCommand) other).labels))
                        && this.tagName.equals(((LabelCommand) other).tagName));
    }
}
