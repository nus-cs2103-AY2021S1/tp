package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.storage.Storage;
import seedu.resireg.testutil.CommandWordAliasBuilder;
import seedu.resireg.testutil.ModelStub;

public class AddAliasCommandTest {

    private CommandHistory history = new CommandHistory();

    @Test
    public void constructor_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAliasCommand(null));
    }

    @Test
    public void execute_aliasAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAliasAdded modelStub = new ModelStubAcceptingAliasAdded();
        CommandWordAlias validAlias = new CommandWordAliasBuilder().build();
        Storage storageStub = null;

        CommandResult commandResult = new AddAliasCommand(validAlias).execute(modelStub, storageStub, history);

        assertEquals(String.format(AddAliasCommand.MESSAGE_SUCCESS, validAlias), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAlias), modelStub.aliasesAdded);
    }

    @Test
    public void execute_duplicateAlias_throwsCommandException() {
        CommandWordAlias validAlias = new CommandWordAliasBuilder().build();
        AddAliasCommand addAliasCommand = new AddAliasCommand(validAlias);
        ModelStub modelStub = new ModelStubWithAlias(validAlias);
        Storage storageStub = null;

        assertThrows(CommandException.class,
            AddAliasCommand.MESSAGE_DUPLICATE_ALIAS, () -> addAliasCommand.execute(modelStub, storageStub, history));
    }

    @Test
    public void equals() {
        CommandWordAlias roomsR = new CommandWordAliasBuilder().withAlias("r").build();
        CommandWordAlias roomsRo = new CommandWordAliasBuilder().withAlias("ro").build();
        AddAliasCommand addRoomsRCommand = new AddAliasCommand(roomsR);
        AddAliasCommand addRoomsRoCommand = new AddAliasCommand(roomsRo);

        // same object -> returns true
        assertTrue(addRoomsRCommand.equals(addRoomsRCommand));

        // same values -> returns true
        AddAliasCommand addRoomsRCommandCopy = new AddAliasCommand(roomsR);
        assertTrue(addRoomsRCommand.equals(addRoomsRCommandCopy));

        // different types -> returns false
        assertFalse(addRoomsRCommandCopy.equals(1));

        // null -> returns false
        assertFalse(addRoomsRCommandCopy.equals(null));

        // different command -> returns false
        assertFalse(addRoomsRCommand.equals(addRoomsRoCommand));
    }

    /**
     * A Model stub that contains a single alias.
     */
    private class ModelStubWithAlias extends ModelStub {
        private final CommandWordAlias commandWordAlias;

        ModelStubWithAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            this.commandWordAlias = commandWordAlias;
        }

        @Override
        public boolean hasCommandWordAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            return this.commandWordAlias.getAlias().equals(commandWordAlias.getAlias());
        }
    }

    /**
     * A Model stub that always accept the alias being added.
     */
    private class ModelStubAcceptingAliasAdded extends ModelStub {
        final ArrayList<CommandWordAlias> aliasesAdded = new ArrayList<>();

        @Override
        public boolean hasCommandWordAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            return aliasesAdded.stream().anyMatch(commandWordAlias::equals);
        }

        @Override
        public void addCommandWordAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            aliasesAdded.add(commandWordAlias);
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return new UserPrefs();
        }
    }

}
