package seedu.resireg.model.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ALIAS_STUDENTS_ST;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_COMMAND_STUDENTS_ST;
import static seedu.resireg.testutil.TypicalCommandWordAliases.ROOMS_R;
import static seedu.resireg.testutil.TypicalCommandWordAliases.STUDENTS_ST;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.CommandWordAliasBuilder;

public class CommandWordAliasTest {

    @Test
    public void equals() {
        // same values -> returns true
        CommandWordAlias roomsCopy = new CommandWordAliasBuilder(ROOMS_R).build();
        assertTrue(ROOMS_R.equals(roomsCopy));

        // same object -> returns true
        assertTrue(ROOMS_R.equals(ROOMS_R));

        // null -> returns false
        assertFalse(ROOMS_R.equals(null));

        // different type -> returns false
        assertFalse(ROOMS_R.equals(5));

        // different student -> returns false
        assertFalse(ROOMS_R.equals(STUDENTS_ST));

        // different command word -> returns false
        CommandWordAlias editedRoomAlias = new CommandWordAliasBuilder(ROOMS_R)
            .withCommandWord(VALID_COMMAND_STUDENTS_ST).build();
        assertFalse(ROOMS_R.equals(editedRoomAlias));

        // different alias -> returns false
        editedRoomAlias = new CommandWordAliasBuilder(ROOMS_R).withAlias(VALID_ALIAS_STUDENTS_ST).build();
        assertFalse(ROOMS_R.equals(editedRoomAlias));

    }
}
