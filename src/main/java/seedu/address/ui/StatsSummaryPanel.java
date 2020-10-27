package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.StatisticsData;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.ui.card.StatsSummaryCard;

import java.util.logging.Logger;

public class StatsSummaryPanel extends UiPart<Region> {
    private static final String FXML = "StatsSummaryPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StatsSummaryPanel.class);

    private StatisticsData dataSet;

    @FXML
    private ListView<Tag> tagListView;

    public StatsSummaryPanel(ObservableList<Lesson> lessonList, StatisticsData dataSet) {
        super(FXML);
        this.dataSet = dataSet;
        ObservableList<Tag> tagList = extractTags(lessonList);
        tagListView.setItems(tagList);
        tagListView.setCellFactory(listView -> new TagListViewCell());
    }

    private ObservableList<Tag> extractTags(ObservableList<Lesson> lessonList) {
        ObservableList<Tag> tagList = FXCollections.observableArrayList();
        for (Lesson lesson : lessonList) {
            Tag tag = lesson.getTag();
            tagList.add(tag);
        }
        return tagList;
    }

    class TagListViewCell extends ListCell<Tag> {
        @Override
        protected void updateItem(Tag tag, boolean empty) {
            super.updateItem(tag, empty);

            if (empty || tag == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StatsSummaryCard(tag, dataSet).getRoot());
            }
        }
    }
}
