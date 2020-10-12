package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.account.Account;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.ReadOnlyAccount;
import seedu.address.model.account.entry.Amount;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.tag.Tag;

public class AddCommandTest {

    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_correctFeedback() throws ParseException, CommandException {
        Tag tagStub = new Tag("stub");
        ArgumentMultimap argMultimapStub = new ArgumentMultimap();
        Set<Tag> tagListStub = ParserUtil.parseTags(argMultimapStub.getAllValues(PREFIX_TAG));
        Expense expenseStub = new Expense(new Description("flowers"), new Amount("20"), tagListStub);
        Model modelStub = new Model() {
            @Override
            public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

            }

            @Override
            public ReadOnlyUserPrefs getUserPrefs() {
                return null;
            }

            @Override
            public GuiSettings getGuiSettings() {
                return null;
            }

            @Override
            public void setGuiSettings(GuiSettings guiSettings) {

            }

            @Override
            public Path getCommonCentsFilePath() {
                return null;
            }

            @Override
            public void setCommonCentsFilePath(Path commonCentsFilePath) {

            }

            @Override
            public void setCommonCents(ReadOnlyCommonCents commonCents) {

            }

            @Override
            public ReadOnlyCommonCents getCommonCents() {
                return null;
            }

            @Override
            public boolean hasAccount(Account account) {
                return false;
            }

            @Override
            public void deleteAccount(Account target) {

            }

            @Override
            public void addAccount(Account account) {

            }

            @Override
            public void setAccount(Account target, Account editedAccount) {

            }

            @Override
            public void setAccount(Account editedAccount) {

            }

            @Override
            public ObservableList<Account> getFilteredAccountList() {
                return null;
            }

            @Override
            public void updateFilteredAccountList(Predicate<Account> predicate) {

            }

        };

        ActiveAccount activeAccountStub = new ActiveAccount() {
            @Override
            public void setActiveAccount(ReadOnlyAccount newActiveAccount) {

            }

            @Override
            public Account getAccount() {
                return null;
            }

            @Override
            public boolean hasEntry(Entry entry) {
                return false;
            }

            @Override
            public boolean hasExpense(Expense expense) {
                return false;
            }

            @Override
            public boolean hasRevenue(Revenue revenue) {
                return false;
            }

            @Override
            public void deleteExpense(Expense target) {

            }

            @Override
            public void deleteRevenue(Revenue target) {

            }

            @Override
            public void addExpense(Expense expense) {

            }

            @Override
            public void addRevenue(Revenue revenue) {

            }

            @Override
            public void setExpense(Expense target, Expense editedExpense) {

            }

            @Override
            public void setRevenue(Revenue target, Revenue editedRevenue) {

            }

            @Override
            public ObservableList<Expense> getFilteredExpenseList() {
                return null;
            }

            @Override
            public ObservableList<Revenue> getFilteredRevenueList() {
                return null;
            }

            @Override
            public void updateFilteredExpenseList(Predicate<Expense> predicate) {

            }

            @Override
            public void updateFilteredRevenueList(Predicate<Revenue> predicate) {

            }
        };

        assertEquals(String.format("New entry added!", expenseStub),
                new AddCommand(expenseStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }
}

//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
//        Person validPerson = new PersonBuilder().build();
//
//        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);
//
//        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person validPerson = new PersonBuilder().build();
//        AddCommand addCommand = new AddCommand(validPerson);
//        ModelStub modelStub = new ModelStubWithPerson(validPerson);
//
//        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON,
//        () -> addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        Person alice = new PersonBuilder().withName("Alice").build();
//        Person bob = new PersonBuilder().withName("Bob").build();
//        AddCommand addAliceCommand = new AddCommand(alice);
//        AddCommand addBobCommand = new AddCommand(bob);
//
//        // same object -> returns true
//        assertTrue(addAliceCommand.equals(addAliceCommand));
//
//        // same values -> returns true
//        AddCommand addAliceCommandCopy = new AddCommand(alice);
//        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addAliceCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addAliceCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(addAliceCommand.equals(addBobCommand));
//    }
//
//    /**
//     * A default model stub that have all of the methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getCommonCentsFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setCommonCentsFilePath(Path addressBookFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setCommonCents(ReadOnlyAddressBook newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyAddressBook getCommonCents() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasAccount(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deletePerson(Person target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setPerson(Person target, Person editedPerson) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Person> getFilteredPersonList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPersonList(Predicate<Person> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single person.
//     */
//    private class ModelStubWithPerson extends ModelStub {
//        private final Person person;
//
//        ModelStubWithPerson(Person person) {
//            requireNonNull(person);
//            this.person = person;
//        }
//
//        @Override
//        public boolean hasAccount(Person person) {
//            requireNonNull(person);
//            return this.person.isSamePerson(person);
//        }
//    }
//
//    /**
//     * A Model stub that always accept the person being added.
//     */
//    private class ModelStubAcceptingPersonAdded extends ModelStub {
//        final ArrayList<Person> personsAdded = new ArrayList<>();
//
//        @Override
//        public boolean hasAccount(Person person) {
//            requireNonNull(person);
//            return personsAdded.stream().anyMatch(person::isSamePerson);
//        }
//
//        @Override
//        public void addPerson(Person person) {
//            requireNonNull(person);
//            personsAdded.add(person);
//        }
//
//        @Override
//        public ReadOnlyAddressBook getCommonCents() {
//            return new AddressBook();
//        }
//    }
//

