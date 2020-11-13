package seedu.address.logic.commands.deliverable;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.deliverable.DeliverableBook;
import seedu.address.model.deliverable.ModelDeliverable;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;
import seedu.address.model.deliverable.deliverable.Deliverable;
import seedu.address.testutil.DeliverableBuilder;


class AddCommandTest {

    @Test
    public void constructor_nullDeliverable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_deliverableAcceptedByModel_addSuccessful() throws Exception {
        ModelDeliverableStubAcceptingDeliverableAdded modelStub = new ModelDeliverableStubAcceptingDeliverableAdded();
        Deliverable validDeliverable = new DeliverableBuilder().build();

        CommandResult commandResult = new AddCommand(validDeliverable).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validDeliverable), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDeliverable), modelStub.deliverablesAdded);
    }

    @Test
    public void execute_duplicateDeliverable_throwsCommandException() {
        Deliverable validDeliverable = new DeliverableBuilder().build();
        AddCommand addCommand = new AddCommand(validDeliverable);
        ModelDeliverable modelStub = new ModelDeliverableStubWithDeliverable(validDeliverable);

        assertThrows(
                CommandException.class, AddCommand.MESSAGE_DUPLICATE_DELIVERABLE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Deliverable testA = new DeliverableBuilder().withTitle("TestA").build();
        Deliverable testB = new DeliverableBuilder().withTitle("TestB").build();
        AddCommand addTestACommand = new AddCommand(testA);
        AddCommand addTestBCommand = new AddCommand(testB);

        // same object -> returns true
        assertTrue(addTestACommand.equals(addTestACommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(testA);
        assertTrue(addTestACommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addTestACommand.equals(1));

        // null -> returns false
        assertFalse(addTestACommand.equals(null));

        // different deliverable -> returns false
        assertFalse(addTestACommand.equals(addTestBCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelDeliverableStub implements ModelDeliverable {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getDeliverableBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliverableBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeliverable(Deliverable deliverable) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliverableBook(ReadOnlyDeliverableBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyDeliverableBook getDeliverableBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeliverable(Deliverable deliverable) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeliverable(Deliverable target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliverable(Deliverable target, Deliverable editedDeliverable) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateDeliverableStatus(Deliverable target, Deliverable completedDeliverable) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Deliverable getDeliverableInView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliverableInView(Deliverable deliverableInView) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<Deliverable> getFilteredDeliverableList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deliverable> getInternalDeliverableList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDeliverableList(Predicate<Deliverable> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single deliverable.
     */
    private class ModelDeliverableStubWithDeliverable extends ModelDeliverableStub {
        private final Deliverable deliverable;

        ModelDeliverableStubWithDeliverable(Deliverable deliverable) {
            requireNonNull(deliverable);
            this.deliverable = deliverable;
        }

        @Override
        public boolean hasDeliverable(Deliverable deliverable) {
            requireNonNull(deliverable);
            return this.deliverable.isSameDeliverable(deliverable);
        }
    }
    /**
     * A Model stub that always accept the deliverable being added.
     */
    private class ModelDeliverableStubAcceptingDeliverableAdded extends ModelDeliverableStub {
        final ArrayList<Deliverable> deliverablesAdded = new ArrayList<>();

        @Override
        public boolean hasDeliverable(Deliverable deliverable) {
            requireNonNull(deliverable);
            return deliverablesAdded.stream().anyMatch(deliverable::isSameDeliverable);
        }

        @Override
        public void addDeliverable(Deliverable deliverable) {
            requireNonNull(deliverable);
            deliverablesAdded.add(deliverable);
        }

        @Override
        public ReadOnlyDeliverableBook getDeliverableBook() {
            return new DeliverableBook();
        }
    }
}
