package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.preset.Preset;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class LoadPresetCommand extends PresetCommand {

    private final Name presetName;
    private final boolean displayAllPresets;

    public LoadPresetCommand(Optional<Name> presetName) {
        this.displayAllPresets = presetName.isEmpty();
        this.presetName = presetName.orElseGet(() -> new Name("Invalid"));
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {

        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }
        try {
            Optional<List<Preset>> lists = storage.readPresetManager();
            lists.ifPresent(list -> model.setOrder(list.get(model.getVendorIndex()).getOrderItems()));
        } catch (DataConversionException | IOException | IndexOutOfBoundsException e) {
            throw new CommandException("Presets cannot be read.");
        }
        return new CommandResult(Messages.MESSAGE_PRESET_LOAD_SUCCESS, false, false, true);
    }


}
