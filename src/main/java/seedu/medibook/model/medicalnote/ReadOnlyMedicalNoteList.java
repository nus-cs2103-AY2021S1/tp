package seedu.medibook.model.medicalnote;

import java.util.List;

/**
 * Unmodifiable view of a medical notes list
 */
public interface ReadOnlyMedicalNoteList {

    /**
     * Returns an unmodifiable view of the patient's medical notes.
     */
    List<MedicalNote> getMedicalNoteList();

}
