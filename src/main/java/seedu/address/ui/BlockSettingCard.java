package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.address.storage.BlockSetting;

public class BlockSettingCard extends UiPart<Region> {
    private static final String FXML = "BlockSettingCard.fxml";

    private final BlockSetting blockSetting;

    @FXML
    private Label blockName;

    @FXML
    private Label floors;

    @FXML
    private Label rooms;


    /**
     * Creates a {@code BlockSettingCard} with the given {@code BlockSetting}.
     */
    public BlockSettingCard(BlockSetting blockSetting) {
        super(FXML);
        this.blockSetting = blockSetting;

        blockName.setText(blockSetting.getBlockName());
        blockName.setPrefWidth(40);
        blockName.setTextAlignment(TextAlignment.LEFT);
        floors.setText(blockSetting.getFloors());
        rooms.setText(blockSetting.getRooms());

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BlockSettingCard)) {
            return false;
        }

        // state check
        BlockSettingCard card = (BlockSettingCard) other;
        return blockSetting.equals(card.blockSetting);
    }
}
