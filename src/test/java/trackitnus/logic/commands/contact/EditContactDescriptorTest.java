package trackitnus.logic.commands.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import trackitnus.logic.commands.contact.EditContactCommand.EditContactDescriptor;
import trackitnus.testutil.builder.EditContactDescriptorBuilder;

public class EditContactDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditContactDescriptor descriptorWithSameValues = new EditContactDescriptor(ContactCommandTestUtil.DESC_AMY);
        assertTrue(ContactCommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(ContactCommandTestUtil.DESC_AMY.equals(ContactCommandTestUtil.DESC_AMY));

        // null -> returns false
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(ContactCommandTestUtil.DESC_BOB));

        // different name -> returns false
        EditContactDescriptor editedAmy =
            new EditContactDescriptorBuilder(ContactCommandTestUtil.DESC_AMY)
                .withName(ContactCommandTestUtil.VALID_NAME_BOB).build();
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy =
            new EditContactDescriptorBuilder(ContactCommandTestUtil.DESC_AMY)
                .withPhone(ContactCommandTestUtil.VALID_PHONE_BOB).build();
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy =
            new EditContactDescriptorBuilder(ContactCommandTestUtil.DESC_AMY)
                .withEmail(ContactCommandTestUtil.VALID_EMAIL_BOB).build();
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy =
            new EditContactDescriptorBuilder(ContactCommandTestUtil.DESC_AMY)
                .withTags(ContactCommandTestUtil.VALID_TAG_HUSBAND).build();
        assertFalse(ContactCommandTestUtil.DESC_AMY.equals(editedAmy));
    }
}
