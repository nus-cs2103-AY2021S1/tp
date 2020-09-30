package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CommonCents;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.account.Account;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "commoncents")
public class JsonSerializableCommonCents {

    public static final String MESSAGE_DUPLICATE_ACCOUNT = "Account list contains duplicate account(s).";

    private final List<JsonAdaptedAccount> accounts = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCommonCents} with the given accounts.
     */
    @JsonCreator
    public JsonSerializableCommonCents(@JsonProperty("accounts") List<JsonAdaptedAccount> accounts) {
        this.accounts.addAll(accounts);
    }

    /**
     * Converts a given {@code ReadOnlyCommonCents} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCommonCents}.
     */
    public JsonSerializableCommonCents(ReadOnlyCommonCents source) {
        accounts.addAll(source.getAccountList().stream().map(JsonAdaptedAccount::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */

    public CommonCents toModelType() throws IllegalValueException {
        CommonCents commonCents = new CommonCents();
        for (JsonAdaptedAccount jsonAdaptedAccount : accounts) {
            Account account = jsonAdaptedAccount.toModelType();
            if (commonCents.hasAccount(account)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ACCOUNT);
            }
            commonCents.addAccount(account);
        }
        return commonCents;
    }
}
