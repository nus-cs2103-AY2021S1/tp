package seedu.address.logic.commands.modulelistcommands;

//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;

//import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
//import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleCommand.EditModuleDescriptor descriptorWithSameValues = new EditModuleCommand
                .EditModuleDescriptor(DESC_CS2030);
        assertTrue(DESC_CS2030.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2030.equals(DESC_CS2030));

        // null -> returns false
        assertFalse(DESC_CS2030.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2030.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2030.equals(DESC_CS2103T));

        // different name -> returns false
        EditModuleCommand.EditModuleDescriptor editedCS2030 = new EditModuleDescriptorBuilder(DESC_CS2030)
                    .withName(VALID_MODULENAME_CS2103T).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different zoom link -> returns false
        editedCS2030 = new EditModuleDescriptorBuilder(DESC_CS2030)
                .withZoomLink(VALID_ZOOMLINK_CS2103T).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));

        // different tags -> returns false
        editedCS2030 = new EditModuleDescriptorBuilder(DESC_CS2030).withTags(VALID_TAG_UNGRADED_MODULE).build();
        assertFalse(DESC_CS2030.equals(editedCS2030));
    }
}
