package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;
import seedu.address.model.vendor.Name;
import seedu.address.storage.JsonSerializablePresetManager;
import seedu.address.testutil.StorageManagerBuilder;
import seedu.address.testutil.TypicalModel;

public class SavePresetCommandTest {

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        assertCommandFailure(new SavePresetCommand(Optional.empty()),
                model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    @Test
    public void execute_noOrderToSave_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        assertCommandFailure(new SavePresetCommand(Optional.empty()),
                model, Messages.MESSAGE_PRESET_SAVE_NO_ORDER);
    }

    @Test
    public void execute_validName_success() throws CommandException, IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(0), 2));

        Name presetName = new Name("MyTestPreset");
        SavePresetCommand savePresetCommand = new SavePresetCommand(Optional.of(presetName));

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(0), 2));
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_SAVE_SUCCESS, presetName);

        assertCommandSuccess(savePresetCommand, model,
                StorageManagerBuilder
                        .buildForPreset(Paths.get("src", "test", "data",
                                "SavePresetCommandTest", "validName.json"), true),
                expectedMessage, expectedModel);
    }

    @Test
    public void execute_noName_success() throws CommandException, IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(0), 2));

        SavePresetCommand savePresetCommand = new SavePresetCommand(Optional.empty());

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(0), 2));
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_SAVE_SUCCESS, "MyPreset");

        assertCommandSuccess(savePresetCommand, model,
                StorageManagerBuilder
                        .buildForPreset(Paths.get("src", "test", "data",
                                "SavePresetCommandTest", "noName.json"), true),
                expectedMessage, expectedModel);
    }

    @Test
    public void execute_overwrite_success() throws CommandException, IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        OrderItem firstItemToAdd = new OrderItem(model.getFilteredMenuItemList().get(0), 2);
        model.addOrderItem(firstItemToAdd);

        SavePresetCommand savePresetCommand = new SavePresetCommand(Optional.empty());
        Path presetPath = Paths.get("src", "test", "data", "SavePresetCommandTest", "overwrite.json");
        FileUtil.createIfMissing(presetPath);
        List<Preset> originalSavedPresets = new ArrayList<>();
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(firstItemToAdd);
        originalSavedPresets.add(new Preset("MyPreset", orderItemList));
        List<List<Preset>> allPresets = new ArrayList<>();
        allPresets.add(originalSavedPresets);
        JsonUtil.saveJsonFile(new JsonSerializablePresetManager(allPresets, true), presetPath);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        expectedModel.addOrderItem(firstItemToAdd);
        String expectedMessage = String.format(Messages.MESSAGE_PRESET_OVERWRITE_SUCCESS, "MyPreset");

        assertCommandSuccess(savePresetCommand, model,
                StorageManagerBuilder.buildForPreset(presetPath, false), expectedMessage, expectedModel);
    }
}
