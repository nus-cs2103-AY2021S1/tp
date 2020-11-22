package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.person.Block;
import seedu.address.storage.BlockSetting;

/**
 * Panel containing the reference to blocks present.
 */
public class ReferenceSection extends UiPart<Region> {
    private static final String FXML = "ReferenceSection.fxml";

    @FXML
    private ListView<BlockSetting> blockListView;

    /**
     * Creates a {@code ReferenceSection}.
     */
    public ReferenceSection() {
        super(FXML);

        ObservableList<BlockSetting> blocksReference = FXCollections.observableArrayList();
        List<BlockSetting> blocks = new ArrayList<>();

        List<String> availableBlocks = Block.getBlockList();
        for (String block : availableBlocks) {
            blocks.add(new BlockSetting(block));
        }
        blocksReference.addAll(blocks);

        blockListView.setItems(blocksReference);
        blockListView.setCellFactory(listView -> new BlockCardCell());
    }

    class BlockCardCell extends ListCell<BlockSetting> {
        /**
         * Overrides the default listCell view.
         * @param item object to create
         * @param empty boolean flag to check if the item is just a placeholder
         * @see PersonListPanel
         */
        @Override
        protected void updateItem(BlockSetting item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BlockSettingCard(item).getRoot());
            }
        }
    }
}
