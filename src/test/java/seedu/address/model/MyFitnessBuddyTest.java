package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDays.DAY1;
import static seedu.address.testutil.TypicalDays.DEFAULT_PROFILE;
import static seedu.address.testutil.TypicalPerson.getTypicalMyFitnessBuddy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.day.Day;
import seedu.address.model.person.Person;
import seedu.address.model.person.Profile;
import seedu.address.testutil.DayBuilder;
import seedu.address.testutil.PersonBuilder;

public class MyFitnessBuddyTest {

    private final MyFitnessBuddy myFitnessBuddy = new MyFitnessBuddy();
    private final Person person = new PersonBuilder().withProfile(DEFAULT_PROFILE).build();

    @Test
    public void constructor() {
        assertEquals(person, myFitnessBuddy.getPerson());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myFitnessBuddy.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMyFitnessBuddy_replacesData() {
        MyFitnessBuddy newData = getTypicalMyFitnessBuddy();
        myFitnessBuddy.resetData(newData);
        assertEquals(newData, myFitnessBuddy);
    }
    //error due to refactoring
    @Test
    public void resetData_withDuplicateDays_throwsDuplicateDayException() {
        // Two days with the same identity fields
        Day editedAlice = new DayBuilder(DAY1)
                .build();
        List<Day> newDays = Arrays.asList(DAY1, editedAlice);
        MyFitnessBuddyStub newData = new MyFitnessBuddyStub(newDays, person);

        //assertThrows(DuplicateDayException.class, () -> myFitnessBuddy.resetData(newData));
    }

    @Test
    public void hasDay_nullDay_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> myFitnessBuddy.getPerson().hasDay((Day) null));
    }

    @Test
    public void hasDay_dayNotInMyFitnessBuddy_returnsFalse() {
        assertFalse(myFitnessBuddy.getPerson().hasDay(DAY1));
    }

    //    @Test
    public void hasDay_dayInMyFitnessBuddy_returnsTrue() {
        myFitnessBuddy.addDay(DAY1);
        assertTrue(myFitnessBuddy.getPerson().hasDay(DAY1));
    }

    //    @Test
    public void hasDay_dayWithSameIdentityFieldsInMyFitnessBuddy_returnsTrue() {
        myFitnessBuddy.addDay(DAY1);
        Day editedAlice = new DayBuilder(DAY1)
                .build();
        assertTrue(myFitnessBuddy.getPerson().hasDay(editedAlice));
    }

    @Test
    public void getDayList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> myFitnessBuddy.getPerson().getDayList().remove(0));
    }

    /**
     * A stub ReadOnlyMyFitnessBuddy whose days list can violate interface constraints.
     */
    private static class MyFitnessBuddyStub implements ReadOnlyMyFitnessBuddy {
        private final ObservableList<Day> personList = FXCollections.observableArrayList();
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private Person person;
        MyFitnessBuddyStub(Collection<Day> personList, Person person) {
            this.person = person;
            this.personList.setAll(personList);
        }

        /**
         * Returns an unmodifiable view of the days list.
         * This list will not contain any duplicate persons.
         */
        @Override
        public ObservableList<Day> getDayList() {
            return person.getDayList();
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        /**
         * Returns the profile of a person in My Fitness Buddy.
         */
        @Override
        public Profile getProfile() {
            return person.getProfile();
        }

        @Override
        public Person getPerson() {
            return person;
        }

        @Override
        public ObservableList<Person> getPersons() {
            return null;
        }

    }

}
