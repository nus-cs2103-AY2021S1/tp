package seedu.bookmark.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.bookmark.MainApp;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Goal;

/**
 * An UI component that displays information of a {@code Book}.
 */
public class BookCard extends UiPart<Region> {

    protected static final String BOOKMARK_ICON_PATH = "/images/bookmark.png";
    protected static final String NO_BOOKMARK_ICON_PATH = "/images/no_bookmark.png";
    private static final String FXML = "BookCard.fxml";
    private static final String COMPLETED_STYLE = "-fx-text-fill: lime";
    private static final String DEFAULT_STYLE = "";
    private static final String IN_PROGRESS_STYLE = "-fx-text-fill: gold";
    private static final String OVERDUE_STYLE = "-fx-text-fill: red";


    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.

     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Book book;

    @FXML
    protected HBox cardPane;
    @FXML
    protected Label name;
    @FXML
    protected Label id;
    @FXML
    protected Label genre;
    @FXML
    protected Label totalPages;
    @FXML
    protected Label bookmark;
    @FXML
    protected FlowPane tags;
    @FXML
    protected ImageView bookmarkIcon;
    @FXML
    protected Label noteLabel;
    @FXML
    protected FlowPane notes;
    @FXML
    protected Label goal;

    /**
     * Creates a {@code BookCard} with the given {@code Book} and index to display.
     */
    public BookCard(Book book, int displayedIndex) {
        super(FXML);
        this.book = book;
        initialize(book, displayedIndex);
    }

    /**
     * Work around for subclasses to specify their own fxml file path.
     */
    public BookCard(Book book, String fxml) {
        super(fxml);
        this.book = book;
    }

    protected void initialize(Book book, int displayedIndex) {
        id.setText(displayedIndex + ". ");
        setCompulsoryFields(book);
        setBookmark(book);
        setTags(book);
        setNotes(book);
        goal.setText(determineGoalText(book));
        goal.setStyle(determineGoalStyle(book));
    }

    private void setCompulsoryFields(Book book) {
        name.setText(book.getName().fullName);
        name.setWrapText(true);
        name.setTextAlignment(TextAlignment.JUSTIFY);
        genre.setText(book.getGenre().value);
        totalPages.setText(book.getTotalPages().value + " pages");
    }

    private void setBookmark(Book book) {
        if (book.hasStartedReading()) {
            bookmark.setText("Bookmarked @ page " + book.getBookmark().value);
            Image image = new Image(MainApp.class.getResourceAsStream(BOOKMARK_ICON_PATH));
            bookmarkIcon.setImage(image);
        } else {
            bookmark.setText("No bookmark for this book yet");
            Image image = new Image(MainApp.class.getResourceAsStream(NO_BOOKMARK_ICON_PATH));
            bookmarkIcon.setImage(image);
        }
    }

    private void setTags(Book book) {
        book.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setNotes(Book book) {
        if (book.hasNotes()) {
            noteLabel.setText("Notes:");
            book.getNotes()
                    .forEach(note -> notes.getChildren().add(new Label(note.title)));
        } else {
            noteLabel.setText("Notes: No notes added");
        }
    }

    protected String determineGoalStyle(Book book) {
        if (book.goalCompleted()) {
            return COMPLETED_STYLE;
        } else if (book.goalInProgress()) {
            return IN_PROGRESS_STYLE;
        } else if (book.goalOverdue()) {
            return OVERDUE_STYLE;
        } else { // No goal
            return DEFAULT_STYLE;
        }
    }

    protected String determineGoalText(Book book) {
        String goalText = String.format("Goal: %s", book.getGoal().toString());
        if (book.goalCompleted()) {
            goalText += Goal.UI_COMPLETED;
        } else if (book.goalOverdue()) {
            goalText += Goal.UI_OVERDUE;
        } else if (book.goalInProgress()) {
            goalText += Goal.UI_IN_PROGRESS;
        }
        return goalText;
    }
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookCard)) {
            return false;
        }

        // state check
        BookCard card = (BookCard) other;
        return id.getText().equals(card.id.getText())
                && book.equals(card.book);
    }
}
