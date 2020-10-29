package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.io.File;
import java.nio.file.Paths;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag to the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the HelloFile's address book. "
            + "\n\nParameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + PREFIX_FILE_ADDRESS + "FILE_ADDRESS "
            + "[" + PREFIX_LABEL_NAME + "LABEL_NAME" + "]...\n"
            + "\n\nExample: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 "
            + PREFIX_FILE_ADDRESS + "F:\\OneDrive\\CS2013T "
            + PREFIX_LABEL_NAME + "CS2103T";
    public static final String TAG_COMMAND_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>"
            + " " + PREFIX_FILE_ADDRESS + "<FILEPATH>" + " " + PREFIX_LABEL_NAME + "<LABEL>";
    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found at %s!"
            + " Please make sure that file is present before adding a tag.";
    public static final String MESSAGE_DUPLICATE_TAG = "Duplicate tag name!";

    private final Tag toTag;

    /**
     * Creates an AddCommand to add the specified {@code Tag}
     */
    public TagCommand(Tag tag) {
        requireNonNull(tag);
        toTag = tag;
    }

    private boolean filePresent(String address) {
        assert address != null;
        File file = new File(address);
        return file.exists();
    }

    /**
     * Executes the command and add the tag to model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A Command result of executing tag command.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTag(toTag)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        // Check if file is present
        String path;
        boolean isAbsolutePath = Paths.get(toTag.getFileAddress().value).isAbsolute();

        path = toTag.getFileAddress().value;

        if (!isAbsolutePath) {
            path = Paths.get(model.getCurrentPath().getAddress().value, toTag.getFileAddress().value)
                    .normalize().toString();
        }

        if (!filePresent(path)) {
            throw new CommandException(
                    String.format(MESSAGE_FILE_NOT_FOUND, path));
        }

        Tag absPathTag = toTag.toAbsolute(isAbsolutePath, model.getCurrentPath().getAddress());

        model.addTag(absPathTag);

        // Save commit for undo
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_SUCCESS, absPathTag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && toTag.equals(((TagCommand) other).toTag));
    }
}
