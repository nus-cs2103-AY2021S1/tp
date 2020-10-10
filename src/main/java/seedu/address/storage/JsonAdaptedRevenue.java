package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.account.entry.Amount;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.tag.Tag;

public class JsonAdaptedRevenue extends JsonAdaptedEntry {

    /**
     * Constructs a {@code JsonAdaptedEntry} with the given entry's details.
     */
    public JsonAdaptedRevenue(String type, String description, String amount, List<JsonAdaptedTag> tags) {
        super("revenue", description, amount, tags);
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedRevenue(Entry source) {
        super("expense", source.getDescription().toString(),
                source.getAmount().toString(),
                source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted revenue object into the model's {@code Revenue} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted revenue.
     */
    @Override
    public Revenue toModelType() throws IllegalValueException {
        final List<Tag> entryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            entryTags.add(tag.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final Description modelDescription = new Description(description);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        final Amount modelAmount = new Amount(amount);

        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "type"));
        }

        final Set<Tag> modelTags = new HashSet<>(entryTags);
        return new Revenue(modelDescription, modelAmount, modelTags);
    }

}
