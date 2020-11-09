package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class VacancyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Vacancy(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidVacancy = "";
        assertThrows(IllegalArgumentException.class, () -> new Vacancy(invalidVacancy));
    }

    @Test
    public void isValidVacancy() {
        // null vacancy
        assertThrows(NullPointerException.class, () -> Vacancy.isValidVacancy(null));

        // invalid vacancies
        assertFalse(Vacancy.isValidVacancy("")); // empty string
        assertFalse(Vacancy.isValidVacancy(" ")); // spaces only
        assertFalse(Vacancy.isValidVacancy("100")); // more than 2 numbers
        assertFalse(Vacancy.isValidVacancy("many")); // non-numeric
        assertFalse(Vacancy.isValidVacancy("1o")); // alphabets and digits
        assertFalse(Vacancy.isValidVacancy("1 2")); // spaces within digits

        // valid vacancies
        assertTrue(Vacancy.isValidVacancy("1")); // 1 digits
        assertTrue(Vacancy.isValidVacancy("20")); // 2 digits
    }
}
