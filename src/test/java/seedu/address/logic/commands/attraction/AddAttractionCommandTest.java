package seedu.address.logic.commands.attraction;

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
import seedu.address.model.AttractionList;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.testutil.AttractionBuilder;

public class AddAttractionCommandTest {

    @Test
    public void constructor_nullAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAttractionCommand(null));
    }

    @Test
    public void execute_attractionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAttractionAdded modelStub = new ModelStubAcceptingAttractionAdded();
        Attraction validAttraction = new AttractionBuilder().build();

        CommandResult commandResult = new AddAttractionCommand(validAttraction).execute(modelStub);

        assertEquals(String.format(AddAttractionCommand.MESSAGE_SUCCESS, validAttraction),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAttraction), modelStub.attractionsAdded);
    }

    @Test
    public void execute_duplicateAttraction_throwsCommandException() {
        Attraction validAttraction = new AttractionBuilder().build();
        AddAttractionCommand addAttractionCommand = new AddAttractionCommand(validAttraction);
        ModelStub modelStub = new ModelStubWithAttraction(validAttraction);

        assertThrows(CommandException.class,
                AddAttractionCommand.MESSAGE_DUPLICATE_ATTRACTION, () -> addAttractionCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Attraction singaporeZoo = new AttractionBuilder().withName("Singapore Zoo").build();
        Attraction nightSafari = new AttractionBuilder().withName("Night Safari").build();
        AddAttractionCommand addSingaporeZooCommand = new AddAttractionCommand(singaporeZoo);
        AddAttractionCommand addNightSafariCommand = new AddAttractionCommand(nightSafari);

        // same object -> returns true
        assertTrue(addSingaporeZooCommand.equals(addSingaporeZooCommand));

        // same values -> returns true
        AddAttractionCommand addSingaporeZooCommandCopy = new AddAttractionCommand(singaporeZoo);
        assertTrue(addSingaporeZooCommand.equals(addSingaporeZooCommandCopy));

        // different types -> returns false
        assertFalse(addSingaporeZooCommand.equals(1));

        // null -> returns false
        assertFalse(addSingaporeZooCommand.equals(null));

        // different attraction -> returns false
        assertFalse(addSingaporeZooCommand.equals(addNightSafariCommand));
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
            throw new AssertionError("This method should not be called");
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
     * A Model stub that contains a single attraction.
     */
    private class ModelStubWithAttraction extends ModelStub {
        private final Attraction attraction;

        ModelStubWithAttraction(Attraction attraction) {
            requireNonNull(attraction);
            this.attraction = attraction;
        }

        @Override
        public boolean hasAttraction(Attraction attraction) {
            requireNonNull(attraction);
            return this.attraction.isSameAttraction(attraction);
        }
    }

    /**
     * A Model stub that always accept the attraction being added.
     */
    private class ModelStubAcceptingAttractionAdded extends ModelStub {
        final ArrayList<Attraction> attractionsAdded = new ArrayList<>();

        @Override
        public boolean hasAttraction(Attraction attraction) {
            requireNonNull(attraction);
            return attractionsAdded.stream().anyMatch(attraction::isSameAttraction);
        }

        @Override
        public void addAttraction(Attraction attraction) {
            requireNonNull(attraction);
            attractionsAdded.add(attraction);
        }

        @Override
        public ReadOnlyAttractionList getAttractionList() {
            return new AttractionList();
        }
    }

}
