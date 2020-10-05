package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IdManagerTest {

    @Test
    public void getNextId() {
        String prefix = "p";
        IdManager idManager = new IdManager(prefix);
        int[] idNumbers = {0, 1, 2, 3, 4, 5};
        for (int num : idNumbers) {
            assertEquals(new Id(prefix, num), idManager.getNextId());
        }
    }
}
