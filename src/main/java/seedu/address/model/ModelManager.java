package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.budget.Budget;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.state.Page;
import seedu.address.state.StateManager;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.EmptyBudgetIndex;
import seedu.address.state.expenditureindex.ExpenditureIndex;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Nusave nusave;
    private final UserPrefs userPrefs;
    private final FilteredList<Renderable> filteredRenderables;
    private final StateManager stateManager;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyNusave nusave, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(nusave, userPrefs);

        logger.fine("Initializing with address book: " + nusave + " and user prefs " + userPrefs);

        this.nusave = new Nusave(nusave);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredRenderables = new FilteredList<>(this.nusave.getInternalList());
        this.stateManager = new StateManager(new EmptyBudgetIndex(), Page.MAIN);
    }

    public ModelManager() {
        this(new Nusave(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getNusaveFilePath() {
        return null;
    }

    @Override
    public void setNusavePath(Path nusaveFilePath) {

    }

    @Override
    public void setNusave(ReadOnlyNusave nusave) {

    }

    //=========== Nusave =======

    @Override
    public ReadOnlyNusave getNusave() {
        return nusave;
    }

    @Override
    public void addBudget(Budget budget) {
        requireNonNull(budget);
        nusave.addBudget(budget);
        updateFilteredRenderableList(PREDICATE_SHOW_ALL_RENDERABLES);
    }

    @Override
    public void deleteBudget(BudgetIndex budget) {
        requireNonNull(budget);
        int budgetIndex = budget.getBudgetIndex().orElse(-1);
        Budget deleteBudget = (Budget) this.filteredRenderables.get(budgetIndex);
        nusave.deleteBudget(deleteBudget);
        updateFilteredRenderableList(PREDICATE_SHOW_ALL_RENDERABLES);
    }

    @Override
    public void deleteExpenditure(ExpenditureIndex expenditure) {
        requireNonNull(expenditure);
        int expenditureIndex = expenditure.getExpenditureIndex().orElse(-1);
        nusave.deleteExpenditure(expenditureIndex, this.stateManager.getBudgetIndex());
        updateFilteredRenderableList(PREDICATE_SHOW_ALL_RENDERABLES);
    }

    /**
     * Adds an expenditure to the specified budget and updates the list
     * @param expenditure
     */
    public void addExpenditure(Expenditure expenditure) {
        requireNonNull(expenditure);
        nusave.addExpenditure(expenditure, this.stateManager.getBudgetIndex());
        updateFilteredRenderableList(PREDICATE_SHOW_ALL_RENDERABLES);
    }

    @Override
    public void repopulateObservableList() {
        nusave.repopulateObservableList(stateManager);

    }

    //=========== StateManager ================================================================================

    @Override
    public boolean isMain() {
        return this.stateManager.isMain();
    }

    @Override
    public boolean isBudget() {
        return this.stateManager.isBudget();
    }

    @Override
    public Optional<Integer> getBudgetIndex() {
        return this.stateManager.getBudgetIndex();
    }

    @Override
    public Page getPage() {
        return this.stateManager.getPage();
    }

    @Override
    public void setBudgetIndex(BudgetIndex index) {
        this.stateManager.setBudgetIndex(index);
    }

    @Override
    public void setPage(Page page) {
        this.stateManager.setPage(page);
    }

    //=========== Filtered Renderable List Accessors =============================================================

    @Override
    public ObservableList<Renderable> getFilteredRenderableList() {
        return filteredRenderables;
    }

    @Override
    public void updateFilteredRenderableList(Predicate<Renderable> predicate) {
        requireNonNull(predicate);
        filteredRenderables.setPredicate(predicate);
    }
}
