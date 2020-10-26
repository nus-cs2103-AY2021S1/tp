package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class PresetCommand extends Command {

    public static final String COMMAND_WORD = "preset";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": saves the current order to a preset.";

    public PresetCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        return new CommandResult(Messages.MESSAGE_PRESET_COMMAND_SUCCESS, false, false, false);
    }
}
