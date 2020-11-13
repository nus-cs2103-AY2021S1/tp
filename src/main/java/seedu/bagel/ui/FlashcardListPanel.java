package seedu.bagel.ui;

import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * Panel containing the list of flashcards.
 */
public class FlashcardListPanel extends UiPart<Region> {
    private static final String FXML = "FlashcardListPanel.fxml";
    private static boolean isSingle;
    private final Logger logger = LogsCenter.getLogger(FlashcardListPanel.class);
    private HostServices hostServices;

    @FXML
    private ListView<Flashcard> flashcardListView;

    /**
     * Creates a {@code FlashcardListPanel} with the given {@code ObservableList}.
     */
    public FlashcardListPanel(ObservableList<Flashcard> flashcardList, HostServices hostServices) {
        super(FXML);
        if (flashcardList.size() == 1) {
            this.isSingle = true;
        } else {
            this.isSingle = false;
        }
        flashcardListView.setItems(flashcardList);
        flashcardListView.setCellFactory(listView -> new FlashcardListViewCell());
        this.hostServices = hostServices;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Flashcard} using a {@code FlashcardCard}.
     */
    class FlashcardListViewCell extends ListCell<Flashcard> {
        @Override
        protected void updateItem(Flashcard flashcard, boolean empty) {
            super.updateItem(flashcard, empty);
            // check if there is single or multiple flashcards
            if (flashcardListView.getItems().size() == 1) {
                FlashcardListPanel.isSingle = true;
            } else {
                FlashcardListPanel.isSingle = false;
            }

            if (empty || flashcard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashcardCard(flashcard, getIndex() + 1, hostServices,
                        FlashcardListPanel.isSingle).getRoot());
            }
        }
    }

}
