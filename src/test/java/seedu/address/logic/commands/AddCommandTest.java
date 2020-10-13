package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;

import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.testutil.ActiveAccountStub;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.RevenueBuilder;


public class AddCommandTest {

    private final ExpenseBuilder expenseBuilder = new ExpenseBuilder();
    private final RevenueBuilder revenueBuilder = new RevenueBuilder();

    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_entryAcceptedByModel_addSuccessful() {
        assertEquals(true, true);
        //placeholder for now
    }

    @Test
    public void execute_typicalExpense() {
        Expense expenseStub = expenseBuilder.build();
        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Expense: buying paint supplies Amount: 131.73 Tags: ",
                new AddCommand(expenseStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void execute_typicalRevenue() {
        Revenue revenueStub = revenueBuilder.build();
        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Revenue: buying paint supplies Amount: 131.73 Tags: ",
                new AddCommand(revenueStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void execute_typicalRevenue_withTags() {
        //to maintain immutability of revenueBuilder
        RevenueBuilder revenueBuilderStub = new RevenueBuilder(revenueBuilder.build());
        revenueBuilderStub.withTags("bar", "foo");
        Revenue revenueStub = revenueBuilderStub.build();
        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Revenue: buying paint supplies Amount: 131.73 Tags: [bar][foo]",
                new AddCommand(revenueStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void execute_typicalExpense_withTags() {
        //to maintain immutability of expenseBuilder
        ExpenseBuilder expenseBuilderStub = new ExpenseBuilder(expenseBuilder.build());
        expenseBuilderStub.withTags("bar", "foo");
        Expense expenseStub = expenseBuilderStub.build();

        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Expense: buying paint supplies Amount: 131.73 Tags: [bar][foo]",
                new AddCommand(expenseStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

}

//class ActiveAccountAddSuccessful extends ActiveAccountStub {
//    private final Account activeAccount;
//    private final ArrayList<Entry> entries;
//
//    public ActiveAccountAddSuccessful(ReadOnlyAccount account) {
//        this.activeAccount = new Account(account);
//        this.entries = new ArrayList<>();
//    }
//
//    @Override
//    public void addExpense(Expense expense) {
//        entries.add(expense);
//    }
//
//    @Override
//    public boolean hasEntry(Entry entry) {
//        return entries.contains(entry);
//    }
//
//}

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

