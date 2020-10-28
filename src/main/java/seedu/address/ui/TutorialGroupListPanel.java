package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Panel containing the list of tutorial groups.
 */
public class TutorialGroupListPanel extends UiPart<Region> {
    private static final String FXML = "TutorialGroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorialGroupListPanel.class);

    @FXML
    private ListView<TutorialGroup> tutorialGroupListView;

    /**
     * Creates a {@code TutorialGroupListPanel} with the given {@code ObservableList}.
     */
    public TutorialGroupListPanel(ObservableList<TutorialGroup> tutorialGroupList) {
        super(FXML);
        tutorialGroupListView.setItems(tutorialGroupList);
        tutorialGroupListView.setCellFactory(listView -> new TutorialGroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code TutorialGroup} using a {@code TutorialGroupCard}.
     */
    class TutorialGroupListViewCell extends ListCell<TutorialGroup> {
        @Override
        protected void updateItem(TutorialGroup tutorialGroup, boolean empty) {
            super.updateItem(tutorialGroup, empty);

            if (empty || tutorialGroup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TutorialGroupCard(tutorialGroup, getIndex() + 1).getRoot());
            }
        }
    }

}
