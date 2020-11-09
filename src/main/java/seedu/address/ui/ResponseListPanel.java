package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.quiz.Response;


/**
 * Panel containing the list of persons.
 */
public class ResponseListPanel extends UiPart<Region> {
    private static final String FXML = "ResponseListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ResponseListPanel.class);

    @FXML
    private ListView<Response> responseListView;

    /**
     * Creates a {@code ResponseListPanel} with the given {@code ObservableList}.
     */
    public ResponseListPanel(ObservableList<Response> responseList) {
        super(FXML);
        responseListView.setItems(responseList);
        responseListView.setCellFactory(listView -> new ResponseListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code PersonCard}.
     */
    class ResponseListViewCell extends ListCell<Response> {
        @Override
        protected void updateItem(Response question, boolean empty) {
            super.updateItem(question, empty);

            if (empty || question == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ResponseCard(question, getIndex() + 1).getRoot());
            }
        }
    }

}
