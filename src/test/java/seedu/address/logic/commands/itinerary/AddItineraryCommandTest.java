package seedu.address.logic.commands.itinerary;

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
import seedu.address.model.ItineraryList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.testutil.ItineraryBuilder;

class AddItineraryCommandTest {
    @Test
    public void constructor_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItineraryCommand(null));
    }

    @Test
    public void execute_itineraryAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItineraryAdded modelStub = new ModelStubAcceptingItineraryAdded();
        Itinerary validItinerary = new ItineraryBuilder().build();

        CommandResult commandResult = new AddItineraryCommand(validItinerary).execute(modelStub);

        assertEquals(String.format(AddItineraryCommand.MESSAGE_SUCCESS, validItinerary),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItinerary), modelStub.itinerariesAdded);
    }

    @Test
    public void execute_duplicateItinerary_throwsCommandException() {
        Itinerary validItinerary = new ItineraryBuilder().build();
        AddItineraryCommand addItineraryCommand = new AddItineraryCommand(validItinerary);
        ModelStub modelStub = new ModelStubWithItinerary(validItinerary);

        assertThrows(CommandException.class,
                AddItineraryCommand.MESSAGE_DUPLICATE_ITINERARY, () -> addItineraryCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Itinerary singaporeZoos = new ItineraryBuilder().withName("Singapore Zoos").build();
        Itinerary parisTrip = new ItineraryBuilder().withName("Paris Trip").build();
        AddItineraryCommand addSingaporeZoosCommand = new AddItineraryCommand(singaporeZoos);
        AddItineraryCommand addParisTripCommand = new AddItineraryCommand(parisTrip);

        // same object -> returns true
        assertTrue(addSingaporeZoosCommand.equals(addSingaporeZoosCommand));

        // same values -> returns true
        AddItineraryCommand addSingaporeZooCommandCopy = new AddItineraryCommand(singaporeZoos);
        assertTrue(addSingaporeZoosCommand.equals(addSingaporeZooCommandCopy));

        // different types -> returns false
        assertFalse(addSingaporeZoosCommand.equals(1));

        // null -> returns false
        assertFalse(addSingaporeZoosCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(addSingaporeZoosCommand.equals(addParisTripCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
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
     * A Model stub that contains a single itinerary.
     */
    private class ModelStubWithItinerary extends AddItineraryCommandTest.ModelStub {
        private final Itinerary itinerary;

        ModelStubWithItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            this.itinerary = itinerary;
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            return this.itinerary.isSameItinerary(itinerary);
        }
    }

    /**
     * A Model stub that always accept the itinerary being added.
     */
    private class ModelStubAcceptingItineraryAdded extends AddItineraryCommandTest.ModelStub {
        final ArrayList<Itinerary> itinerariesAdded = new ArrayList<>();

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            return itinerariesAdded.stream().anyMatch(itinerary::isSameItinerary);
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            itinerariesAdded.add(itinerary);
        }

        @Override
        public ReadOnlyItineraryList getItineraryList() {
            return new ItineraryList();
        }
    }
}
