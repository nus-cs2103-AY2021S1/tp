package seedu.address.model.account.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class RevenueTest {

    private static final Description VALID_DESCRIPTION = new Description("sell crafts");
    private static final String INVALID_DESCRIPTION_STRING = "";
    private static final Amount VALID_AMOUNT = new Amount("30.00");
    private static final String INVALID_AMOUNT_STRING = "";
    private static final Tag VALID_TAG_1 = new Tag("art");
    private static final Tag VALID_TAG_2 = new Tag("craft");
    private Set<Tag> tags = new HashSet<>();

    @BeforeEach
    public void setUp() {
        tags = new HashSet<>();
        tags.add(VALID_TAG_1);
        tags.add(VALID_TAG_2);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Revenue(null, null, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Revenue(
            new Description(INVALID_DESCRIPTION_STRING), VALID_AMOUNT, tags));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
            () -> new Revenue(VALID_DESCRIPTION, new Amount(INVALID_AMOUNT_STRING), tags));
    }

    @Test
    public void equals() {
        Revenue validRevenue = new Revenue(VALID_DESCRIPTION, VALID_AMOUNT, tags);
        Revenue validRevenueCopy = new Revenue(VALID_DESCRIPTION, VALID_AMOUNT, tags);

        // same values -> returns true
        assertEquals(validRevenue, validRevenueCopy);

        // different types -> return false
        assertNotEquals(1, validRevenue);

        // null -> returns false
        assertNotEquals(null, validRevenue);
    }

}
