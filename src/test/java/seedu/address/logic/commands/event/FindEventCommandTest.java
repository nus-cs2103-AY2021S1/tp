package seedu.address.logic.commands.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_EVENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.ModelStub;
import seedu.address.logic.commands.schedulercommands.FindEventCommand;
import seedu.address.model.EventList;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventContainsDatePredicate;
import seedu.address.model.event.EventNameContainsKeyWordsPredicate;
import seedu.address.model.event.FindEventCriteria;
import seedu.address.testutil.event.FindEventCriteriaBuilder;


public class FindEventCommandTest {

    private final ModelStubAlwaysHaveFind alwaysHaveFind = new ModelStubAlwaysHaveFind();
    private final ModelStubEmpty modelStubEmpty = new ModelStubEmpty();

    @Test
    public void constructor_nullFindEventCriteria_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindEventCommand(null));
    }

    @Test
    public void executeName_multipleKeywords_eventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventNameContainsKeyWordsPredicate predicate = prepareNamePredicate("test cs2101");
        alwaysHaveFind.addEvent(VALID_EVENT);
        FindEventCriteria findEventCriteria = new FindEventCriteriaBuilder()
                .withNamePredicate(predicate).build();
        FindEventCommand command = new FindEventCommand(findEventCriteria);
        ModelStubAlwaysHaveFind expectedModel = new ModelStubAlwaysHaveFind();
        expectedModel.addEvent(VALID_EVENT);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, alwaysHaveFind, expectedMessage, expectedModel);
        assertEquals(FXCollections.observableList(List.of(VALID_EVENT)), alwaysHaveFind.getFilteredEventList());
    }

    @Test
    public void executeName_multipleKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        alwaysHaveFind.addEvent(VALID_EVENT);
        EventNameContainsKeyWordsPredicate predicate = prepareNamePredicate("assignment");
        FindEventCriteria findEventCriteria = new FindEventCriteriaBuilder()
                .withNamePredicate(predicate).build();
        FindEventCommand command = new FindEventCommand(findEventCriteria);
        ModelStubAlwaysHaveFind expectedModel = new ModelStubAlwaysHaveFind();
        expectedModel.addEvent(VALID_EVENT);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, alwaysHaveFind, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), alwaysHaveFind.getFilteredEventList());
    }

    @Test
    public void executeDate_multipleKeywords_eventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 1);
        EventContainsDatePredicate predicate = prepareDatePredicate(VALID_EVENT_DATE_1);
        alwaysHaveFind.addEvent(VALID_EVENT);
        FindEventCriteria findEventCriteria = new FindEventCriteriaBuilder()
                .withDatePredicate(predicate).build();
        FindEventCommand command = new FindEventCommand(findEventCriteria);
        ModelStubAlwaysHaveFind expectedModel = new ModelStubAlwaysHaveFind();
        expectedModel.addEvent(VALID_EVENT);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, alwaysHaveFind, expectedMessage, expectedModel);
        assertEquals(FXCollections.observableList(List.of(VALID_EVENT)), alwaysHaveFind.getFilteredEventList());
    }

    @Test
    public void executeDate_multipleKeywords_noEventFound() {
        String expectedMessage = String.format(MESSAGE_EVENTS_LISTED_OVERVIEW, 0);
        EventContainsDatePredicate predicate = prepareDatePredicate(VALID_EVENT_DATE_2);
        alwaysHaveFind.addEvent(VALID_EVENT);
        FindEventCriteria findEventCriteria = new FindEventCriteriaBuilder()
                .withDatePredicate(predicate).build();
        FindEventCommand command = new FindEventCommand(findEventCriteria);
        ModelStubAlwaysHaveFind expectedModel = new ModelStubAlwaysHaveFind();
        expectedModel.addEvent(VALID_EVENT);
        expectedModel.updateFilteredEventList(predicate);
        assertCommandSuccess(command, alwaysHaveFind, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), alwaysHaveFind.getFilteredEventList());
    }


    @Test
    public void equals() {
        EventNameContainsKeyWordsPredicate namePredicate =
                new EventNameContainsKeyWordsPredicate(Collections.singletonList("first"));
        EventContainsDatePredicate datePredicate =
                new EventContainsDatePredicate(List.of("2022-01-02T12:00"));

        FindEventCriteria firstCriteria = new FindEventCriteriaBuilder()
                .withNamePredicate(namePredicate).build();
        FindEventCriteria secondCriteria = new FindEventCriteriaBuilder()
                .withDatePredicate(datePredicate).build();

        FindEventCommand findFirstCommand = new FindEventCommand(firstCriteria);
        FindEventCommand findSecondCommand = new FindEventCommand(secondCriteria);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same predicate value -> returns true
        FindEventCommand findFirstCommandCopy = new FindEventCommand(firstCriteria);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(10));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate value -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Parses {@code userInput} into a {@code ContactNameContainsKeywordsPredicate}.
     */
    private EventNameContainsKeyWordsPredicate prepareNamePredicate(String userInput) {
        return new EventNameContainsKeyWordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EventContainsDatePredicate}.
     */
    private EventContainsDatePredicate prepareDatePredicate(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-uuuu HHmm");
        return new EventContainsDatePredicate(List.of(LocalDateTime.parse(userInput, formatter).toString()));
    }

    public class ModelStubAlwaysHaveFind extends ModelStub {
        private final EventList eventList = new EventList();
        private final FilteredList<Event> filteredList = new FilteredList<>(eventList.getEventList());

        @Override
        public void addEvent(Event event) {
            eventList.addEvent(event);
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            return filteredList;
        }

        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            filteredList.setPredicate(predicate);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            } else if (other instanceof ModelStubAlwaysHaveFind) {
                ModelStubAlwaysHaveFind otherModel = (ModelStubAlwaysHaveFind) other;
                return this.eventList.equals(otherModel.eventList) && this.filteredList.equals(otherModel.filteredList);
            } else {
                return false;
            }
        }
    }

    public class ModelStubEmpty extends ModelStub {

    }
}
