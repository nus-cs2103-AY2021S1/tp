package seedu.medibook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.Account;
import seedu.medibook.model.UserAccountsList;

@JsonRootName(value = "accounts")
class JsonSerializableUserAccountsList {

    @JsonProperty("accounts")
    private final List<JsonAdaptedAccount> accounts = new ArrayList<>();

    @JsonCreator
    public JsonSerializableUserAccountsList(@JsonProperty("accounts") List<JsonAdaptedAccount> accounts) {
        this.accounts.addAll(accounts);
    }

    public JsonSerializableUserAccountsList(UserAccountsList source) {
        accounts.addAll(source.getAccountsList().stream().map(JsonAdaptedAccount::new).collect(Collectors.toList()));
    }

    public UserAccountsList toModelType() throws IllegalValueException {
        UserAccountsList userAccountsList = new UserAccountsList();
        for (JsonAdaptedAccount jsonAdaptedAccount : accounts) {
            Account account = jsonAdaptedAccount.toModelType();
            userAccountsList.addAccount(account);
        }
        return userAccountsList;
    }
}
