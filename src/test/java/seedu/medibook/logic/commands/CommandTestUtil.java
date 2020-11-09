package seedu.medibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_MCR;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_TREATMENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static seedu.medibook.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;
import seedu.medibook.model.patient.FieldContainsKeywordsPredicate;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_IC_AMY = "S9753124K";
    public static final String VALID_IC_BOB = "T0102030G";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_DOB_AMY = "14-02-1997";
    public static final String VALID_DOB_BOB = "11-11-2001";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_HEIGHT_AMY = "172";
    public static final String VALID_HEIGHT_BOB = "166";
    public static final String VALID_WEIGHT_AMY = "49.9";
    public static final String VALID_WEIGHT_BOB = "65.0";
    public static final String VALID_BMI_AMY = "16.9";
    public static final String VALID_BMI_BOB = "23.6";
    public static final String VALID_BLOOD_TYPE_AMY = "A+";
    public static final String VALID_BLOOD_TYPE_BOB = "B+";
    public static final String VALID_ALLERGY_PENICILLIN = "Penicillin";
    public static final String VALID_ALLERGY_SHELLFISH = "shellfish (mild)";
    public static final String VALID_CONDITION_BACK = "back sprain";
    public static final String VALID_CONDITION_DIABETES = "Type 1 Diabetes (T1D)";
    public static final String VALID_TREATMENT_PHYSIOTHERAPY = "physiotherapy";
    public static final String VALID_TREATMENT_PARACETAMOL = "Paracetamol (given 20-11-2019)";
    public static final String VALID_NON_FUTURE_DATE = "15-10-2020";
    public static final String VALID_FUTURE_DATE = "15-10-2030";
    public static final String VALID_DOCTOR_NAME = "John Doe";
    public static final String VALID_DOCTOR_MCR = "M09192U";
    public static final String VALID_CONTENT = "Patient is sick.";

    public static final String IC_DESC_AMY = " " + PREFIX_IC + VALID_IC_AMY;
    public static final String IC_DESC_BOB = " " + PREFIX_IC + VALID_IC_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String DOB_DESC_AMY = " " + PREFIX_DATE + VALID_DOB_AMY;
    public static final String DOB_DESC_BOB = " " + PREFIX_DATE + VALID_DOB_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String HEIGHT_DESC_AMY = " " + PREFIX_HEIGHT + VALID_HEIGHT_AMY;
    public static final String HEIGHT_DESC_BOB = " " + PREFIX_HEIGHT + VALID_HEIGHT_BOB;
    public static final String WEIGHT_DESC_AMY = " " + PREFIX_WEIGHT + VALID_WEIGHT_AMY;
    public static final String WEIGHT_DESC_BOB = " " + PREFIX_WEIGHT + VALID_WEIGHT_BOB;
    public static final String BLOOD_TYPE_DESC_AMY = " " + PREFIX_BLOOD_TYPE + VALID_BLOOD_TYPE_AMY;
    public static final String BLOOD_TYPE_DESC_BOB = " " + PREFIX_BLOOD_TYPE + VALID_BLOOD_TYPE_BOB;
    public static final String ALLERGY_DESC_PENICILLIN = " " + PREFIX_ALLERGY + VALID_ALLERGY_PENICILLIN;
    public static final String ALLERGY_DESC_SHELLFISH = " " + PREFIX_ALLERGY + VALID_ALLERGY_SHELLFISH;
    public static final String CONDITION_DESC_BACK = " " + PREFIX_CONDITION + VALID_CONDITION_BACK;
    public static final String CONDITION_DESC_DIABETES = " " + PREFIX_CONDITION + VALID_CONDITION_DIABETES;
    public static final String TREATMENT_DESC_PARACETAMOL = " " + PREFIX_TREATMENT + VALID_TREATMENT_PARACETAMOL;
    public static final String TREATMENT_DESC_PHYSIOTHERAPY = " " + PREFIX_TREATMENT + VALID_TREATMENT_PHYSIOTHERAPY;
    public static final String NOTE_NON_FUTURE_DATE_DESC = " " + PREFIX_DATE + VALID_NON_FUTURE_DATE;
    public static final String NOTE_FUTURE_DATE_DESC = " " + PREFIX_DATE + VALID_FUTURE_DATE;
    public static final String NOTE_DOCTOR_NAME_DESC = " " + PREFIX_NAME + VALID_DOCTOR_NAME;
    public static final String NOTE_DOCTOR_MCR_DESC = " " + PREFIX_MCR + VALID_DOCTOR_MCR;
    public static final String NOTE_CONTENT_DESC = " " + PREFIX_CONTENT + VALID_CONTENT;

    public static final String INVALID_IC_DESC = " " + PREFIX_IC + "A222223HH";
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_DOB_DESC = " " + PREFIX_DATE + "31/12/95"; // '-' should be used instead of '/'
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_HEIGHT_DESC = " " + PREFIX_HEIGHT + "17o"; // 'o' not allowed in height
    // 2 decimal places is not allowed in weight
    public static final String INVALID_WEIGHT_DESC = " " + PREFIX_WEIGHT + "55.55";
    public static final String INVALID_BLOOD_TYPE_DESC = " " + PREFIX_BLOOD_TYPE + "C+"; // 'C+' invalid blood type
    // medical tags not allowed to have only non-alphanumeric characters
    public static final String INVALID_ALLERGY_DESC = " " + PREFIX_ALLERGY + "->.";
    public static final String INVALID_CONDITION_DESC = " " + PREFIX_CONDITION + "/@!";
    public static final String INVALID_TREATMENT_DESC = " " + PREFIX_TREATMENT + "[/*";
    public static final String INVALID_NOTE_DATE_DESC = " " + PREFIX_DATE + "2-3-2020"; // incorrect date format
    // '*' not allowed in name
    public static final String INVALID_NOTE_DOCTOR_NAME_DESC = " " + PREFIX_NAME + "A* doctor";
    public static final String INVALID_NOTE_DOCTOR_MCR_DESC = " " + PREFIX_MCR + "Z82730P"; // incorrect mcr format
    public static final String INVALID_NOTE_CONTENT_DESC = " " + PREFIX_CONTENT + ""; // content cannot be empty

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPatientDescriptor DESC_AMY;
    public static final EditCommand.EditPatientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withIc(VALID_IC_AMY).withName(VALID_NAME_AMY)
                .withDateOfBirth(VALID_DOB_AMY).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withTreatments(VALID_TREATMENT_PARACETAMOL).withHeight(VALID_HEIGHT_AMY)
                .withWeight(VALID_WEIGHT_AMY).withBloodType(VALID_BLOOD_TYPE_AMY)
                .withAllergies(VALID_ALLERGY_PENICILLIN).withConditions(VALID_CONDITION_DIABETES).build();
        DESC_BOB = new EditPatientDescriptorBuilder().withIc(VALID_IC_BOB).withName(VALID_NAME_BOB)
                .withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
                .withBloodType(VALID_BLOOD_TYPE_BOB).withAllergies(VALID_ALLERGY_PENICILLIN, VALID_ALLERGY_SHELLFISH)
                .withConditions(VALID_CONDITION_BACK, VALID_CONDITION_DIABETES)
                .withTreatments(VALID_TREATMENT_PHYSIOTHERAPY, VALID_TREATMENT_PARACETAMOL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the medi book, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MediBook expectedMediBook = new MediBook(actualModel.getMediBook());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMediBook, actualModel.getMediBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s medi book.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new FieldContainsKeywordsPredicate(Arrays.asList(splitName[0]), PREFIX_NAME));

        assertEquals(1, model.getFilteredPatientList().size());
    }

}
