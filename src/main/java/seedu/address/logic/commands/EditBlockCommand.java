package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;

/**
 * Edits the range of block letter.
 */
public class EditBlockCommand extends Command {

    public static final String COMMAND_WORD = "edit-block-range";

    public static final String COMMAND_NOTES = "Notes: Please restart the application for the block "
        + "settings changes to take effect. This command will erase the existing data "
        + "if the resident's block letter is no longer valid after updating the block settings.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the range of block letter "
        + "by specifying the first and last block letter.\n"
        + COMMAND_NOTES + "\n"
        + "Parameters: FIRST_BLOCK_LETTER LAST_BLOCK_LETTER (must be a capital letter)\n"
        + "Example: " + COMMAND_WORD + " A D";

    public static final String MESSAGE_EDIT_BLOCK_SUCCESS = "Edited Block Letter Range: %s to %s\n" + COMMAND_NOTES;
    public static final String MESSAGE_ALLOWED_BLOCK_RANGE = "Allowed Block Letter: %s to %s "
        + "(must be a capital letter)";
    public static final String MESSAGE_INVALID_BLOCK_RANGE = "The first block letter cannot be after "
        + "the last block letter";
    public static final String MESSAGE_INVALID_FIRST_BLOCK_LETTER = "The provided first block letter is invalid."
        + "\n" + MESSAGE_ALLOWED_BLOCK_RANGE;
    public static final String MESSAGE_INVALID_LAST_BLOCK_LETTER = "The provided last block letter is invalid."
        + "\n" + MESSAGE_ALLOWED_BLOCK_RANGE;

    private final char firstBlockLetter;
    private final char lastBlockLetter;

    /**
     * @param firstBlockLetter first block letter
     * @param lastBlockLetter last block letter
     */
    public EditBlockCommand(char firstBlockLetter, char lastBlockLetter) {
        requireAllNonNull(firstBlockLetter, lastBlockLetter);
        this.firstBlockLetter = firstBlockLetter;
        this.lastBlockLetter = lastBlockLetter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // do validation checks for firstBlockLetter and lastBlockLetter
        if (firstBlockLetter < UserPrefs.MIN_ALLOWED_BLOCK_LETTER
            || firstBlockLetter > UserPrefs.MAX_ALLOWED_BLOCK_LETTER) {
            throw new CommandException(String.format(MESSAGE_INVALID_FIRST_BLOCK_LETTER,
                UserPrefs.MIN_ALLOWED_BLOCK_LETTER, UserPrefs.MAX_ALLOWED_BLOCK_LETTER));
        }
        if (lastBlockLetter < UserPrefs.MIN_ALLOWED_BLOCK_LETTER
            || lastBlockLetter > UserPrefs.MAX_ALLOWED_BLOCK_LETTER) {
            throw new CommandException(String.format(MESSAGE_INVALID_LAST_BLOCK_LETTER,
                UserPrefs.MIN_ALLOWED_BLOCK_LETTER, UserPrefs.MAX_ALLOWED_BLOCK_LETTER));
        }
        if (firstBlockLetter > lastBlockLetter) {
            throw new CommandException(MESSAGE_INVALID_BLOCK_RANGE);
        }

        // build available block range based on the first and last block letter
        int size = lastBlockLetter - firstBlockLetter + 1;
        char k = firstBlockLetter;
        String[] blockSettings = new String[size];
        for (int i = 0; i < size; i++) {
            blockSettings[i] = String.valueOf(k);
            k++;
        }

        UserPrefs userPrefs = model.getModifiableUserPrefs();
        userPrefs.setBlockSettings(blockSettings);

        return new CommandResult(String.format(MESSAGE_EDIT_BLOCK_SUCCESS, firstBlockLetter, lastBlockLetter));
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
            || (obj instanceof EditBlockCommand)
            && firstBlockLetter == (((EditBlockCommand) obj).firstBlockLetter)
            && lastBlockLetter == (((EditBlockCommand) obj).lastBlockLetter);
    }
}
