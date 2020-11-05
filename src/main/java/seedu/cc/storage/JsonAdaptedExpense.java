package seedu.cc.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Entry;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.tag.Tag;

public class JsonAdaptedExpense extends JsonAdaptedEntry {
    /**
     * Constructs a {@code JsonAdaptedAccount} with the given account.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("description") String description,
                              @JsonProperty("amount") String amount,
                              @JsonProperty("tags")List<JsonAdaptedTag> tags) {
        super(description, amount, tags);
    }

    /**
     * Converts a given {@code Entry} into this class for Jackson use.
     */
    public JsonAdaptedExpense(Entry source) {
        super(source);
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                            Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(String.format(Description.MESSAGE_CONSTRAINTS));
        }
        final Description modelDescription = new Description(description);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(String.format(Amount.MESSAGE_CONSTRAINTS));
        }
        final Amount modelAmount = new Amount(amount);
        final Set<Tag> modelTags = new HashSet<>(entryTags);
        return new Expense(modelDescription, modelAmount, modelTags);
    }

}
