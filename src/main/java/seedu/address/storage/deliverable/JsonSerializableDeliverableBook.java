package seedu.address.storage.deliverable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;
import seedu.address.model.deliverable.deliverable.Deliverable;


/**
 * An Immutable DeliverableBook that is serializable to JSON format.
 */
@JsonRootName(value = "deliverablebook")
public class JsonSerializableDeliverableBook {
    public static final String MESSAGE_DUPLICATE_DELIVERABLE = "Deliverables list contains duplicate deliverable(s).";

    private final List<JsonAdaptedDeliverable> deliverables = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDeliverableBook} with the given deliverables.
     */
    @JsonCreator
    public JsonSerializableDeliverableBook(@JsonProperty("deliverables") List<JsonAdaptedDeliverable> deliverables) {
        this.deliverables.addAll(deliverables);
    }

    /**
     * Converts a given {@code ReadOnlyDeliverableBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDeliverableBook}.
     */
    public JsonSerializableDeliverableBook(ReadOnlyDeliverableBook source) {
        deliverables.addAll(source.getDeliverableList().stream().map(JsonAdaptedDeliverable::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this deliverable book into the model's {@code DeliverableBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DeliverableBook toModelType() throws IllegalValueException {
        DeliverableBook deliverableBook = new DeliverableBook();
        for (JsonAdaptedDeliverable jsonAdaptedDeliverable : deliverables) {
            Deliverable deliverable = jsonAdaptedDeliverable.toModelType();
            if (deliverableBook.hasDeliverable(deliverable)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DELIVERABLE);
            }
            deliverableBook.addDeliverable(deliverable);
        }
        return deliverableBook;
    }
}
