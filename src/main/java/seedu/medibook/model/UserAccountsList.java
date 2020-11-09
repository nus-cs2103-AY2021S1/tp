package seedu.medibook.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserAccountsList {

    private final List<Account> accountsList = new ArrayList<>();

    public void addAccount(Account account) {
        this.accountsList.add(account);
    }

    public List<Account> getAccountsList() {
        return this.accountsList;
    }

    /**
     * Checks if a given username and password are representative of an account.
     */
    public Optional<Account> check(String username, String password) {
        Optional<Account> result = Optional.empty();
        for (Account account : accountsList) {
            if (account.check(username, password)) {
                result = Optional.of(account);
            }
        }
        return result;
    }

    /**
     * Checks if a username already exists in the account database.
     */
    public boolean usernameExists(Account newAccount) {
        boolean result = false;
        for (Account account : accountsList) {
            if (newAccount.getUsername().equals(account.getUsername())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks if a doctor's MCR is already linked with an account in the database.
     */
    public boolean mcrExists(Account newAccount) {
        boolean result = false;
        for (Account account : accountsList) {
            if (newAccount.getDoctor().getMcr().equals(account.getDoctor().getMcr())) {
                result = true;
            }
        }
        return result;
    }
    /**
     * Checks if two UserAccountsList are equals.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UserAccountsList)) {
            return false;
        }

        UserAccountsList otherList = (UserAccountsList) other;
        return otherList.accountsList.equals(this.accountsList);
    }
}
