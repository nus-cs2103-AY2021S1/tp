package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;
import seedu.address.testutil.StorageManagerBuilder;
import seedu.address.testutil.TypicalModel;

public class DeleteCommandTest {

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        assertCommandFailure(new DeletePresetCommand(Optional.of(new Name("Invalid name"))),
                model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    @Test
    public void execute_validName_success() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = StorageManagerBuilder
                .buildForPreset(Paths.get("src", "test", "data",
                        "DeletePresetCommandTest", "validName.json"), true);
        Name presetName = new Name("MyTestPreset");
        List<Preset> currentPreset = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(model.getFilteredMenuItemList().get(1), 2));
        currentPreset.add(new Preset(presetName.toString(), orderItems));
        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(currentPreset);
        storage.savePresetManager(allPresets);

        DeletePresetCommand deletePresetCommand = new DeletePresetCommand(Optional.of(presetName));

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_DELETE_SUCCESS, presetName);

        assertCommandSuccess(deletePresetCommand, model, storage, expectedMessage, expectedModel);
    }

    @Test
    public void execute_presetNotFound_throwsException() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = StorageManagerBuilder
                .buildForPreset(Paths.get("src", "test", "data",
                        "DeletePresetCommandTest", "presetNotFound.json"), true);
        Name searchForName = new Name("Invalid Preset");
        DeletePresetCommand deletePresetCommand = new DeletePresetCommand(Optional.of(searchForName));
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_NOT_FOUND, searchForName);
        assertCommandFailure(deletePresetCommand, model, storage, expectedMessage);


        List<Preset> currentPreset = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(model.getFilteredMenuItemList().get(1), 2));
        currentPreset.add(new Preset("MyTestPreset", orderItems));
        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(currentPreset);
        storage.savePresetManager(allPresets);

        assertCommandFailure(deletePresetCommand, model, storage, expectedMessage);
    }
}
