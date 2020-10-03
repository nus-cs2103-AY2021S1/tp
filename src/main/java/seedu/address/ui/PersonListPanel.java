package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.log.Log;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Log> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Log> logList) {
        super(FXML);
        personListView.setItems(logList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Log} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Log> {
        @Override
        protected void updateItem(Log log, boolean empty) {
            super.updateItem(log, empty);

            if (empty || log == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(log, getIndex() + 1).getRoot());
            }
        }
    }

}
