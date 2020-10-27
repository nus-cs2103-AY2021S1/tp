package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;

import java.util.Optional;

public class SavePresetCommand extends PresetCommand {

    private final Name presetName;

    public SavePresetCommand(Optional<Name> presetName) {
        this.presetName = presetName.orElseGet(() -> new Name("My saved Preset"));
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        return null;
    }
}
