package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.io.File;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag to the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the HelleFile's address book.\n"
            + "Parameters: "
            + PREFIX_TAG_NAME + "TAG_NAME "
            + PREFIX_FILE_ADDRESS + "FILE_ADDRESS\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TAG_NAME + "cs2103 "
            + PREFIX_FILE_ADDRESS + "F:\\OneDrive\\CS2013T ";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found at %s!"
            + "Please make sure that file is present before adding.";
    public static final String MESSAGE_DUPLICATE_TAG = "Duplicate tag name!";

    private final Tag toTag;

    /**
     * Creates an AddCommand to add the specified {@code Tag}
     */
    public TagCommand(Tag tag) {
        requireNonNull(tag);
        // Check if file is present
        checkArgument(filePresent(tag.getFileAddress()),
                String.format(MESSAGE_FILE_NOT_FOUND, tag.getFileAddress().value));
        toTag = tag;
    }

    private boolean filePresent(FileAddress address) {
        File file = new File(address.value);
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

        model.addTag(toTag);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toTag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagCommand // instanceof handles nulls
                && toTag.equals(((TagCommand) other).toTag));
    }
}
