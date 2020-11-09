package seedu.medibook.testutil;

import seedu.medibook.model.Account;
import seedu.medibook.model.UserAccountsList;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

public class TypicalAccounts {
    public static final Account VALID_ACCOUNT1 = new Account("user1", "password1",
            new Doctor(new Name("Doctor"), new Mcr("M12345Q")));

    public static final Account VALID_ACCOUNT2 = new Account("user2", "password2",
            new Doctor(new Name("test"), new Mcr("M65321A")));

    public static final Account VALID_ACCOUNT3 = new Account("user3", "password3",
            new Doctor(new Name("asdf"), new Mcr("M11111T")));

    public static UserAccountsList getTypicalAccountsList() {
        UserAccountsList userAccounts = new UserAccountsList();
        userAccounts.addAccount(VALID_ACCOUNT1);;
        userAccounts.addAccount(VALID_ACCOUNT2);
        userAccounts.addAccount(VALID_ACCOUNT3);
        return userAccounts;
    }
}
