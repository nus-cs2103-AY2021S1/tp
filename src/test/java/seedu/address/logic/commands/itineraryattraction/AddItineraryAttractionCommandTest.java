package seedu.address.logic.commands.itineraryattraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_ATTRACTION;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ITINERARY_DAY;
import static seedu.address.commons.core.Messages.MESSAGE_ITINERARY_NOT_SELECTED;
import static seedu.address.commons.core.Messages.MESSAGE_TIMING_CLASH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAttractionList;
import seedu.address.model.ReadOnlyItineraryAttractionList;
import seedu.address.model.ReadOnlyItineraryList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.testutil.AttractionBuilder;
import seedu.address.testutil.AttractionListBuilder;
import seedu.address.testutil.ItineraryAttractionBuilder;
import seedu.address.testutil.ItineraryBuilder;

public class AddItineraryAttractionCommandTest {
    // A itinerary time instance for testing exceptions
    // does not throw error for same start and end time as this was checked in the parser
    private final ItineraryTime timeObject = new ItineraryTime("1000");

    @Test
    public void constructor_nullItineraryAttraction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItineraryAttractionCommand(null, null, null, null));
    }

    @Test
    public void execute_itineraryNotSelected_throwsCommandException() {
        ModelStubNoItinerarySelected model = new ModelStubNoItinerarySelected();

        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(
                INDEX_FIRST, timeObject, timeObject, INDEX_FIRST);
        assertThrows(CommandException.class, MESSAGE_ITINERARY_NOT_SELECTED, () -> addIaCommand.execute(model));
    }

    @Test
    public void execute_invalidAttractionIndex_throwsCommandException() {
        ModelStubWithEmptyAttractionInFilteredList model = new ModelStubWithEmptyAttractionInFilteredList();
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(
                INDEX_FIRST, timeObject, timeObject, INDEX_FIRST);

        assertThrows(CommandException.class, MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, ()
            -> addIaCommand.execute(model)); // test on empty filtered list


        ModelStubWithItineraryAttraction model2 = new ModelStubWithItineraryAttraction(new ItineraryBuilder()
                .withItineraryAttraction(new ItineraryAttractionBuilder().withAttraction(new AttractionBuilder()
                        .build()).build(), INDEX_FIRST).build());
        AddItineraryAttractionCommand addIaCommand2 = new AddItineraryAttractionCommand(
                INDEX_SECOND, timeObject, timeObject, INDEX_FIRST);

        assertThrows(CommandException.class, MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX, ()
            -> addIaCommand2.execute(model2)); // test on filtered list with 1 element
    }

    @Test
    public void execute_timingClash_throwsCommandException() {
        // start time of new itineraryAttraction conflicts with end time of existing itineraryAttraction
        ItineraryAttraction ia = new ItineraryAttractionBuilder().withStartTime("0900").withEndTime("1100").build();
        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(ia, INDEX_THIRD).build();
        ModelStubWithItineraryAttraction model = new ModelStubWithItineraryAttraction(itinerary);
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(
                INDEX_FIRST, new ItineraryTime("1000"), new ItineraryTime("1300"), INDEX_THIRD);

        assertThrows(CommandException.class, MESSAGE_TIMING_CLASH, ()
            -> addIaCommand.execute(model));
    }

    @Test
    public void execute_timingClash2_throwsCommandException() {
        // end time of new itineraryAttraction conflicts with start time of existing itineraryAttraction
        ItineraryAttraction ia = new ItineraryAttractionBuilder().withStartTime("0900").withEndTime("1100").build();
        Itinerary itinerary = new ItineraryBuilder().withItineraryAttraction(ia, INDEX_THIRD).build();
        ModelStubWithItineraryAttraction model = new ModelStubWithItineraryAttraction(itinerary);
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(
                INDEX_FIRST, new ItineraryTime("0600"), new ItineraryTime("1000"), INDEX_THIRD);

        assertThrows(CommandException.class, MESSAGE_TIMING_CLASH, ()
            -> addIaCommand.execute(model));
    }

    @Test
    public void execute_invalidDay_throwsCommandException() {
        Itinerary itinerary = new ItineraryBuilder().build();
        ModelStubWithItineraryAttraction model = new ModelStubWithItineraryAttraction(itinerary);
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(INDEX_FIRST, timeObject,
                timeObject, Index.fromZeroBased(4));

        assertThrows(CommandException.class, MESSAGE_INVALID_ITINERARY_DAY, ()
            -> addIaCommand.execute(model));
    }

    @Test
    public void execute_duplicateItineraryAttraction_throwsCommandException() {
        ModelStubWithItineraryAttraction model = new ModelStubWithItineraryAttraction(new ItineraryBuilder()
                .withItineraryAttraction(new ItineraryAttractionBuilder().withAttraction(new AttractionBuilder()
                        .build()).build(), INDEX_FIRST).build());

        ItineraryAttraction expectedIa = new ItineraryAttractionBuilder()
                .withAttraction(new AttractionBuilder().build()).build();

        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(
                INDEX_FIRST, expectedIa.getStartTime(), expectedIa.getEndTime(), INDEX_FIRST);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_ATTRACTION, ()
            -> addIaCommand.execute(model));
    }

    @Test
    public void equals() {

        // test itinerary attraction command equals
        ItineraryTime startTime = new ItineraryTime("1200");
        ItineraryTime endTime = new ItineraryTime("1300");
        Index index = Index.fromOneBased(9);
        Index day = Index.fromOneBased(2);
        AddItineraryAttractionCommand addIaCommand = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        AddItineraryAttractionCommand addIaCommand2 = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertEquals(addIaCommand, addIaCommand2);

        // test itinerary attraction command not equal index
        index = Index.fromOneBased(1);
        addIaCommand2 = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        // test itinerary attraction command not equal day, set addIaCommand index to 1
        day = Index.fromOneBased(1);
        addIaCommand = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        startTime = new ItineraryTime("1159");
        addIaCommand = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        // test itinerary attraction command not equal end time
        endTime = new ItineraryTime("1359");
        addIaCommand2 = new AddItineraryAttractionCommand(index, startTime, endTime, day);
        assertNotEquals(addIaCommand, addIaCommand2);

        // tests itinerary attraction class
        ItineraryAttraction ia1 = new ItineraryAttractionBuilder().withStartTime("1200").build();
        ItineraryAttraction ia2 = new ItineraryAttractionBuilder().withStartTime("1200").build();

        // same object -> returns true
        assertEquals(ia1, ia1);
        assertEquals(ia2, ia2);

        // same values -> returns true
        assertEquals(ia1, ia2);

        // different types -> returns false
        assertNotEquals(ia1, 1);

        // null -> returns false
        assertNotEquals(ia1, null);
    }

    @Test
    public void execute_attractionAccepted_addSuccessful() throws Exception {
        ModelStubWithItineraryAttraction model = new ModelStubWithItineraryAttraction(new ItineraryBuilder().build());

        ItineraryAttraction validIa = new ItineraryAttractionBuilder().build();
        CommandResult commandResult = new AddItineraryAttractionCommand(
                INDEX_FIRST, validIa.getStartTime(), validIa.getEndTime(), INDEX_FIRST).execute(model);

        assertEquals(String.format(AddItineraryAttractionCommand.MESSAGE_ADD_ATTRACTION_SUCCESS, validIa),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIa), model.getDay(INDEX_FIRST).getItineraryAttractions());
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
     * A Model stub that returns no itinerary selected.
     */
    private class ModelStubNoItinerarySelected extends ModelStub {
        @Override
        public Itinerary getCurrentItinerary() {
            return null;
        }
    }

    /**
     * A Model stub that contains a empty filtered list of Attraction type.
     * filteredAttractions does not contain anything.
     */
    private class ModelStubWithEmptyAttractionInFilteredList extends ModelStub {
        private final FilteredList<Attraction> filteredAttractions = new FilteredList<>(
                new AttractionListBuilder().build().getAttractionList());

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            return filteredAttractions;
        }

        @Override
        public Itinerary getCurrentItinerary() {
            return new ItineraryBuilder().build();
        }

    }

    /**
     * A Model stub that contains 1 Itinerary and 1 ItineraryAttraction in Day 1 with non null filteredAttractions.
     */
    private class ModelStubWithItineraryAttraction extends ModelStub {
        private final Itinerary itinerary;
        private final FilteredList<Attraction> filteredAttractions;

        public ModelStubWithItineraryAttraction(Itinerary itinerary) {
            this.itinerary = itinerary;
            this.filteredAttractions = new FilteredList<>(new AttractionListBuilder()
                    .withAttraction(new AttractionBuilder().build()).build().getAttractionList());
        }

        @Override
        public ObservableList<Attraction> getFilteredAttractionList() {
            return filteredAttractions;
        }

        @Override
        public Itinerary getCurrentItinerary() {
            return itinerary;
        }

        private Day getDay(Index index) {
            return itinerary.getDay(index);
        }
    }

}
