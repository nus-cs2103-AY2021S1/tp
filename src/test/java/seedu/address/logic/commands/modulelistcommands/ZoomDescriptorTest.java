package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LESSON_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ZOOM_LINK_CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ZoomDescriptorBuilder;

public class ZoomDescriptorTest {

    @Test
    public void equals() {

        ZoomDescriptor descriptor = new ZoomDescriptorBuilder()
                .withModuleLesson(VALID_MODULE_LESSON_LECTURE).withZoomLink(VALID_ZOOM_LINK_CS2030).build();

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
                .withModuleLesson(VALID_MODULE_LESSON_TUTORIAL).build();
        assertFalse(descriptor.equals(editedDescriptor));

        // different zoom link -> returns false
        editedDescriptor = new ZoomDescriptorBuilder(descriptor)
                .withZoomLink(VALID_ZOOM_LINK_CS2103T).build();
        assertFalse(descriptor.equals(editedDescriptor));
    }

}
