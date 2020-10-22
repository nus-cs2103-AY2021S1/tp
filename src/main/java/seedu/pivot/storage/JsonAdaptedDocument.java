package seedu.pivot.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.model.investigationcase.Document;
import seedu.pivot.model.investigationcase.Name;
import seedu.pivot.model.investigationcase.Reference;

/**
 * Jackson-friendly version of {@link Document}.
 */
class JsonAdaptedDocument {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Document's %s field is missing!";

    private final String name;
    private final String reference;

    /**
     * Constructs a {@code JsonAdaptedDocument} with the given {@code documentStorageName }.
     */
    @JsonCreator
    public JsonAdaptedDocument(@JsonProperty("name") String name, @JsonProperty("reference") String reference) {
        this.name = name;
        this.reference = reference;
    }

    /**
     * Converts a given {@code Document} into this class for Jackson use.
     */
    public JsonAdaptedDocument(Document source) {
        name = source.getName().getAlphaNum();
        reference = source.getReference().getFileName();
    }


    /**
     * Converts this Jackson-friendly adapted Document object into the model's {@code Document} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Document.
     */
    public Document toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (reference == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "reference"));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        if (!Reference.isValidReference(reference)) {
            throw new IllegalValueException(Reference.MESSAGE_CONSTRAINTS);
        }
        return new Document(new Name(name), new Reference(reference));
    }

}
