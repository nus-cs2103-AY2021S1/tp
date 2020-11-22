package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class CountryTest {

    private static final String[] COUNTRY_CODES = Locale.getISOCountries();

    @Test
    public void constructor_validCountryCode_returnCountry() {
        for (String countryCodes : COUNTRY_CODES) {
            try {
                new Country(countryCodes);
            } catch (AssertionError e) {
                fail();
            }
        }
    }

    @Test
    public void constructor_invalidCountryCode_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new Country("abc"));
        assertThrows(AssertionError.class, () -> new Country("12"));
        assertThrows(AssertionError.class, () -> new Country("ab"));
        assertThrows(AssertionError.class, () -> new Country("AA"));
    }

    @Test
    public void getCountryName_differentCountry_differentCountryName() {
        for (int i = 0; i < COUNTRY_CODES.length - 1; i++) {
            Country countryFirst = new Country(COUNTRY_CODES[i]);
            Country countrySecond = new Country(COUNTRY_CODES[i + 1]);
            assertNotEquals(countryFirst.getCountryName(), countrySecond.getCountryName());
        }
    }

    @Test
    public void getCountryName_sameCountry_sameCountryName() {
        for (String countryCode : COUNTRY_CODES) {
            Country countryFirst = new Country(countryCode);
            Country countrySecond = new Country(countryCode);
            assertEquals(countryFirst.getCountryName(), countrySecond.getCountryName());
        }
    }

    @Test
    public void equals_notACountry_returnFalse() {
        for (String countryCode : COUNTRY_CODES) {
            Country countryFirst = new Country(countryCode);
            Object obj = new Object();
            assertNotEquals(countryFirst, obj);
        }
    }

    @Test
    public void equals_differentCountry_returnFalse() {
        for (int i = 0; i < COUNTRY_CODES.length - 1; i++) {
            Country countryFirst = new Country(COUNTRY_CODES[i]);
            Country countrySecond = new Country(COUNTRY_CODES[i + 1]);
            assertNotEquals(countryFirst, countrySecond);
            assertNotEquals(countrySecond, countryFirst);
        }
    }

    @Test
    public void equals_sameCountry_returnTrue() {
        for (String countryCode : COUNTRY_CODES) {
            Country countryFirst = new Country(countryCode);
            Country countrySecond = new Country(countryCode);
            assertEquals(countryFirst, countrySecond);
            assertEquals(countrySecond, countryFirst);
        }
    }

    @Test
    public void hashCode_differentCountry_differentHashCode() {
        for (int i = 0; i < COUNTRY_CODES.length - 1; i++) {
            Country countryFirst = new Country(COUNTRY_CODES[i]);
            Country countrySecond = new Country(COUNTRY_CODES[i + 1]);
            assertNotEquals(countryFirst.hashCode(), countrySecond.hashCode());
        }
    }

    @Test
    public void hashCode_sameCountry_sameHashCode() {
        for (String countryCode : COUNTRY_CODES) {
            Country countryFirst = new Country(countryCode);
            Country countrySecond = new Country(countryCode);
            assertEquals(countryFirst.hashCode(), countrySecond.hashCode());
        }
    }
}
