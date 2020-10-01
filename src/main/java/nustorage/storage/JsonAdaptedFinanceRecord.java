package nustorage.storage;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.FinanceRecord;


/**
 * Jackson-friendly version of {@link FinanceRecord}.
 */
class JsonAdaptedFinanceRecord {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Finance record's %s field is missing!";

    private final double amount;
    private final LocalDateTime dateTime;


    /**
     * Constructs a {@code JsonAdaptedFinanceRecord} with the given record details.
     */
    @JsonCreator
    public JsonAdaptedFinanceRecord(@JsonProperty("amount") double amount,
                                    @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.amount = amount;
        this.dateTime = dateTime;
    }


    /**
     * Converts a given {@code FinanceRecord} into this class for Jackson use.
     */
    @JsonCreator
    public JsonAdaptedFinanceRecord(FinanceRecord source) {
        this.amount = source.getAmount();
        this.dateTime = source.getDate();
    }


    /**
     * Converts this Jackson-friendly adapted finance record object into the model's {@code FinanceRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public FinanceRecord toModelType() throws IllegalValueException {
        if (this.amount < 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "amount"));
        }
        final double modelAmount = this.amount;

        if (this.dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date-time"));
        }
        final LocalDateTime modelDateTime = this.dateTime;

        return new FinanceRecord(modelAmount, modelDateTime);

    }

}
