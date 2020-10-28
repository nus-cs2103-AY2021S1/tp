package seedu.address.logic.commands;

import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_MESSAGE;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;

public class LoadPresetCommand extends PresetCommand {

    private final Name presetName;
    private final boolean displayAllPresets;

    /**
     * Creates a LoadPresetCommand to load the specified preset {@code Name}
     * @param presetName
     */
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
            List<List<Preset>> allLists = storage.readPresetManager()
                    .orElseThrow(() -> new CommandException(FILE_OPS_ERROR_MESSAGE));

            int currentIndex = model.getVendorIndex();
            if (currentIndex >= allLists.size()) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE);
            }
            if (displayAllPresets) {
                List<Preset> vendorPresets = allLists.get(currentIndex);
                if (vendorPresets.isEmpty()) {
                    throw new CommandException(ParserUtil.MESSAGE_PRESET_NO_SAVED_PRESETS);
                }
                StringBuilder message = new StringBuilder();
                for (Preset preset:vendorPresets) {
                    message.append(preset.getName()).append(", ");
                }
                String removeComma = message.toString().trim();
                removeComma = removeComma.substring(0, removeComma.length() - 1);
                return new CommandResult(PresetCommand.MESSAGE_DISPLAY_ALL_PRESETS + removeComma,
                        false, false, true);
            }
            List<OrderItem> orderItems = allLists.get(currentIndex)
                    .stream()
                    .filter(preset -> preset.getName().equals(presetName.toString()))
                    .findFirst()
                    .map(Preset::getOrderItems)
                    .orElseThrow(() -> new CommandException(String.format(Messages.MESSAGE_PRESET_NOT_FOUND,
                            presetName)));

            model.showDefaultMenu();
            model.setOrder(orderItems);

        } catch (DataConversionException | IOException | IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_PRESET_LOAD_ERROR);
        }
        return new CommandResult(String.format(Messages.MESSAGE_PRESET_LOAD_SUCCESS, presetName),
                false, false, true);
    }


}
