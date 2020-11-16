package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.stubs.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.Tasker;
import seedu.address.model.consultation.Consultation;
import seedu.address.testutil.ConsultationBuilder;

public class AddConsultCommandTest {
    @Test
    public void constructor_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddConsultCommand(null));
    }

    @Test
    public void execute_consultationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingConsultAdded modelStub = new ModelStubAcceptingConsultAdded();
        Consultation validConsult = new ConsultationBuilder().build();

        CommandResult commandResult = new AddConsultCommand(validConsult).execute(modelStub);

        assertEquals(String.format(AddConsultCommand.MESSAGE_SUCCESS, validConsult), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validConsult), modelStub.addedConsults);
    }

    @Test
    public void execute_duplicateConsultation_throwsCommandException() {
        Consultation validConsult = new ConsultationBuilder().build();
        AddConsultCommand addCommand = new AddConsultCommand(validConsult);
        ModelStub modelStub = new AddConsultCommandTest.ModelStubWithConsultation(validConsult);

        assertThrows(CommandException.class,
                AddConsultCommand.MESSAGE_DUPLICATE_CONSULTATION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Consultation consultFromAlice = new ConsultationBuilder().withName("Alice").build();
        Consultation consultFromBob = new ConsultationBuilder().withName("Bob").build();
        AddConsultCommand addAliceCommand = new AddConsultCommand(consultFromAlice);
        AddConsultCommand addBobCommand = new AddConsultCommand(consultFromBob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddConsultCommand addAliceCommandCopy = new AddConsultCommand(consultFromAlice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different consultation -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single consultation.
     */
    private class ModelStubWithConsultation extends ModelStub {
        private final Consultation consultation;

        ModelStubWithConsultation(Consultation consult) {
            requireNonNull(consult);
            consultation = consult;
        }

        @Override
        public boolean hasConsult(Consultation c) {
            requireNonNull(c);
            return consultation.isSameConsultation(c);
        }
    }

    /**
     * A Model stub that always accept the consultation being added.
     */
    private class ModelStubAcceptingConsultAdded extends ModelStub {
        final ArrayList<Consultation> addedConsults = new ArrayList<>();

        @Override
        public boolean hasConsult(Consultation consultation) {
            requireNonNull(consultation);
            return addedConsults.stream().anyMatch(consultation::isSameConsultation);
        }

        @Override
        public void addConsultation(Consultation consultation) {
            requireNonNull(consultation);
            addedConsults.add(consultation);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new Tasker();
        }
    }
}
