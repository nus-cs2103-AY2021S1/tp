package seedu.jarvis.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.add.AddConsultationCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.testutil.ConsultationBuilder;
import seedu.jarvis.testutil.ModelStub;

public class AddConsultationCommandTest {

    @Test
    public void constructor_nullConsultation_throwsNullPointerException() {
        Consultation consultation = null;
        assertThrows(NullPointerException.class, () -> new AddConsultationCommand(consultation));
    }

    @Test
    public void execute_consultationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMasteryCheckAdded modelStub = new ModelStubAcceptingMasteryCheckAdded();
        Consultation validConsultation = new ConsultationBuilder().build();

        CommandResult commandResult = new AddConsultationCommand(validConsultation).execute(modelStub);

        assertEquals(String.format(AddConsultationCommand.MESSAGE_SUCCESS_CONSULTATION, validConsultation),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validConsultation), modelStub.consultationsAdded);
    }

    @Test
    public void execute_duplicateConsultation_throwsCommandException() {
        Consultation validConsultation = new ConsultationBuilder().build();
        AddConsultationCommand addCommand = new AddConsultationCommand(validConsultation);
        ModelStub modelStub = new ModelStubWithConsultation(validConsultation);

        assertThrows(CommandException.class,
                AddConsultationCommand.MESSAGE_DUPLICATE_CONSULTATION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_consultationAcceptedByModel_commandTargetFeatureAccurate() throws CommandException {
        ModelStubAcceptingMasteryCheckAdded modelStub = new ModelStubAcceptingMasteryCheckAdded();
        Consultation validConsultation = new ConsultationBuilder().build();

        CommandResult commandResult = new AddConsultationCommand(validConsultation).execute(modelStub);

        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.Consultations, actualTargetTab);
    }

    /**
     * A Model stub that contains a single consultation.
     */
    private class ModelStubWithConsultation extends ModelStub {
        private final Consultation consultation;

        ModelStubWithConsultation(Consultation consultation) {
            requireNonNull(consultation);
            this.consultation = consultation;
        }

        @Override
        public boolean hasConsultation(Consultation consultation) {
            requireNonNull(consultation);
            return this.consultation.isSameConsultation(consultation);
        }
    }

    /**
     * A Model stub that always accept the consultation being added.
     */
    private class ModelStubAcceptingMasteryCheckAdded extends ModelStub {
        final ArrayList<Consultation> consultationsAdded = new ArrayList<>();

        @Override
        public boolean hasConsultation(Consultation consultation) {
            requireNonNull(consultation);
            return consultationsAdded.stream().anyMatch(consultation::isSameConsultation);
        }

        @Override
        public void addConsultation(Consultation consultation) {
            requireNonNull(consultation);
            consultationsAdded.add(consultation);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
