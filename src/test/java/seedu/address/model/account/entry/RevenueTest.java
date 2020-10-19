package seedu.address.model.account.entry;

import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class RevenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Revenue(null, null, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Amount amount = new Amount("15.00");
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("craft"));
        tags.add(new Tag("art"));

        assertThrows(IllegalArgumentException.class, () -> new Revenue(
                new Description(invalidDescription), amount, tags));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        Description description = new Description("bought craft supplies");
        String amount = "";
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("craft"));
        tags.add(new Tag("art"));

        assertThrows(IllegalArgumentException.class, () -> new Revenue(description, new Amount(amount), tags));
    }

}
