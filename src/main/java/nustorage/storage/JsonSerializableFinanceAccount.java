package nustorage.storage;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.FinanceAccount;
import nustorage.model.record.FinanceRecord;


/**
 * An Immutable Finance Account that is serializable to the JSON format
 */
@JsonRootName("financeAccount")
class JsonSerializableFinanceAccount {

    public static final String MESSAGE_DUPLICATE_FINANCE_RECORD = "Finance record list contains duplicate records!";

    public final List<JsonAdaptedFinanceRecord> financeRecords = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableFinanceAccount} with the given finance records.
     */
    @JsonCreator
    public JsonSerializableFinanceAccount(
            @JsonProperty("financeRecords") List<JsonAdaptedFinanceRecord> financeRecords) {

        this.financeRecords.addAll(financeRecords);
    }


    @JsonCreator
    public JsonSerializableFinanceAccount(FinanceAccount source) {
        financeRecords.addAll(
                source.asUnmodifiableObservableList()
                        .stream()
                        .map(JsonAdaptedFinanceRecord::new)
                        .collect(Collectors.toList())
        );
    }



    /**
     * Converts this finance account into the model's {@link FinanceAccount} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public FinanceAccount toModelType() throws IllegalValueException {
        FinanceAccount finAccount = new FinanceAccount();
        for (JsonAdaptedFinanceRecord jsonFinRecord : this.financeRecords) {
            FinanceRecord finRecord = jsonFinRecord.toModelType();
            if (finAccount.hasRecord(finRecord)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FINANCE_RECORD);
            }
            finAccount.addRecord(finRecord);
        }
        return finAccount;
    }

}
