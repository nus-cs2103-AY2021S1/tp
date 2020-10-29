// EditDescriptorTest.java

package chopchop.logic.edit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditDescriptorTest {

    @Test
    void test_descriptors() {
        assertEquals("add", EditOperationType.ADD.toString());
        assertEquals("edit", EditOperationType.EDIT.toString());
        assertEquals("delete", EditOperationType.DELETE.toString());
    }
}
