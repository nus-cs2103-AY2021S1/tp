package seedu.homerce.storage.expense;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Date;
import seedu.homerce.model.util.attributes.Description;
import seedu.homerce.model.util.attributes.Tag;
import seedu.homerce.storage.JsonAdaptedTag;

/**
 * Jackson-friendly version of {@link Expense}.
 */
public class JsonAdaptedExpense {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Expense's %s field is missing!";

    private final BigDecimal value;
    private final String date;
    private final String description;
    private final String isFixed;
    private final JsonAdaptedTag tag;
    private final boolean isRecurring;

    /**
     * Constructs a {@code JsonAdaptedExpense} with the given Expense details.
     */
    @JsonCreator
    public JsonAdaptedExpense(@JsonProperty("value") Double value, @JsonProperty("date") String date,
                              @JsonProperty("description") String description,
                              @JsonProperty("isFixed") String isFixed, @JsonProperty("tag") JsonAdaptedTag tag,
                              @JsonProperty("isRecurring") boolean isRecurring) throws IllegalValueException {
        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        this.value = new BigDecimal(value);
        this.date = date;
        this.description = description;
        this.isFixed = isFixed;
        this.tag = tag;
        this.isRecurring = isRecurring;
    }

    /**
     * Converts a given {@code Expense} into this class for Jackson use.
     */
    public JsonAdaptedExpense(Expense source) {
        value = source.getValue().value;
        date = source.getDate().toString();
        description = source.getDescription().value;
        isFixed = source.getIsFixed().value ? "y" : "n";
        tag = new JsonAdaptedTag(source.getTag());
        isRecurring = source.getIsFixed().getIsRecurring();
    }

    /**
     * Converts this Jackson-friendly adapted expense object into the model's {@code Expense} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted expense.
     */
    public Expense toModelType() throws IllegalValueException {
        if (value == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }
        if (!Amount.isValidAmount(value.doubleValue())) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelValue = new Amount(value.doubleValue());

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(date)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(date);

        if (isFixed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, IsFixed.class.getSimpleName()));
        }
        if (!IsFixed.isValidIsFixed(isFixed)) {
            throw new IllegalValueException(IsFixed.MESSAGE_CONSTRAINTS);
        }
        IsFixed modelIsFixed;
        if (isFixed.equals("y") && isRecurring) {
            modelIsFixed = new IsFixed("y", true);
        } else if (isFixed.equals("y")) {
            modelIsFixed = new IsFixed("y", false);
        } else {
            modelIsFixed = new IsFixed("n");
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Tag.class.getSimpleName()));
        }
        if (!Tag.isValidTagName(tag.toModelType().tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        final Tag modelTag = new Tag(tag.toModelType().tagName);
        Expense expense = new Expense(modelDescription, modelIsFixed, modelValue, modelDate, modelTag);

        return expense;
    }
}
