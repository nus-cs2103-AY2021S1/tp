package seedu.jarvis.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.model.mission.Mission;


/**
 * Panel containing the list of missions.
 */
public class MissionListPanel extends UiPart<Region> {
    private static final String FXML = "MissionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MissionListPanel.class);

    @FXML
    private ListView<Mission> missionListView;

    /**
     * Creates a {@code MissionListPanel} with the given {@code ObservableList}.
     */
    public MissionListPanel(ObservableList<Mission> missionList) {
        super(FXML);

        Label emptyListLabel = new Label("No Missions");
        emptyListLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        missionListView.setPlaceholder(emptyListLabel);
        missionListView.setItems(missionList);
        missionListView.setCellFactory(listView -> new MissionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Mission} using a {@code MissionCard}.
     */
    class MissionListViewCell extends ListCell<Mission> {
        @Override
        protected void updateItem(Mission mission, boolean empty) {
            super.updateItem(mission, empty);

            if (empty || mission == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MissionCard(mission, getIndex() + 1).getRoot());
            }
        }
    }

}
