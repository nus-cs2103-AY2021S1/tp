package seedu.address.model.clientsource;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClientSourceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientSource(null));
    }

    @Test
    public void constructor_invalidClientSourceName_throwsIllegalArgumentException() {
        String invalidClientSourceName = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientSource(invalidClientSourceName));
    }

    @Test
    public void isValidClientSourceName() {
        // null clientsource name
        assertThrows(NullPointerException.class, () -> ClientSource.isValidClientSourceName(null));
    }

}
