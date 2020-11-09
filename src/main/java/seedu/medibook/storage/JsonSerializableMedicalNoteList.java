package seedu.medibook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;

/**
 * An Immutable MedicalNoteList that is serializable to JSON format.
 */
@JsonRootName(value = "medical notes")
class JsonSerializableMedicalNoteList {

    @JsonProperty("medical notes")
    private final List<JsonAdaptedMedicalNote> medicalNotes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMedicalNoteList} with the given medical notes.
     */
    @JsonCreator
    public JsonSerializableMedicalNoteList(@JsonProperty("medical notes") List<JsonAdaptedMedicalNote> medicalNotes) {
        this.medicalNotes.addAll(medicalNotes);
    }

    /**
     * Converts a given {@code ReadOnlyMediBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMediBook}.
     */
    public JsonSerializableMedicalNoteList(ReadOnlyMedicalNoteList source) {
        medicalNotes.addAll(source.getMedicalNoteList().stream()
                .map(JsonAdaptedMedicalNote::new).collect(Collectors.toList()));
    }

    /**
     * Converts this medi book into the model's {@code MediBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MedicalNoteList toModelType() throws IllegalValueException {
        MedicalNoteList medicalNoteList = new MedicalNoteList();
        for (JsonAdaptedMedicalNote jsonAdaptedMedicalNote : medicalNotes) {
            MedicalNote medicalNote = jsonAdaptedMedicalNote.toModelType();
            medicalNoteList.add(medicalNote);
        }
        return medicalNoteList;
    }

}
