package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.quiz.Attempt;

/**
 * Panel containing the list of persons.
 */
public class AttemptListPanel extends UiPart<Region> {
    private static final String FXML = "AttemptListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AttemptListPanel.class);

    @FXML
    private ListView<Attempt> attemptListView;

    /**
     * Creates a {@code AttemptListPanel} with the given {@code ObservableList}.
     */
    public AttemptListPanel(ObservableList<Attempt> flashcardList) {
        super(FXML);
        attemptListView.setItems(flashcardList);
        attemptListView.setCellFactory(listView -> new AttemptListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Attempt} using a {@code AttemptCard}.
     */
    class AttemptListViewCell extends ListCell<Attempt> {
        @Override
        protected void updateItem(Attempt flashcard, boolean empty) {
            super.updateItem(flashcard, empty);

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AttemptCard(flashcard, getIndex() + 1).getRoot());
            }
        }
    }

}
