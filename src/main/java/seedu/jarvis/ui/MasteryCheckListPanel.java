package seedu.jarvis.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.jarvis.commons.core.LogsCenter;
import seedu.jarvis.model.masterycheck.MasteryCheck;

public class MasteryCheckListPanel extends UiPart<Region> {

    private static final String FXML = "MasteryCheckListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(MasteryCheckListPanel.class);

    @javafx.fxml.FXML
    private ListView<MasteryCheck> masteryCheckListView;

    /**
     * Creates a {@code ConsultationListPanel} with the given {@code ObservableList}.
     */
    public MasteryCheckListPanel(ObservableList<MasteryCheck> masteryCheckList) {
        super(FXML);

        Label emptyListLabel = new Label("No Mastery Checks");
        emptyListLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20");
        masteryCheckListView.setPlaceholder(emptyListLabel);
        masteryCheckListView.setItems(masteryCheckList);
        masteryCheckListView.setCellFactory(listView -> new MasteryCheckListPanel.MasteryCheckListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Consultation} using a {@code ConsultationCard}.
     */
    class MasteryCheckListViewCell extends ListCell<MasteryCheck> {
        @Override
        protected void updateItem(MasteryCheck masteryCheck, boolean empty) {
            super.updateItem(masteryCheck, empty);

            if (empty || masteryCheck == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MasteryCheckCard(masteryCheck, getIndex() + 1).getRoot());
            }
        }
    }

}
