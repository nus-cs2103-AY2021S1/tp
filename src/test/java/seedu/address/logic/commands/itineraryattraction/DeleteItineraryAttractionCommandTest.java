package seedu.address.logic.commands.itineraryattraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITINERARY_DAY;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.getTypicalAttractionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.address.testutil.TypicalItineraries.getTypicalItineraryList;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.testutil.ItineraryAttractionBuilder;
import seedu.address.testutil.ItineraryBuilder;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteAttractionCommand}.
 */
public class DeleteItineraryAttractionCommandTest {

    @Test
    public void execute_validIndexValidDay_success() {
        Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        DeleteItineraryAttractionCommand delIaCommand = new DeleteItineraryAttractionCommand(INDEX_FIRST, INDEX_FIRST);
        ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().build();
        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(itineraryAttraction, INDEX_FIRST).build();
        model.setCurrentItinerary(itinerary);

        String expectedMessage = String.format(DeleteItineraryAttractionCommand.MESSAGE_DELETE_ATTRACTION_SUCCESS,
                itineraryAttraction);
        ModelManager expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(),
                new UserPrefs());
        Itinerary expectedItinerary = new ItineraryBuilder().withItineraryAttraction(
                new ItineraryAttractionBuilder().build(), INDEX_FIRST).build();
        expectedModel.setCurrentItinerary(expectedItinerary);
        expectedModel.getCurrentItinerary().deleteItineraryAttraction(INDEX_FIRST, INDEX_FIRST);

        assertCommandSuccess(delIaCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_commandFailure() {
        Model model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        DeleteItineraryAttractionCommand delIaCommand = new DeleteItineraryAttractionCommand(INDEX_SECOND, INDEX_FIRST);
        ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().build();
        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(itineraryAttraction, INDEX_FIRST).build();
        model.setCurrentItinerary(itinerary);

        String expectedMessage = Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX;
        ModelManager expectedModel = new ModelManager(model.getAttractionList(), model.getItineraryList(),
                new UserPrefs());
        Itinerary expectedItinerary = new ItineraryBuilder().withItineraryAttraction(
                new ItineraryAttractionBuilder().build(), INDEX_FIRST).build();
        expectedModel.setCurrentItinerary(expectedItinerary);
        expectedModel.getCurrentItinerary().deleteItineraryAttraction(INDEX_FIRST, INDEX_FIRST);
        assertCommandFailure(delIaCommand, model, expectedMessage);

        model = new ModelManager(getTypicalAttractionList(), getTypicalItineraryList(), new UserPrefs());
        delIaCommand = new DeleteItineraryAttractionCommand(INDEX_SECOND, INDEX_THIRD);
        itineraryAttraction = new ItineraryAttractionBuilder().build();
        itinerary = new ItineraryBuilder().withItineraryAttraction(itineraryAttraction, INDEX_FIRST).build();
        model.setCurrentItinerary(itinerary);
        expectedMessage = Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX;
        assertCommandFailure(delIaCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidAttractionIndex_throwsCommandException() {
        DeleteItineraryAttractionCommand delIaCommand = new DeleteItineraryAttractionCommand(INDEX_THIRD, INDEX_FIRST);
        ModelStubWithItinerary modelWithItinerary = new ModelStubWithItinerary(new ItineraryBuilder().build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, () ->
                delIaCommand.execute(modelWithItinerary));
    }

    @Test
    public void execute_invalidDay_throwsCommandException() {
        Itinerary itinerary = new ItineraryBuilder().build();
        ModelStubWithItinerary model = new ModelStubWithItinerary(itinerary);
        DeleteItineraryAttractionCommand delIaCommand = new DeleteItineraryAttractionCommand(INDEX_FIRST,
                Index.fromZeroBased(4));

        assertThrows(CommandException.class, MESSAGE_INVALID_ITINERARY_DAY, ()
            -> delIaCommand.execute(model));
    }

    @Test
    public void execute_itineraryNotSelected_throwsCommandException() {
        ModelStubNoItinerarySelected model = new ModelStubNoItinerarySelected();

        DeleteItineraryAttractionCommand delIaCommand = new DeleteItineraryAttractionCommand(INDEX_FIRST, INDEX_FIRST);
        assertThrows(CommandException.class, MESSAGE_ITINERARY_NOT_SELECTED, () -> delIaCommand.execute(model));
    }

    @Test
    public void equals() {
        DeleteItineraryAttractionCommand deleteFirstCommand;
        DeleteItineraryAttractionCommand deleteSecondCommand;
        deleteFirstCommand = new DeleteItineraryAttractionCommand(INDEX_FIRST, INDEX_THIRD);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // diff index -> return false
        deleteSecondCommand = new DeleteItineraryAttractionCommand(INDEX_SECOND, INDEX_THIRD);
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);

        // diff day visiting -> return false
        deleteFirstCommand = new DeleteItineraryAttractionCommand(INDEX_SECOND, INDEX_FIRST);
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);

        // different types -> returns false
        assertNotEquals(deleteFirstCommand, 1);

        // null -> returns false
        assertNotEquals(deleteFirstCommand, null);
    }

    /**
     * A default model stub that throws exception for all method calls.
     */
    private class ModelStub implements Model {
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
        public Path getAttractionListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttractionListFilePath(Path attractionListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getItineraryListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItineraryListFilePath(Path itineraryListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAttraction(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttractionList(ReadOnlyAttractionList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAttractionList getAttractionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAttraction(Attraction attraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAttraction(Attraction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAttraction(Attraction target, Attraction editedAttraction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markVisitedAttraction(Attraction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAttractionList(Predicate<Attraction> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItineraryList(ReadOnlyItineraryList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyItineraryList getItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItinerary(Itinerary target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItinerary(Itinerary target, Itinerary editedItinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Itinerary> getFilteredItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Itinerary getCurrentItinerary() {
            throw new AssertionError("This methods should not be called.");
        }

        @Override
        public ReadOnlyItineraryAttractionList getItineraryAttractionList() {
            throw new AssertionError("This methods should not be called.");
        }

        @Override
        public ObservableList<ItineraryAttraction> getFilteredItineraryAttractionList() {
            throw new AssertionError("This methods should not be called.");
        }
    }

    /**
     * A Model stub that contains a itinerary in itineraryList.
     */
    private class ModelStubWithItinerary extends ModelStub {
        private final Itinerary itinerary;

        public ModelStubWithItinerary(Itinerary itinerary) {
            this.itinerary = itinerary;
        }

        @Override
        public Itinerary getCurrentItinerary() {
            return this.itinerary;
        }
    }

    /**
     * A Model stub that returns no itinerary selected.
     */
    private class ModelStubNoItinerarySelected extends ModelStub {
        @Override
        public Itinerary getCurrentItinerary() {
            return null;
        }
    }

}
