package seedu.bookmark.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Note;

/**
 * Panel that shows detailed information about the books.
 */
public class DetailedBookListPanel extends BookListPanel {

    private static final String FXML = "DetailedBookListPanel.fxml";

    @FXML
    private ListView<Note> bookNotesListView;
    @FXML
    private Label notesHeading;


    /**
     * Creates a {@code DetailedBookListPanel} with the given {@code ObservableList}.
     */
    public DetailedBookListPanel(ObservableList<Book> bookList) {
        super(FXML);
        assert(bookList.size() <= 1);

        bookListView.setItems(bookList);
        bookListView.setCellFactory(lv -> new DetailedBookListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code DetailedBookCard}.
     */
    class DetailedBookListViewCell extends ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);

            if (empty || book == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DetailedBookCard(book, getIndex() + 1).getRoot());
            }
        }
    }
}
