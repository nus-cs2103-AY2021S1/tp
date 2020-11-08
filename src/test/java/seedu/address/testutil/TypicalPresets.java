package seedu.address.testutil;

import static seedu.address.testutil.TypicalOrderItems.MILO;
import static seedu.address.testutil.TypicalOrderItems.NASI_GORENG;
import static seedu.address.testutil.TypicalOrderItems.PRATA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;

public class TypicalPresets {
    public static final Preset PRESET = new PresetBuilder().withName("Preset 1")
            .withOrderItems(PresetBuilder.TYPICAL_ORDER_ITEMS).build();
    public static final List<OrderItem> ORDER_ITEMS = new ArrayList<>(Arrays.asList(PRATA, MILO, NASI_GORENG));
    public static final Preset PRESET_2 = new PresetBuilder().withName("Preset 2")
            .withOrderItems(ORDER_ITEMS).build();

    public static List<List<Preset>> getTypicalPresets() {
        List<List<Preset>> presets = new ArrayList<>();
        List<Preset> presets1 = new ArrayList<>();
        presets1.add(PRESET);
        List<Preset> presets2 = new ArrayList<>();
        presets2.add(PRESET_2);
        presets.add(presets1);
        presets.add(presets2);

        return presets;
    }
}
