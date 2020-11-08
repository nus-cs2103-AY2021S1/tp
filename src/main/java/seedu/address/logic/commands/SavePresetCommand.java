package seedu.address.logic.commands;

import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_MESSAGE;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.preset.Preset;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;

public class SavePresetCommand extends PresetCommand {

    private final Name presetName;

    public SavePresetCommand(Optional<Name> presetName) {
        this.presetName = presetName.orElseGet(() -> new Name("MyPreset"));
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        if (!model.isSelected()) {
            throw new CommandException(Messages.MESSAGE_VENDOR_NOT_SELECTED);
        }
        if (model.getOrderSize() == 0) {
            throw new CommandException(Messages.MESSAGE_PRESET_SAVE_NO_ORDER);
        }

        try {
            List<List<Preset>> allLists = storage.readPresetManager()
                    .orElseThrow(() -> new CommandException(FILE_OPS_ERROR_MESSAGE));
            int currentIndex = model.getVendorIndex();
            if (currentIndex >= allLists.size()) {
                IntStream.rangeClosed(allLists.size(), currentIndex)
                        .forEach(x -> allLists.add(new ArrayList<>()));
            }
            // check entire menu???? whether order is valid
            List<Preset> currentVendorPresets = allLists.get(model.getVendorIndex());

            Preset newPreset = new Preset(presetName.toString(),
                    model.getObservableOrderItemList());

            Optional<Preset> preset = currentVendorPresets.stream()
                    .filter(x -> x.getName().equals(presetName.toString()))
                    .findFirst();

            String message = Messages.MESSAGE_PRESET_SAVE_SUCCESS;
            if (preset.isPresent()) {
                currentVendorPresets.remove(preset.get());
                message = Messages.MESSAGE_PRESET_OVERWRITE_SUCCESS;
            }

            currentVendorPresets.add(newPreset);

            storage.savePresetManager(allLists);

            return new CommandResult(String.format(message, presetName), false, false, true, false);
        } catch (IOException | DataConversionException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SavePresetCommand // instanceof handles nulls
                && presetName.equals(((SavePresetCommand) other).presetName));
    }
}
