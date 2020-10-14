package seedu.fma.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class ArgumentMultimapTest {

    @Test
    void put_singleArgValue_doesNotThrowError() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertDoesNotThrow(() -> map.put(new Prefix("t/"), "jacky"));
    }

    @Test
    void put_multipleArgValues_doesNotThrowError() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertDoesNotThrow(() -> {
            map.put(new Prefix("t/"), "jacky");
            map.put(new Prefix("t/"), "johny");
            map.put(new Prefix("s/"), "johny");
        });
    }

    @Test
    void put_duplicateArgValues_doesNotThrowError() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertDoesNotThrow(() -> {
            map.put(new Prefix("t/"), "jacky");
            map.put(new Prefix("t/"), "jacky");
        });
    }

    @Test
    void getValue_noArgValues_returnEmpty() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertEquals(Optional.empty(), map.getValue(new Prefix("s/")));
    }

    @Test
    void getValue_hasArgValues_returnLastArgValue() {
        ArgumentMultimap map = new ArgumentMultimap();
        map.put(new Prefix("s/"), "Jimmy");
        map.put(new Prefix("s/"), "Picky");
        map.put(new Prefix("n/"), "Mustard");
        assertEquals(Optional.of("Picky"), map.getValue(new Prefix("s/")));
        assertEquals(Optional.of("Mustard"), map.getValue(new Prefix("n/")));
    }

    @Test
    void getAllValues_noArgValues_returnEmptyList() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertEquals(0, map.getAllValues(new Prefix("s/")).size());
    }

    @Test
    void getAllValues_hasArgValues_returnListOfArgValues() {
        ArgumentMultimap map = new ArgumentMultimap();
        map.put(new Prefix("s/"), "Jimmy");
        map.put(new Prefix("s/"), "Picky");
        List<String> argValues = map.getAllValues(new Prefix("s/"));
        assertEquals(2, argValues.size());
        assertEquals("Jimmy", argValues.get(0));
        assertEquals("Picky", argValues.get(1));
    }

    @Test
    void getPreamble_noPreamble_returnEmptyString() {
        ArgumentMultimap map = new ArgumentMultimap();
        assertEquals("", map.getPreamble());
    }

    @Test
    void getPreamble_hasPreamble_returnPreamble() {
        ArgumentMultimap map = new ArgumentMultimap();
        map.put(new Prefix(""), "Sun");
        assertEquals("Sun", map.getPreamble());
    }
}
