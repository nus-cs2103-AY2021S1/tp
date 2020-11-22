package guitests.guihandles;

import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.note.CountryNote;

/**
 * Provides a handle for {@code CountryNoteListPanel} containing the list of {@code NoteListCard}.
 */
public class CountryNoteListPanelHandle extends NodeHandle<ListView<CountryNote>> {
    //TODO: This class looks like it is needed for testing but is not being used?

    private static final String COUNTRY_NOTE_LIST_VIEW_ID = "#countryNoteListView";

    private static final String COUNTRY_NOTE_CARD_CONTAINER_ID = "#countryNoteContainer";

    public CountryNoteListPanelHandle(ListView<CountryNote> countryNoteListNode) {
        super(countryNoteListNode);
    }

    /**
     * Returns the client card handle of a client associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public NoteListCardHandle getCountryNoteCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(NoteListCardHandle::new)
                .filter(handle -> handle.equals(getCountryNote(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private CountryNote getCountryNote(int index) {
        return getRootNode().getItems().get(index);
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(COUNTRY_NOTE_CARD_CONTAINER_ID).queryAll();
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
