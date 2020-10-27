package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.LogicManager.FILE_OPS_ERROR_MESSAGE;

public class PresetCommand extends Command {

    public static final String COMMAND_WORD = "preset";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": saves the current order to a preset.";

    private final boolean save;
    private final Name presetName;

    public PresetCommand(boolean save, Name presetName) {
        this.save = save;
        this.presetName = presetName;
    }

    @Override
    public CommandResult execute(Model model, Storage storage) throws CommandException {
        requireNonNull(model);
        if (!model.isSelected()) {
            throw new CommandException(ParserUtil.MESSAGE_VENDOR_NOT_SELECTED);
        }

        if (this.save) {
            try {
                //TODO: Change to index
                storage.saveOrderManager(model.getOrderManager(),model.getVendorIndex());
            } catch (IOException | DataConversionException ioe) {
                throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
            }
            return new CommandResult(Messages.MESSAGE_PRESET_SAVE_SUCCESS, false, false, true);
        } else {
            try {
                Optional<List<List<OrderItem>>> lists = storage.readOrderManager();
                lists.ifPresent(list -> model.setOrder(list.get(model.getVendorIndex())));
            } catch (DataConversionException | IOException | IndexOutOfBoundsException e) {
                throw new CommandException("Presets cannot be read.");
            }
            return new CommandResult(Messages.MESSAGE_PRESET_LOAD_SUCCESS, false, false, true);
        }

    }
}
