package seedu.pivot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.storage.JsonAdaptedCase.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.BENSON_MEIER_ROBBERY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.model.investigationcase.Description;
import seedu.pivot.model.investigationcase.Status;
import seedu.pivot.model.investigationcase.Title;

public class JsonAdaptedCaseTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DESCRIPTION = "Esm@nd@";
    private static final String INVALID_DOCUMENT_REFERENCE = "invalid :across ?/\0 OS";
    private static final String INVALID_DOCUMENT_NAME = " ";
    private static final String INVALID_STATUS = "status";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_WITNESS_NAME = "T|M";
    private static final String INVALID_SUSPECT_NAME = "!!!";
    private static final String INVALID_VICTIM_NAME = "P@ul";
    private static final String INVALID_GENDER = "P";
    private static final String INVALID_PHONE = "phone";
    private static final String INVALID_EMAIL = "abc";

    private static final String VALID_NAME = BENSON_MEIER_ROBBERY.getTitle().toString();
    private static final String VALID_DESCRIPTION = BENSON_MEIER_ROBBERY.getDescription().toString();
    private static final String VALID_STATUS = BENSON_MEIER_ROBBERY.getStatus().toString();
    private static final String VALID_DOCUMENT_NAME = "name";
    private static final String VALID_DOCUMENT_REFERENCE = "test1.txt";
    private static final List<JsonAdaptedDocument> VALID_DOCUMENTS = BENSON_MEIER_ROBBERY.getDocuments().stream()
            .map(JsonAdaptedDocument::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedSuspect> VALID_SUSPECTS = BENSON_MEIER_ROBBERY.getSuspects().stream()
            .map(JsonAdaptedSuspect::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedVictim> VALID_VICTIMS = BENSON_MEIER_ROBBERY.getVictims().stream()
            .map(JsonAdaptedVictim::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON_MEIER_ROBBERY.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedWitness> VALID_WITNESSES = BENSON_MEIER_ROBBERY.getWitnesses().stream()
            .map(JsonAdaptedWitness::new)
            .collect(Collectors.toList());
    private static final String VALID_ADDRESS = "Blk 123";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedCase person = new JsonAdaptedCase(BENSON_MEIER_ROBBERY);
        assertEquals(BENSON_MEIER_ROBBERY, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCase person =
                new JsonAdaptedCase(INVALID_NAME, VALID_DESCRIPTION, VALID_STATUS,
                        VALID_DOCUMENTS,
                        VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCase person = new JsonAdaptedCase(null, VALID_DESCRIPTION, VALID_STATUS,
                VALID_DOCUMENTS, VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, INVALID_DESCRIPTION, VALID_STATUS,
                        VALID_DOCUMENTS,
                        VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, null, VALID_STATUS, VALID_DOCUMENTS,
                        VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, INVALID_STATUS, VALID_DOCUMENTS,
                        VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedCase person = new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION,
                null, VALID_DOCUMENTS, VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, VALID_STATUS,
                        VALID_DOCUMENTS, VALID_SUSPECTS, VALID_VICTIMS,
                        VALID_WITNESSES, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidSuspect_throwsIllegalValueException() {
        List<JsonAdaptedSuspect> invalidSuspects = new ArrayList<>(VALID_SUSPECTS);
        invalidSuspects.add(new JsonAdaptedSuspect(INVALID_SUSPECT_NAME, INVALID_GENDER, INVALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS));
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, VALID_STATUS, VALID_DOCUMENTS,
                        invalidSuspects, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidVictims_throwsIllegalValueException() {
        List<JsonAdaptedVictim> invalidVictims = new ArrayList<>(VALID_VICTIMS);
        invalidVictims.add(new JsonAdaptedVictim(INVALID_VICTIM_NAME, INVALID_GENDER, INVALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS));
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, VALID_STATUS, VALID_DOCUMENTS,
                        VALID_SUSPECTS, invalidVictims, VALID_WITNESSES, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidWitnesses_throwsIllegalValueException() {
        List<JsonAdaptedWitness> invalidWitnesses = new ArrayList<>(VALID_WITNESSES);
        invalidWitnesses.add(new JsonAdaptedWitness(INVALID_WITNESS_NAME, INVALID_GENDER, INVALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS));
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, VALID_STATUS, VALID_DOCUMENTS,
                        VALID_SUSPECTS, VALID_VICTIMS, invalidWitnesses, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidDocuments_throwsIllegalValueException() {
        List<JsonAdaptedDocument> invalidDocumentReference = new ArrayList<>(VALID_DOCUMENTS);
        invalidDocumentReference.add(new JsonAdaptedDocument(VALID_DOCUMENT_NAME, INVALID_DOCUMENT_REFERENCE));
        JsonAdaptedCase person =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, VALID_STATUS, invalidDocumentReference,
                        VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        assertThrows(IllegalValueException.class, person::toModelType);

        List<JsonAdaptedDocument> invalidDocumentName = new ArrayList<>(VALID_DOCUMENTS);
        invalidDocumentName.add(new JsonAdaptedDocument(INVALID_DOCUMENT_NAME, VALID_DOCUMENT_REFERENCE));
        JsonAdaptedCase person2 =
                new JsonAdaptedCase(VALID_NAME, VALID_DESCRIPTION, VALID_STATUS, invalidDocumentName,
                        VALID_SUSPECTS, VALID_VICTIMS, VALID_WITNESSES, VALID_TAGS);
        assertThrows(IllegalValueException.class, person2::toModelType);
    }

}
