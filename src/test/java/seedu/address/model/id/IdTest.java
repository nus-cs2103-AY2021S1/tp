package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class IdTest {

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
}
