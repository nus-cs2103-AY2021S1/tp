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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;
import seedu.address.model.vendor.Name;
import seedu.address.storage.Storage;
import seedu.address.testutil.StorageManagerBuilder;
import seedu.address.testutil.TypicalModel;

public class LoadPresetCommandTest {

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        assertCommandFailure(new LoadPresetCommand(Optional.empty()),
                model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    @Test
    public void execute_validName_success() throws CommandException, IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Name presetName = new Name("MyTestPreset");
        Storage storage = StorageManagerBuilder
                .buildForPreset(Paths.get("src", "test", "data",
                        "LoadPresetCommandTest", "validName.json"), true);
        List<Preset> currentPreset = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(model.getFilteredMenuItemList().get(1), 2));
        currentPreset.add(new Preset(presetName.toString(), orderItems));
        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(currentPreset);
        storage.savePresetManager(allPresets);
        LoadPresetCommand loadPresetCommand = new LoadPresetCommand(Optional.of(presetName));

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(1), 2));
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_LOAD_SUCCESS, presetName);

        assertCommandSuccess(loadPresetCommand, model, storage, expectedMessage, expectedModel);
    }

    @Test
    public void execute_listAllPresets_success() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = StorageManagerBuilder
                .buildForPreset(Paths.get("src", "test", "data",
                        "LoadPresetCommandTest", "listAllPresets.json"), true);
        List<Preset> currentPreset = new ArrayList<>();

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(model.getFilteredMenuItemList().get(1), 2));
        List<OrderItem> orderItems2 = new ArrayList<>();
        orderItems2.add(new OrderItem(model.getFilteredMenuItemList().get(0), 3));

        currentPreset.add(new Preset("MyTestPreset 1", orderItems));
        currentPreset.add(new Preset("Number 2", orderItems2));
        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(currentPreset);

        storage.savePresetManager(allPresets);
        LoadPresetCommand loadPresetCommand = new LoadPresetCommand(Optional.empty());

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        String expectedMessage = PresetCommand.MESSAGE_DISPLAY_ALL_PRESETS + "MyTestPreset 1, Number 2";

        assertCommandSuccess(loadPresetCommand, model, storage, expectedMessage, expectedModel);
    }

    @Test
    public void execute_presetNotFound_throwsException() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = StorageManagerBuilder
                .buildForPreset(Paths.get("src", "test", "data",
                        "LoadPresetCommandTest", "presetNotFound.json"), true);
        Name searchForName = new Name("Invalid Preset");
        LoadPresetCommand loadPresetCommand = new LoadPresetCommand(Optional.of(searchForName));
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_NOT_FOUND, searchForName);
        assertCommandFailure(loadPresetCommand, model, storage, expectedMessage);


        List<Preset> currentPreset = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(model.getFilteredMenuItemList().get(1), 2));
        currentPreset.add(new Preset("MyTestPreset", orderItems));
        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(currentPreset);
        storage.savePresetManager(allPresets);

        assertCommandFailure(loadPresetCommand, model, storage, expectedMessage);
    }

    @Test
    public void execute_noPresetsToDisplay_throwsException() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = StorageManagerBuilder
                .buildForPreset(Paths.get("src", "test", "data",
                        "LoadPresetCommandTest", "noPresetsToDisplay.json"), true);
        LoadPresetCommand loadPresetCommand = new LoadPresetCommand(Optional.empty());
        String expectedMessage = Messages.MESSAGE_PRESET_NO_SAVED_PRESETS;

        assertCommandFailure(loadPresetCommand, model, storage, expectedMessage);

        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(new ArrayList<>());
        storage.savePresetManager(allPresets);
        assertCommandFailure(loadPresetCommand, model, storage, expectedMessage);
    }
}
