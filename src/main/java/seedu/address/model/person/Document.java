package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class Document {

    public static final String MESSAGE_CONSTRAINTS = "Document names should be alphanumeric";
    public static final String NAME_VALIDATION_REGEX = "\\p{Alnum}+";

    public final String name;
    public final String reference;

    /**
     * Constructs an {@code Document}.
     *
     * @param name A valid name.
     * @param reference A valid reference.
     */
    public Document(String name, String reference) {
        requireNonNull(name);
        requireNonNull(reference);

        this.name = name;
        this.reference = reference;
    }



    public String getName() {
        return this.name;
    }

    public String getReference() {
        return this.reference;
    }

    //Currently only checks for alphanumeric names
    public static boolean isValidDocumentName(String documentName, String documentReference) {
        return documentName.matches(NAME_VALIDATION_REGEX) && documentReference != "";
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Document // instanceof handles nulls
                && name.equals(((Document) other).name)
                && reference.equals(((Document) other).reference)); // state check
    }


    @Override
    public int hashCode() {
        return name.hashCode() + reference.hashCode();
    }

    @Override
    public String toString() {
        return this.name + ", " + this.reference;
    }
}
