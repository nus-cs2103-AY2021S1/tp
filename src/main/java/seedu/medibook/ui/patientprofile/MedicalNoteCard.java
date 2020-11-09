package seedu.medibook.ui.patientprofile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays a {@code MedicalNote}.
 */
public class MedicalNoteCard extends UiPart<Region> {

    private static final String FXML = "MedicalNoteCard.fxml";

    public final MedicalNote medicalNote;

    @FXML
    private HBox cardPane;
    @FXML
    private Label noteIndex;
    @FXML
    private Label date;
    @FXML
    private Label doctorName;
    @FXML
    private TextArea content;

    /**
     * Creates a {@code MedicalNoteCard} with the given {@code MedicalNote} to be displayed.
     */
    public MedicalNoteCard(MedicalNote medicalNote, int displayedIndex) {
        super(FXML);
        this.medicalNote = medicalNote;
        noteIndex.setText("Note #" + displayedIndex);
        date.setText(medicalNote.date.toString());
        doctorName.setText(medicalNote.doctor.toString());
        content.setText(medicalNote.getContentString());
        content.prefWidthProperty().bind(this.getRoot().prefWidthProperty());
        content.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalNoteCard)) {
            return false;
        }

        // state check
        MedicalNoteCard card = (MedicalNoteCard) other;
        return medicalNote.equals(card.medicalNote);
    }
}
