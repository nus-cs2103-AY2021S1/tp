package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Document;

/**
 * Jackson-friendly version of {@link Document}.
 */
class JsonAdaptedDocument {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "DOCUMENT's %s field is missing!";

    private String documentName;
    private String documentReference;

    /**
     * Constructs a {@code JsonAdaptedDocument} with the given {@code documentStorageName }.
     */
    @JsonCreator
    public JsonAdaptedDocument(String documentStorageValue) {
        String[] doc = documentStorageValue.split(", ");
        try {
            Document document = new Document(doc[0], doc[1]);
            this.documentName = doc[0];
            this.documentReference = doc[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.documentName = null;
            this.documentReference = null;
        }
    }

    /**
     * Converts a given {@code Document} into this class for Jackson use.
     */
    public JsonAdaptedDocument(Document source) {
        documentName = source.getName();
        documentReference = source.getReference();
    }

    @JsonValue
    public String getDocumentStorage() {
        return documentName + ", " + documentReference;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Document toModelType() throws IllegalValueException {
        if (documentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "document name"));
        }
        if (documentReference == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "document reference"));
        }
        if (!Document.isValidDocumentName(documentName, documentReference)) {
            throw new IllegalValueException(Document.MESSAGE_CONSTRAINTS);
        }
        return new Document(documentName, documentReference);
    }

}
