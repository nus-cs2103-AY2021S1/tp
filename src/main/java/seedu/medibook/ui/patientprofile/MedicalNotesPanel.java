package seedu.medibook.ui.patientprofile;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.ui.UiPart;

/**
 * Panel containing the list of {@code MedicalNote} of a {@code Patient}.
 */
public class MedicalNotesPanel extends UiPart<Region> {

    private static final String FXML = "MedicalNotesPanel.fxml";

    @FXML
    private Label panelHeader;
    @FXML
    private ListView<MedicalNote> medicalNotesListView;

    /**
     * Creates a {@code MedicalNotesPanel} with the given {@code List} of {@code MedicalNote} objects.
     */
    public MedicalNotesPanel(ObservableList<MedicalNote> observableMedicalNotes) {
        super(FXML);

        panelHeader.setText("Medical Notes:");
        medicalNotesListView.setItems(observableMedicalNotes);
        medicalNotesListView.setCellFactory(listView -> new MedicalNotesPanel.MedicalNotesListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code MedicalNote} using a {@code MedicalNoteCard}.
     */
    class MedicalNotesListViewCell extends ListCell<MedicalNote> {
        @Override
        protected void updateItem(MedicalNote medicalNote, boolean empty) {
            super.updateItem(medicalNote, empty);

            if (empty || medicalNote == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalNoteCard(medicalNote, getIndex() + 1).getRoot());
            }
        }
    }
}

