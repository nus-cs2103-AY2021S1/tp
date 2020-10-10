package seedu.address.model.account.entry;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Expense(null, null, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Amount amount = new Amount("1.00");
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Food"));
        tags.add(new Tag("Restaurant"));

        assertThrows(IllegalArgumentException.class, () -> new Expense(new Description(invalidDescription), amount, tags));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        Description description = new Description("dinner at genki sushi");
        String amount = "";
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Food"));
        tags.add(new Tag("Restaurant"));

        assertThrows(IllegalArgumentException.class, () -> new Expense(description, new Amount(amount), tags));
    }
}