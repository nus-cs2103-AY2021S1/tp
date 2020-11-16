package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalConsults.getTypicalTasker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.consultation.Consultation;
import seedu.address.testutil.ConsultationBuilder;


public class AddConsultIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTasker(), new UserPrefs());
    }

    @Test
    public void execute_newConsultation_success() {
        Consultation validConsultation = new ConsultationBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addConsultation(validConsultation);

        assertCommandSuccess(new AddConsultCommand(validConsultation), model,
                String.format(AddConsultCommand.MESSAGE_SUCCESS, validConsultation), expectedModel);
    }

    @Test
    public void execute_duplicateConsultation_throwsCommandException() {
        Consultation consultation = model.getAddressBook().getConsultationList().get(0);
        assertCommandFailure(new AddConsultCommand(consultation), model,
                AddConsultCommand.MESSAGE_DUPLICATE_CONSULTATION);
    }
}
