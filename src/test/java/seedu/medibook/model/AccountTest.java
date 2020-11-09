package seedu.medibook.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;

public class AccountTest {

    @Test
    void equals() {
        Account account1 = new Account("user", "pass",
                new Doctor(new Name("Brandon"), new Mcr("MP2037X")));
        Account account2 = new Account("user", "pass",
                new Doctor(new Name("Brandon"), new Mcr("MP2037X")));
        assertTrue(account1.equals(account2));
    }
}
