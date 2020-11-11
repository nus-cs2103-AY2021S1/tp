package seedu.bookmark.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.bookmark.model.book.Note;

public class NoteCard extends UiPart<Region> {

    private static final String FXML = "NoteCard.fxml";

    public final Note note;

    @FXML
    protected HBox cardPane;
    @FXML
    protected Label id;
    @FXML
    protected Label title;
    @FXML
    protected Label body;

    /**
     * Creates a {@code NoteCard} with the given {@code Note} and index to display.
     */
    public NoteCard(Note note, int displayedIndex) {
        super(FXML);
        this.note = note;
        initialize(note, displayedIndex);
    }

    /**
     * Initializes the components of this {@code NoteCard} using the given {@code Note} and index to display.
     */
    public void initialize(Note note, int displayedIndex) {
        id.setText(displayedIndex + ". ");
        title.setText(note.title);
        body.setText(note.text);
        title.setWrapText(true);
        title.setTextAlignment(TextAlignment.JUSTIFY);
        body.setWrapText(true);
        body.setTextAlignment(TextAlignment.JUSTIFY);
    }
}
