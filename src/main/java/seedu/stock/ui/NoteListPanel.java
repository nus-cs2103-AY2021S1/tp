package seedu.stock.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.stock.commons.core.LogsCenter;
import seedu.stock.model.stock.Note;

public class NoteListPanel extends UiPart<Region> {

    private static final String FXML = "NoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StockListPanel.class);

    @FXML
    private ListView<Note> notesListView;

    /**
     * Creates a {@code StockListPanel} with the given {@code ObservableList}.
     */
    public NoteListPanel(ObservableList<Note> noteList) {
        super(FXML);
        notesListView.setItems(noteList);
        notesListView.setCellFactory(listView -> new NoteListPanel.NoteListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Stock} using a {@code StockCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, getIndex() + 1).getRoot());
            }
        }
    }
}
