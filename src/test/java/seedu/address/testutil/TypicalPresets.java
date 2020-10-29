package seedu.address.testutil;

import seedu.address.model.order.OrderItem;
import seedu.address.model.preset.Preset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.testutil.TypicalOrderItems.*;

public class TypicalPresets {
    public static final Preset PRESET = new PresetBuilder().withName("Preset 1")
            .withOrderItems(PresetBuilder.TYPICAL_ORDER_ITEMS).build();
    public static final List<OrderItem> orderItems2 = new ArrayList<>(Arrays.asList(PRATA, MILO, NASI_GORENG));
    public static final Preset PRESET_2 = new PresetBuilder().withName("Preset 2")
            .withOrderItems(orderItems2).build();
}
