package seedu.forgetfulnus.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.forgetfulnus.commons.core.LogsCenter;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Panel containing the glossary.
 */
public class FlashCardListPanel extends UiPart<Region> {

    private static final String FXML = "FlashCardListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(FlashCardListPanel.class);

    @FXML
    private ListView<FlashCard> flashCardListView;


    /**
     * Creates a {@code FlashCardListPanel} with the given {@code ObservableList}.
     */
    public FlashCardListPanel(ObservableList<FlashCard> flashCardList) {
        super(FXML);
        flashCardListView.setItems(flashCardList);
        flashCardListView.setCellFactory(listView -> new FlashCardListViewCell());
    }

    private FlashCardListPanel(ListView<FlashCard> l) {
        super(FXML);
        flashCardListView = l;
    }

    /**
     * Returns a FlashCardListPanel that is scrolled to the index.
     * @param index
     * @return FlashCardListPanel
     */
    public FlashCardListPanel scrollTo(int index) {
        if (index >= 3) {
            logger.log(Level.INFO, "Scrolling to: " + index);
            flashCardListView.scrollTo(index - 2);
            return new FlashCardListPanel(flashCardListView);
        } else if (index == 0) {
            logger.log(Level.INFO, "Scrolling to: " + 0);
            flashCardListView.scrollTo(0);
            return new FlashCardListPanel(flashCardListView);
        }
        logger.log(Level.INFO, "Is scrolled to: " + index);
        return this;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code FlashCard} using a {@code FlashCardCardView}.
     */
    class FlashCardListViewCell extends ListCell<FlashCard> {
        @Override
        protected void updateItem(FlashCard flashCard, boolean empty) {
            super.updateItem(flashCard, empty);

            if (empty || flashCard == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new FlashCardCardView(flashCard, getIndex() + 1).getRoot());
            }
        }
    }
}
