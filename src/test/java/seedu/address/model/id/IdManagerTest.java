package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class IdManagerTest {

    @Test
    public void getNextId() {

        // using constructor with an initial id
        String prefix = "p";
        int previousIdNumber = 1024;
        Id previousId = new Id(prefix, previousIdNumber);
        IdManager idManager = new IdManager(previousId);
        IntStream nextIds = IntStream.iterate(previousIdNumber + 1, idNumber -> idNumber + 1);
        nextIds.limit(5).forEach(idNumber -> assertEquals(new Id(prefix, idNumber), idManager.getNextId()));

        // using factory method initialize
        IdManager idManager2 = IdManager.initialize(prefix);
        IntStream nextIds2 = IntStream.iterate(1, idNumber -> idNumber + 1);
        nextIds2.limit(5).forEach(idNumber -> assertEquals(new Id(prefix, idNumber), idManager2.getNextId()));
    }
}
