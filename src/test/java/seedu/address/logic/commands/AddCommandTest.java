package seedu.address.logic.commands;

public class AddCommandTest {

//    @Test
//    public void constructor_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddCommand(null));
//    }
//
//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
//        Flashcard validFlashcard = new FlashcardBuilder().build();
//
//        CommandResult commandResult = new AddCommand(validFlashcard).execute(modelStub);
//
//        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validFlashcard), modelStub.personsAdded);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Flashcard validFlashcard = new FlashcardBuilder().build();
//        AddCommand addCommand = new AddCommand(validFlashcard);
//        ModelStub modelStub = new ModelStubWithPerson(validFlashcard);
//
//        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () ->
//        addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        Flashcard alice = new FlashcardBuilder().withName("Alice").build();
//        Flashcard bob = new FlashcardBuilder().withName("Bob").build();
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
//        public Path getAddressBookFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBookFilePath(Path addressBookFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addFlashcard(Flashcard flashcard) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBook(ReadOnlyAddressBook newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasFlashcard(Flashcard flashcard) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteFlashcard(Flashcard target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Flashcard> getFilteredPersonList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPersonList(Predicate<Flashcard> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single person.
//     */
//    private class ModelStubWithPerson extends ModelStub {
//        private final Flashcard flashcard;
//
//        ModelStubWithPerson(Flashcard flashcard) {
//            requireNonNull(flashcard);
//            this.flashcard = flashcard;
//        }
//
//        @Override
//        public boolean hasFlashcard(Flashcard flashcard) {
//            requireNonNull(flashcard);
//            return this.flashcard.isSameQuestion(flashcard);
//        }
//    }
//
//    /**
//     * A Model stub that always accept the person being added.
//     */
//    private class ModelStubAcceptingPersonAdded extends ModelStub {
//        final ArrayList<Flashcard> personsAdded = new ArrayList<>();
//
//        @Override
//        public boolean hasFlashcard(Flashcard flashcard) {
//            requireNonNull(flashcard);
//            return personsAdded.stream().anyMatch(flashcard::isSameQuestion);
//        }
//
//        @Override
//        public void addFlashcard(Flashcard flashcard) {
//            requireNonNull(flashcard);
//            personsAdded.add(flashcard);
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            return new AddressBook();
//        }
//    }

}
