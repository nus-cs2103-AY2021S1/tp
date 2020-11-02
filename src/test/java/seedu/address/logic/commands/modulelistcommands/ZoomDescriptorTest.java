package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ZoomDescriptorBuilder;

public class ZoomDescriptorTest {

    @Test
    public void equals() {

        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULELESSONTYPE).withZoomLink(VALID_ZOOMLINK_CS2030).build();

        // same descriptor values -> returns true
        ZoomDescriptor descriptorWithSameValues = new ZoomDescriptor(descriptor);
        assertTrue(descriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        assertFalse(descriptor.equals(null));

        // different types -> returns false
        assertFalse(descriptor.equals(5));

        // different module lesson -> returns false
        ZoomDescriptor editedDescriptor = new ZoomDescriptorBuilder(descriptor)
                .withModuleLesson(VALID_MODULELESSONTYPE_ES2660).build();
        assertFalse(descriptor.equals(editedDescriptor));

        // different zoom link -> returns false
        editedDescriptor = new ZoomDescriptorBuilder(descriptor)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).build();
        assertFalse(descriptor.equals(editedDescriptor));
    }

}
