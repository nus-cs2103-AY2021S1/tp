package seedu.bookmark.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.bookmark.commons.core.LogsCenter;
import seedu.bookmark.model.book.Book;

/**
 * Panel containing the list of books.
 */
public class BookListPanel extends UiPart<Region> {

    private static final String FXML = "BookListPanel.fxml";
    @FXML
    protected ListView<Book> bookListView;
    private final Logger logger = LogsCenter.getLogger(BookListPanel.class);

    /**
     * Creates a {@code BookListPanel} with the given {@code ObservableList}.
     */
    public BookListPanel(ObservableList<Book> bookList) {
        super(FXML);
        bookListView.setItems(bookList);
        bookListView.setCellFactory(lv -> new BookListViewCell());
    }

    /**
     * Constructor for subclasses to specify their own fxml file path.
     */
    public BookListPanel(String fxmlPath) {
        super(fxmlPath);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Book} using a {@code BookCard}.
     */
    class BookListViewCell extends ListCell<Book> {
        @Override
        protected void updateItem(Book book, boolean empty) {
            super.updateItem(book, empty);

            if (empty || book == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new BookCard(book, getIndex() + 1).getRoot());
            }
        }
    }

}
