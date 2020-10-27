package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;

import java.util.Optional;

public class LoadPresetCommand extends PresetCommand {

    private final Name presetName;
    private final boolean displayAllPresets;

    public LoadPresetCommand(Optional<Name> presetName) {
        this.displayAllPresets = presetName.isEmpty();
        this.presetName = presetName.orElseGet(() -> new Name("Invalid"));
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        return null;
    }
}
