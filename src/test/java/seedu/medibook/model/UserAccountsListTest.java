package seedu.medibook.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

public class UserAccountsListTest {

    private UserAccountsList testUserAccountsList = new UserAccountsList();

    @Test
    public void check_invalidInput_returnEmptyOptional() {
        Account testAccount = new Account("user", "pass",
                new Doctor(new Name("Brandon"), new Mcr("MP2037X")));
        testUserAccountsList.addAccount(testAccount);
        assertEquals(Optional.empty(), testUserAccountsList.check("user2", "pass"));
        assertEquals(Optional.empty(), testUserAccountsList.check("user", "pass2"));
    }

    @Test
    public void check_validInput_successWithCorrectAccount() {
        Account testAccount = new Account("user", "pass",
                new Doctor(new Name("Brandon"), new Mcr("MP2037X")));
        testUserAccountsList.addAccount(testAccount);
        assertEquals(Optional.of(testAccount), testUserAccountsList.check("user", "pass"));
    }
}
