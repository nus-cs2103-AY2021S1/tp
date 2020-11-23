package seedu.address.logic.commands.hairdresser;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.HairStyleX;
import seedu.address.model.ReadOnlyHairStyleX;
import seedu.address.model.person.Person;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.testutil.HairdresserBuilder;

public class AddHairdresserCommandTest {

    @Test
    public void constructor_nullHairdresser_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddHairdresserCommand(null));
    }

    @Test
    public void execute_hairdresserAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingHairdresserAdded modelStub = new ModelStubAcceptingHairdresserAdded();
        Hairdresser validHairdresser = new HairdresserBuilder().build();

        CommandResult commandResult = new AddHairdresserCommand(validHairdresser).execute(modelStub);

        assertEquals(String.format(AddHairdresserCommand.MESSAGE_SUCCESS, validHairdresser),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validHairdresser), modelStub.hairdressersAdded);
    }

    @Test
    public void execute_duplicateHairdresser_throwsCommandException() {
        Hairdresser validHairdresser = new HairdresserBuilder().build();
        AddHairdresserCommand addCommand = new AddHairdresserCommand(validHairdresser);
        ModelStub modelStub = new ModelStubWithHairdresser(validHairdresser);

        assertThrows(CommandException.class, AddHairdresserCommand.MESSAGE_DUPLICATE_HAIRDRESSER, ()
            -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Hairdresser alice = new HairdresserBuilder().withName("Alice").build();
        Hairdresser bob = new HairdresserBuilder().withName("Bob").build();
        AddHairdresserCommand addAliceCommand = new AddHairdresserCommand(alice);
        AddHairdresserCommand addBobCommand = new AddHairdresserCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddHairdresserCommand addAliceCommandCopy = new AddHairdresserCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different hairdresser -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }



    /**
     * A Model stub that contains a single hairdresser.
     */
    private class ModelStubWithHairdresser extends ModelStub {
        private final Hairdresser hairdresser;

        ModelStubWithHairdresser(Hairdresser hairdresser) {
            requireNonNull(hairdresser);
            this.hairdresser = hairdresser;
        }

        @Override
        public boolean hasHairdresser(Hairdresser hairdresser) {
            requireNonNull(hairdresser);
            return this.hairdresser.isSame(hairdresser);
        }
    }

    /**
     * A Model stub that always accept the hairdresser being added.
     */
    private class ModelStubAcceptingHairdresserAdded extends ModelStub {
        final ArrayList<Hairdresser> hairdressersAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return hairdressersAdded.stream().anyMatch(person::isSame);
        }

        @Override
        public boolean hasHairdresser(Hairdresser hairdresser) {
            requireNonNull(hairdresser);
            return hairdressersAdded.stream().anyMatch(hairdresser::isSame);
        }

        @Override
        public void addHairdresser(Hairdresser hairdresser) {
            requireNonNull(hairdresser);
            hairdressersAdded.add(hairdresser);
        }

        @Override
        public ReadOnlyHairStyleX getHairStyleX() {
            return new HairStyleX();
        }
    }

}
