package seedu.jarvis.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.model.quest.Quest;



/**
 * Panel containing the list of quests.
 */
public class QuestListPanel extends UiPart<Region> {
    private static final String FXML = "QuestListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(QuestListPanel.class);

    @javafx.fxml.FXML
    private ListView<Quest> questListView;

    /**
     * Creates a {@code QuestListPanel} with the given {@code ObservableList}.
     */
    public QuestListPanel(ObservableList<Quest> questList) {
        super(FXML);

        Label emptyListLabel = new Label("No Quests");
        emptyListLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        questListView.setPlaceholder(emptyListLabel);
        questListView.setItems(questList);
        questListView.setCellFactory(listView -> new QuestListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Quest} using a {@code QuestCard}.
     */
    class QuestListViewCell extends ListCell<Quest> {
        @Override
        protected void updateItem(Quest quest, boolean empty) {
            super.updateItem(quest, empty);

            if (empty || quest == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new QuestCard(quest, getIndex() + 1).getRoot());
            }
        }
    }

}
