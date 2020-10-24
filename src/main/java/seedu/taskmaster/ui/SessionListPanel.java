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


import java.util.logging.Logger;

/**
 * Panel containing the list of sessions.
 */
public class SessionListPanel extends UiPart<Region> {
    private static final String FXML = "SessionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @FXML
    private ListView<SessionStub> sessionListView; //String is the type of objects, if SRL use SRL

    public SessionListPanel(ObservableList<SessionStub> sessionList) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListViewCell());
    }

    /**
     *
     */
    class SessionListViewCell extends ListCell<SessionStub> { //change the stub to session
        final Button button = new Button();
        @Override
        protected void updateItem(SessionStub session, boolean empty) {
            super.updateItem(session, empty);
            if (empty || session == null) {
                setGraphic(null);
                setText(null);
             } else {
                button.setText(session.name);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //will implement later
                    }
                });
                setGraphic(button);
            }
        }
    }

    class SessionStub {
        private final String name;
        private final StudentRecordList srList;
        SessionStub(String name, StudentRecordList srList) {
            this.name = name;
            this.srList = srList;
        }
    }

}
