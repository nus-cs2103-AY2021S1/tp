package seedu.address.logic.command.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.command.CommandTestUtil.VALID_COST_SKI;
import static seedu.address.logic.command.CommandTestUtil.VALID_LOCATION_HOME;
import static seedu.address.logic.command.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.friend.Friend;
import seedu.address.testutil.EditActivityDescriptorBuilder;

public class EditDescriptorTest {

    private EditDescriptor editDescriptor = new EditDescriptor();

    @Test
    void isAnyFieldEdited_withNewEditDescriptor_returnsFalse() {
        assertFalse(editDescriptor.isAnyFieldEdited());
    }

    @Test
    void isAnyFieldEdited_withEditedNames_returnsTrue() {
        EditDescriptor editDescriptor = new EditDescriptor();
        editDescriptor.setName(new Name(VALID_NAME_AMY));
        assertTrue(editDescriptor.isAnyFieldEdited());
    }

    @Test
    void buildFromSource_withNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> EditDescriptor.buildFromSource(null));
    }

    @Test
    void buildFromSource_validArgumentMultimap_returnsCorrectEditDescriptor() {
        String args = NAME_DESC_AMY;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        try {
            editDescriptor = EditDescriptor.buildFromSource(argMultimap);
        } catch (ParseException e) {
            assert false;
        }
        EditDescriptor compareDescriptor = new EditDescriptor();
        compareDescriptor.setName(new Name(VALID_NAME_AMY));

        assertEquals(editDescriptor, compareDescriptor);

    }

    @Test
    void buildFromSource_validArgumentMultimap_returnsModifiedEditDescriptor() {
        String args = NAME_DESC_AMY;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        try {
            editDescriptor = EditDescriptor.buildFromSource(argMultimap);
        } catch (ParseException e) {
            assert false;
        }
        EditDescriptor compareDescriptor = new EditDescriptor();

        assertFalse(compareDescriptor.equals(editDescriptor));

    }
    @Test
    public void wrongFieldEdited_validActivityField_false() {
        EditDescriptor editDescriptor = new EditActivityDescriptorBuilder();
        editDescriptor.setLocation(new Location(VALID_LOCATION_HOME));
        assertFalse(editDescriptor.wrongFieldEdited(Activity.TPO_WORD));
    }

    @Test
    public void wrongFieldEdited_invalidFriendField_true() {
        EditDescriptor editDescriptor = new EditActivityDescriptorBuilder();
        editDescriptor.setCost(new Cost(VALID_COST_SKI));
        assertTrue(editDescriptor.wrongFieldEdited(Friend.TPO_WORD));
    }
}
