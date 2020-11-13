package seedu.address.logic.commands.main;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Renderable;
import seedu.address.model.budget.Budget;
import seedu.address.model.budget.BudgetList;
import seedu.address.model.budget.Threshold;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.state.Page;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.expenditureindex.ExpenditureIndex;
import seedu.address.testutil.TypicalBudget;

class CreateBudgetCommandTest {

    @Test
    public void equalTest() {
        CreateBudgetCommand command = new CreateBudgetCommand(TypicalBudget.getMcDonaldsBudget());
        // same object -> return true
        assertTrue(command.equals(command));

        // different type -> return false
        assertFalse(command.equals(5));

        // null -> return false
        assertFalse(command.equals(null));

        CreateBudgetCommand differentBudgetCommand =
                new CreateBudgetCommand(TypicalBudget.getMcDonaldsBudget());
        // different obj same budget to add -> true
        assertTrue(command.equals(differentBudgetCommand));
    }

    @Test
    public void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateBudgetCommand(null));
    }

    @Test
    public void execute_budgetCreatedByModel_createSuccessful() throws Exception {
        ModelStubAcceptingBudgetCreated modelStub = new ModelStubAcceptingBudgetCreated();
        Budget validBudget = TypicalBudget.getMcDonaldsBudget();

        CommandResult commandResult = new CreateBudgetCommand(validBudget).execute(modelStub);

        assertEquals(String.format(CreateBudgetCommand.MESSAGE_SUCCESS, validBudget),
                commandResult.getFeedbackToUser());
        assertEquals(new BudgetList(Arrays.asList(validBudget)), modelStub.budgetCreated);
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
        public Path getNusaveFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNusavePath(Path nusaveFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setNusave(ReadOnlyNusave nusave) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyNusave getNusave() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void openBudget(BudgetIndex budgetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void closeBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editBudget(Budget oldBudget, Budget editedBudget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Budget getBudgetAtIndex(BudgetIndex budgetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Expenditure getExpenditureAtIndex(ExpenditureIndex expenditureIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isIndexOutOfBound(BudgetIndex budgetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isIndexOutOfBound(ExpenditureIndex expenditureIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBudget(BudgetIndex budget) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAllBudgets() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortBudgetsByName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortBudgetsByCreatedDate() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addExpenditure(Expenditure expenditure) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void findBudget(String searchTerm) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listBudgets() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteExpenditure(ExpenditureIndex expenditure) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void findExpenditure(String searchTerm) throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortExpendituresByName() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortExpenditureByCreatedDate() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listExpenditures() throws CommandException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editExpenditure(Expenditure oldExpenditure, Expenditure editedExpenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String calculateExpenditureValue(BudgetIndex budgetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Page getPage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getPageName(BudgetIndex index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getPageTitle() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getTotalExpenditureValue() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Threshold> getThreshold() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isBudgetPage() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public BooleanProperty getBudgetPageProp() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StringProperty getTotalExpenditureStringProp() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public StringProperty getThresholdStringProp() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPage(Page page) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setThreshold(Optional<Threshold> threshold) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTotalExpenditure(String expenditure) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBudgetIndex(BudgetIndex index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPageTitle(String page) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isMain() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isWithinRange(BudgetIndex budgetIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isWithinRange(ExpenditureIndex expenditureIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Renderable> getFilteredRenderableList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRenderableList(Predicate<Renderable> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void repopulateObservableList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveToHistory() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accept a Budget being created.
     */
    private class ModelStubAcceptingBudgetCreated extends ModelStub {
        final BudgetList budgetCreated = new BudgetList();

        @Override
        public void addBudget(Budget budget) {
            requireNonNull(budget);
            budgetCreated.add(budget);
        }

        @Override
        public void saveToHistory() {
        }
    }

}
