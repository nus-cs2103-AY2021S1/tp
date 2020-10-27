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

import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_MESSAGE;

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

        try {
            List<List<Preset>> allLists = storage.readPresetManager()
                    .orElseThrow(() -> new CommandException(FILE_OPS_ERROR_MESSAGE));
            //TODO: UPDATE ERROR THROW
            int currentIndex = model.getVendorIndex();
            if (currentIndex >= allLists.size()) {
                throw new CommandException("dab");
            }
            List<Preset> currentVendorPresets = allLists.get(model.getVendorIndex());
            Optional<Preset> preset = currentVendorPresets.stream()
                    .filter(x -> x.getName().equals(presetName.toString()))
                    .findFirst();
            preset.ifPresent(x -> model.setOrder(x.getOrderItems()));

            storage.savePresetManager(model.getOrderManager(), presetName.toString(), model.getVendorIndex());
        } catch (IOException | DataConversionException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
        return new CommandResult(Messages.MESSAGE_PRESET_SAVE_SUCCESS, false, false, true);

    }
}
