package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagLabelEqualsKeywordPredicate;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens the file specified in the filepath of a tag or label. \n"
            + "This command accepts either tag or label but not both."
            + "\n\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME or " + PREFIX_LABEL_NAME + "LABEL_NAME "
            + "\n\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 ";
    public static final String OPEN_COMMAND_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "TAG_NAME" + " OR "
            + COMMAND_WORD + " " + PREFIX_LABEL_NAME + "LABEL";
    public static final String MESSAGE_SUCCESS = "File opened! Tag: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "Tag '%s' not found!";
    public static final String MESSAGE_LABEL_NOT_FOUND = "No tag found with the label %s!";
    public static final String MESSAGE_ERROR = "Error opening %s: ";
    public static final String MESSAGE_FILE_NOT_FOUND = "The file: %s doesn't exist.";
    public static final String MESSAGE_FILE_NO_PERMISSION = "You have no permission to open %s.";
    public static final String MESSAGE_ENV_NOT_DESKTOP_SUPPORTED = "Sorry, open command is not supported"
            + "in your platform.";

    private final TagName tagName;
    private final Label label;

    /**
     * Creates an OpenCommand to open the file specified in the {@code Tag}
     */
    public OpenCommand(TagName tagName) {
        assert tagName != null;
        this.tagName = tagName;
        this.label = null;
    }

    /**
     * Creates an OpenCommand to open the file specified by the {@code Label}
     */
    public OpenCommand(Label label) {
        assert label != null;
        this.tagName = null;
        this.label = label;
    }

    /**
     * Executes the command and opens the file specified by tagName.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if tagName cannot be found in model.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert label != null || tagName != null;

        List<Tag> tagsToOpen;

        if (tagName != null) {
            // Open one tag
            Tag tag = getTag(model, tagName);
            tagsToOpen = List.of(tag);
        } else {
            // Open potentially many tags based on label
            tagsToOpen = getTagList(model, label);
        }

        // Check if environment is desktop supported before opening files
        if (!Desktop.isDesktopSupported()) {
            throw new CommandException(MESSAGE_ENV_NOT_DESKTOP_SUPPORTED);
        }

        // Perform open and get a set of opened tags
        Map<Tag, String> openResults = openTags(tagsToOpen);

        // Format result string
        String resultMessage = getFormattedResultString(tagsToOpen, openResults);

        // Throw exception if some tags were not opened
        if (checkError(openResults, tagsToOpen)) {
            throw new CommandException(resultMessage);
        }

        return new CommandResult(resultMessage);
    }

    private String getFormattedResultString(List<Tag> tagsToOpen, Map<Tag, String> openResults) {
        StringBuilder sb = new StringBuilder();
        for (Tag tag : tagsToOpen) {
            String message = openResults.get(tag);
            sb.append(message).append("\n");
        }

        String resultMessage = sb.toString();
        // Remove last "\n"
        if (resultMessage.endsWith("\n")) {
            resultMessage = resultMessage.substring(0, resultMessage.length() - 1);
        }
        return resultMessage;
    }

    private boolean checkError(Map<Tag, String> results, List<Tag> tagList) {
        return tagList.stream().anyMatch((tag) -> results.get(tag).startsWith("Error"));
    }

    private Tag getTag(Model model, TagName tagName) throws CommandException {
        List<Tag> tagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(tagName));
        if (tagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND, tagName.toString()));
        }
        assert tagList.size() == 1 : "Only one tag should be found.";
        return tagList.get(0);
    }

    private List<Tag> getTagList(Model model, Label label) throws CommandException {
        List<Tag> tagList = model.findFilteredTagList(new TagLabelEqualsKeywordPredicate(label));
        if (tagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_LABEL_NOT_FOUND, label.toString()));
        }
        return tagList;
    }

    /**
     * Opens all tags from a list and returns a map of runtime messages.
     */
    private Map<Tag, String> openTags(List<Tag> tags) {
        Map<Tag, String> result = new HashMap<>();

        // Open every tags in the list and store error messages
        tags.forEach(tag -> {
            File file = new File(tag.getFileAddress().value);
            if (!file.exists()) {
                // File does not exist
                result.put(tag, String.format(MESSAGE_ERROR + MESSAGE_FILE_NOT_FOUND,
                        tag.getTagName(), tag.getFileAddress()));
            } else if (!file.canRead()) {
                // No read permission
                result.put(tag, String.format(MESSAGE_ERROR + MESSAGE_FILE_NO_PERMISSION,
                        tag.getTagName(), tag.getFileAddress()));
            } else {
                // Use concurrent threads here to avoid JavaFX freeze in Linux
                new Thread(() -> {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                result.put(tag, String.format(MESSAGE_SUCCESS, tag));
            }
        });

        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other == null) {
            return false;
        } else if (!(other instanceof OpenCommand)) {
            return false;
        }
        OpenCommand o = (OpenCommand) other;

        if (this.tagName != null && o.tagName != null) {
            return this.tagName.equals(o.tagName);
        } else if (this.label != null && o.label != null) {
            return this.label.equals(o.label);
        }

        return false;
    }
}
