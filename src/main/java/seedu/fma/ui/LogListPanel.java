package seedu.fma.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.fma.commons.core.LogsCenter;
import seedu.fma.model.log.Log;

/**
 * Panel containing the list of logs.
 */
public class LogListPanel extends UiPart<Region> {
    private static final String FXML = "LogListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LogListPanel.class);

    @FXML
    private ListView<Log> logListView;

    /**
     * Creates a {@code LogListPanel} with the given {@code ObservableList}.
     */
    public LogListPanel(ObservableList<Log> logList) {
        super(FXML);
        logListView.setItems(logList);
        logListView.setCellFactory(listView -> new LogListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Log} using a {@code LogCard}.
     */
    class LogListViewCell extends ListCell<Log> {
        @Override
        protected void updateItem(Log log, boolean empty) {
            super.updateItem(log, empty);

            if (empty || log == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LogCard(log, getIndex() + 1).getRoot());
            }
        }
    }

}
