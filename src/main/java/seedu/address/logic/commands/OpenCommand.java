package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Opens the file specified in the filepath of a tag. "
            + "\n\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + "\n\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 ";
    public static final String OPEN_COMMAND_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>";
    public static final String MESSAGE_SUCCESS = "File opened! Tag: %1$s";
    public static final String MESSAGE_TAG_NOT_FOUND = "Tag '%s' not found!";
    public static final String MESSAGE_FILE_NOT_FOUND = "The file: %s doesn't exist.";

    private final TagName tagName;

    /**
     * Creates an OpenCommand to open the file specified in the {@code Tag}
     */
    public OpenCommand(TagName tagName) {
        this.tagName = tagName;
    }

    /**
     * Executes the command and opens the file specified by tagName.
     *
     * @param model {@code Model} which the command should operate on.
     * @throws CommandException if tagName cannot be found in model.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Check if tagName is in tag list
        List<Tag> tagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(tagName));
        if (tagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND, tagName.toString()));
        }

        // Get tag and prepare file to be opened
        Tag tagToOpen = tagList.get(0);
        File file = new File(tagToOpen.getFileAddress().value);

        try {
            // Open file using java.awt.Desktop
            Desktop.getDesktop().open(file);
        } catch (IOException | IllegalArgumentException e) {
            throw new CommandException(e.getMessage(), e);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagToOpen.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OpenCommand // instanceof handles nulls
                && tagName.equals(((OpenCommand) other).tagName));
    }
}
