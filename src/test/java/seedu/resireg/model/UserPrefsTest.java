package seedu.resireg.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalCommandWordAliases.ROOMS_R;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.alias.exceptions.DuplicateCommandWordAliasException;
import seedu.resireg.testutil.CommandWordAliasBuilder;

public class UserPrefsTest {

    private final UserPrefs userPrefs = new UserPrefs();

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setResiRegFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setResiRegFilePath(null));
    }

    @Test
    public void resetData_withDuplicateAliases_throwsDuplicateCommandWordAliasException() {
        CommandWordAlias editedAlias = new CommandWordAliasBuilder(ROOMS_R).build();
        List<CommandWordAlias> newAliases = Arrays.asList(ROOMS_R, editedAlias);
        UserPrefsStub newData = new UserPrefsStub(newAliases);

        assertThrows(DuplicateCommandWordAliasException.class, () -> userPrefs.resetData(newData));
    }

    @Test
    public void hasAlias_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> userPrefs.hasAlias(null));
    }

    @Test
    public void hasAlias_aliasNotInUserPrefs_returnsFalse() {
        assertFalse(userPrefs.hasAlias(ROOMS_R));
    }

    @Test
    public void hasAlias_aliasInUserPrefs_returnsTrue() {
        userPrefs.addAlias(ROOMS_R);
        assertTrue(userPrefs.hasAlias(ROOMS_R));
    }

    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class UserPrefsStub implements ReadOnlyUserPrefs {
        private GuiSettings guiSettings = new GuiSettings();
        private List<CommandWordAlias> commandWordAliases;
        private Path resiRegFilePath = Paths.get("data" , "resireg.json");

        UserPrefsStub(List<CommandWordAlias> commandWordAliases) {
            this.commandWordAliases = commandWordAliases;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return guiSettings;
        }

        @Override
        public List<CommandWordAlias> getCommandWordAliases() {
            return commandWordAliases;
        }

        @Override
        public Path getResiRegFilePath() {
            return resiRegFilePath;
        }

        @Override
        public int getDaysStoredInBin() {
            return 0;
        }
    }

}
