package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import seedu.address.model.note.Note;

/**
 * Provides a handler for {@code NoteListCard}.
 */
public class NoteListCardHandle extends NodeHandle<Node> {

    private static final String NOTE_ID_LABEL_ID = "#id";
    private static final String NOTE_CONTENT_TEXT_ID = "#noteContent";
    private final Label noteId;
    private final Text noteContent;

    /**
     * Constructor for handler.
     *
     * @param noteListCard Node to handle.
     */
    public NoteListCardHandle(Node noteListCard) {
        super(noteListCard);
        noteId = getChildNode(NOTE_ID_LABEL_ID);
        noteContent = getChildNode(NOTE_CONTENT_TEXT_ID);
    }

    public String getNoteContent() {
        return noteContent.getText();
    }

    public String getNoteId() {
        return noteId.getText();
    }

    /**
     * Returns true if this handle contains {@code client}.
     */
    public boolean equals(Note note) {
        return getNoteContent().equals(note.getNoteContent());
    }

}
