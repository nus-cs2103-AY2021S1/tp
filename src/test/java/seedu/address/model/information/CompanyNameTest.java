package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    public static final String HUNDRED_CHARACTER_STRING = "4}ZL$@Uw9kHD19 D9bZ)$Mk;8m|kIs},B[qlS>HFYJd1U;"
            + "$4|FcxWft r0+q9 P#4o3\" Et!>U=rB<{?DHp+hC|q>/uwi|3|*UYI";


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidCompanyName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidCompanyName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidCompanyName(null));

        // invalid company name
        assertFalse(CompanyName.isValidCompanyName("")); // empty string
        assertFalse(CompanyName.isValidCompanyName(" ")); // spaces only
        assertFalse(CompanyName.isValidCompanyName(HUNDRED_CHARACTER_STRING + "a")); // 101 characters

        // valid company name
        assertTrue(CompanyName.isValidCompanyName("^")); // only non-alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("peter*")); // contains non-alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("peter jack")); // alphabets only; person name
        assertTrue(CompanyName.isValidCompanyName("12345")); // numbers only
        assertTrue(CompanyName.isValidCompanyName("peter the 2nd")); // alphanumeric characters
        assertTrue(CompanyName.isValidCompanyName("Capital Tan")); // with capital letters
        assertTrue(CompanyName.isValidCompanyName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(CompanyName.isValidCompanyName("Google")); // alphabets only; company name
        assertTrue(CompanyName.isValidCompanyName("Software engineer")); // alphabets only; job title
        assertTrue(CompanyName.isValidCompanyName(HUNDRED_CHARACTER_STRING)); // 100 characters
    }
}
