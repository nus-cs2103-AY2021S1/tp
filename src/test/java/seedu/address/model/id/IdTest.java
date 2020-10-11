package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Id(""));
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void increment() {
        String prefix = "P";
        int[] idNumbers = {0, 1, 2, 3, 4, 5};
        Stream<Id> idStream = Stream.iterate(new Id(prefix, 0), Id::increment);
        List<Id> idList = idStream.limit(6).collect(Collectors.toList());
        for (int i = 0; i < idNumbers.length; i++) {
            assertEquals(new Id(prefix, idNumbers[i]), idList.get(i));
        }
    }

    @Test
    public void isValidId() {

        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId("12345")); // only numbers
        assertFalse(Id.isValidId("P")); // no numbers
        assertFalse(Id.isValidId("P1B2S3")); // mixed numbers and letters
        assertFalse(Id.isValidId("p123")); // lower cased prefix letter
        assertFalse(Id.isValidId("M123")); // invalid prefix letter

        // valid id
        assertTrue(Id.isValidId("P12345"));
        assertTrue(Id.isValidId("B12345"));
        assertTrue(Id.isValidId("S12345"));
    }
}
