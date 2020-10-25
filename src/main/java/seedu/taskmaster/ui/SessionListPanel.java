package seedu.taskmaster.ui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.taskmaster.commons.core.LogsCenter;
import seedu.taskmaster.model.session.StudentRecordList;


import java.util.function.IntConsumer;
import java.util.logging.Logger;

/**
 * Panel containing the list of sessions.
 */
public class SessionListPanel extends UiPart<Region> {
    private static final String FXML = "SessionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);
    private Runnable studentDisplay;

    @FXML
    private ListView<SessionStub> sessionListView;

    public SessionListPanel(ObservableList<SessionStub> sessionList, IntConsumer listDisplay, Runnable studentDisplay) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell(listDisplay));
        this.studentDisplay = studentDisplay;
    }

    @FXML
    private void handleStudent() {
        studentDisplay.run();
    }

    /**
     *
     */
    class SessionListViewCell extends ListCell<SessionStub> { //change the stub to session
        IntConsumer listDisplay;
        final Button button = new Button();
        SessionListViewCell(IntConsumer listDisplay) {
            this.listDisplay = listDisplay;
        }
        @Override
        protected void updateItem(SessionStub session, boolean empty) {
            super.updateItem(session, empty);
            if (empty || session == null) {
                setGraphic(null);
                setText(null);
             } else {
                button.setText(session.getName());
                button.setMinSize(130, 30);
                button.setMaxSize(130, 30);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        listDisplay.accept(getIndex() + 1);
                    }
                });
                setGraphic(button);
            }
        }
    }

}
