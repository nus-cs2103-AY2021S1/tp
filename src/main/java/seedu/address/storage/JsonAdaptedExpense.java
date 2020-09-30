package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.account.entry.*;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JsonAdaptedExpense extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedExpense} with the given expense's details.
     */
    public JsonAdaptedExpense(String description, String amount, List<JsonAdaptedTag> tags) {
        super("expense", description, amount, tags);
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedExpense(Entry source) {
        super("expense", source.getDescription().toString(),
                source.getAmount().toString(),
                source.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted profit object into the model's {@code Profit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted profit.
     */
    @Override
    public Expense toModelType() throws IllegalValueException {
        final List<Tag> entryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            entryTags.add(tag.toModelType());
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
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
        return new Expense(modelDescription, modelAmount, modelTags);
    }
}
