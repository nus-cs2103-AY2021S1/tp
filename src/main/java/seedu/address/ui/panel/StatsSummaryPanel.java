package seedu.address.ui.panel;

import java.util.HashMap;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.StatisticsData;
import seedu.address.model.tag.Tag;
import seedu.address.ui.UiPart;
import seedu.address.ui.card.StatsSummaryCard;

public class StatsSummaryPanel extends UiPart<Region> {
    private static final String FXML = "panel/StatsSummaryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StatsSummaryPanel.class);

    private StatisticsData dataSet;
    private HashMap<String, String> tagColors;

    @FXML
    private ListView<Tag> tagListView;

    /**
     * Create a StatsSummaryPanel to hold stats summary
     * @param dataSet the statistic data
     */
    public StatsSummaryPanel(StatisticsData dataSet, HashMap<String, String> tagColors) {
        super(FXML);
        this.dataSet = dataSet;
        this.tagColors = tagColors;
        ObservableList<Tag> tagList = FXCollections.observableArrayList(dataSet.getTags());
        tagListView.setItems(tagList);
        tagListView.setCellFactory(listView -> new TagListViewCell());
    }

    class TagListViewCell extends ListCell<Tag> {
        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StatsSummaryCard(tag, dataSet, tagColors.get(tag.tagName)).getRoot());
            }
        }
    }
}
