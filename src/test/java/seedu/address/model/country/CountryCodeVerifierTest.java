package seedu.address.model.country;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;

import org.junit.jupiter.api.Test;

public class CountryCodeVerifierTest {

    @Test
    public void isValidCode_validCode_returnTrue() {
        for (String countryCode : CountryCodeVerifier.getCountryCodes()) {
            assertTrue(CountryCodeVerifier.isValidCountryCode(countryCode));
        }
    }

    @Test
    public void isValidCode_invalidCode_returnFalse() {
        assertFalse(CountryCodeVerifier.isValidCountryCode("ZZ"));
        assertFalse(CountryCodeVerifier.isValidCountryCode("12"));
        assertFalse(CountryCodeVerifier.isValidCountryCode("az"));
        assertFalse(CountryCodeVerifier.isValidCountryCode("bd"));
        assertFalse(CountryCodeVerifier.isValidCountryCode("bdc"));
    }

    @Test
    public void getCountryCodes_returnsIsoCountries() {
        assertArrayEquals(Locale.getISOCountries(), CountryCodeVerifier.getCountryCodes());
    }
}
