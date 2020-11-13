package seedu.address.logic.commands.meeting;

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
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

class AddCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelMeetingStubAcceptingMeetingAdded modelStub = new ModelMeetingStubAcceptingMeetingAdded();
        Meeting validMeeting = new MeetingBuilder().build();

        CommandResult commandResult = new AddCommand(validMeeting).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Meeting validMeeting = new MeetingBuilder().build();
        AddCommand addCommand = new AddCommand(validMeeting);
        ModelMeetingStub modelStub = new ModelMeetingStubWithMeeting(validMeeting);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_MEETING, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Meeting testA = new MeetingBuilder().withTitle("TestA").build();
        Meeting testB = new MeetingBuilder().withTitle("TestB").build();
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

        // different person -> returns false
        assertFalse(addTestACommand.equals(addTestBCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelMeetingStub implements ModelMeeting {
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
        public Path getMeetingBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMeeting(Meeting person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingBook(ReadOnlyMeetingBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Meeting getMeetingInView() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeetingInView(Meeting meetingInView) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMeeting(Meeting target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMeeting(Meeting target, Meeting editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getFilteredMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Meeting> getInternalMeetingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMeetingList(Predicate<Meeting> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelMeetingStubWithMeeting extends ModelMeetingStub {
        private final Meeting meeting;

        ModelMeetingStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting person) {
            requireNonNull(person);
            return this.meeting.isSameMeeting(person);
        }
    }
    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelMeetingStubAcceptingMeetingAdded extends ModelMeetingStub {
        final ArrayList<Meeting> personsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return personsAdded.stream().anyMatch(meeting::isSameMeeting);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            personsAdded.add(meeting);
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            return new MeetingBook();
        }
    }
}
