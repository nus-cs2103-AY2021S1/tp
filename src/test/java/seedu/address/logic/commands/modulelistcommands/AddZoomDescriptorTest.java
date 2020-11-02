package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULELESSONTYPE_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOMLINK_CS2103T;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.AddZoomDescriptorBuilder;

public class AddZoomDescriptorTest {

    @Test
    public void equals() {

        AddZoomDescriptor descriptor = new AddZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULELESSONTYPE).withZoomLink(VALID_ZOOMLINK_CS2030).build();

        // same descriptor values -> returns true
        AddZoomDescriptor descriptorWithSameValues = new AddZoomDescriptor(descriptor);
        assertTrue(descriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        assertFalse(descriptor.equals(null));

        // different types -> returns false
        assertFalse(descriptor.equals(5));

        // different module lesson -> returns false
        AddZoomDescriptor editedDescriptor =  new AddZoomDescriptorBuilder(descriptor)
                .withModuleLesson(VALID_MODULELESSONTYPE_ES2660).build();
        assertFalse(descriptor.equals(editedDescriptor));

        // different zoom link -> returns false
        editedDescriptor = new AddZoomDescriptorBuilder(descriptor)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).build();
        assertFalse(descriptor.equals(editedDescriptor));
    }

}
