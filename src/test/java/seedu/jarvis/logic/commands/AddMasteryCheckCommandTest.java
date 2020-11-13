package seedu.jarvis.logic.commands;


import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jarvis.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.jarvis.logic.commands.add.AddMasteryCheckCommand;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.testutil.MasteryCheckBuilder;
import seedu.jarvis.testutil.ModelStub;

public class AddMasteryCheckCommandTest {

    @Test
    public void constructor_nullMasteryCheck_throwsNullPointerException() {
        MasteryCheck masteryCheck = null;
        assertThrows(NullPointerException.class, () -> new AddMasteryCheckCommand(masteryCheck));
    }

    @Test
    public void execute_masteryCheckAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingMasteryCheckAdded modelStub = new ModelStubAcceptingMasteryCheckAdded();
        MasteryCheck validMasteryCheck = new MasteryCheckBuilder().build();

        CommandResult commandResult = new AddMasteryCheckCommand(validMasteryCheck).execute(modelStub);

        assertEquals(String.format(AddMasteryCheckCommand.MESSAGE_SUCCESS_MASTERY_CHECK, validMasteryCheck),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMasteryCheck), modelStub.masteryChecksAdded);
    }

    @Test
    public void execute_duplicateMasteryCheck_throwsCommandException() {
        MasteryCheck validMasteryCheck = new MasteryCheckBuilder().build();
        AddMasteryCheckCommand addCommand = new AddMasteryCheckCommand(validMasteryCheck);
        ModelStub modelStub = new ModelStubWithMasteryCheck(validMasteryCheck);

        assertThrows(CommandException.class,
                AddMasteryCheckCommand.MESSAGE_DUPLICATE_MASTERY_CHECK, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_masteryCheckAcceptedByModel_commandTargetFeatureAccurate() throws CommandException {
        ModelStubAcceptingMasteryCheckAdded modelStub = new ModelStubAcceptingMasteryCheckAdded();
        MasteryCheck validMasteryCheck = new MasteryCheckBuilder().build();

        CommandResult commandResult = new AddMasteryCheckCommand(validMasteryCheck).execute(modelStub);

        CommandTargetFeature actualTargetTab = commandResult.getCommandTargetFeature();

        assertEquals(CommandTargetFeature.MasteryCheck, actualTargetTab);
    }

    /**
     * A Model stub that contains a single mastery check.
     */
    private class ModelStubWithMasteryCheck extends ModelStub {
        private final MasteryCheck masteryCheck;

        ModelStubWithMasteryCheck(MasteryCheck masteryCheck) {
            requireNonNull(masteryCheck);
            this.masteryCheck = masteryCheck;
        }

        @Override
        public boolean hasMasteryCheck(MasteryCheck masteryCheck) {
            requireNonNull(masteryCheck);
            return this.masteryCheck.isSameConsultation(masteryCheck);
        }
    }

    /**
     * A Model stub that always accept the mastery check being added.
     */
    private class ModelStubAcceptingMasteryCheckAdded extends ModelStub {
        final ArrayList<MasteryCheck> masteryChecksAdded = new ArrayList<>();

        @Override
        public boolean hasMasteryCheck(MasteryCheck masteryCheck) {
            requireNonNull(masteryCheck);
            return masteryChecksAdded.stream().anyMatch(masteryCheck::isSameConsultation);
        }

        @Override
        public void addMasteryCheck(MasteryCheck masteryCheck) {
            requireNonNull(masteryCheck);
            masteryChecksAdded.add(masteryCheck);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
