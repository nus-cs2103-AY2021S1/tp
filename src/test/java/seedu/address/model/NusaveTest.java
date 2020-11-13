//package seedu.address.model;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalBudgets.BUDGET_LIST;
//import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;
//import static seedu.address.testutil.TypicalExpenditures.getTypicalExpenditures;
//import static seedu.address.testutil.TypicalState.TYPICAL_MAIN_PAGE_STATE;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import seedu.address.model.budget.BudgetList;
//import seedu.address.model.expenditure.ExpenditureList;
//import seedu.address.state.State;
//import seedu.address.state.budgetindex.BudgetIndexManager;
//
//class NusaveTest {
//
//    private final Nusave nusave = new Nusave();
//
//    @Test
//    public void constructor() {
//        assertEquals(Collections.emptyList(), nusave.getBudgetListAsObservableList());
//    }
//
//    @Test
//    void getPageName() {
//        nusave.resetData(getTypicalNusave());
//        assertEquals(nusave.getPageName(new BudgetIndexManager(0)), "McDonalds");
//    }
//
//    @Test
//    void resetData_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> nusave.resetData(null));
//    }
//
//    @Test
//    public void resetData_withValidReadOnlyAddressBook_replacesData() {
//        Nusave newData = getTypicalNusave();
//        nusave.resetData(newData);
//        assertEquals(nusave.getBudgetListAsObservableList(), newData.getBudgetListAsObservableList());
//    }
//
//    @Test
//    void addExpenditure() {
//        nusave.resetData(getTypicalNusave());
//        ExpenditureList list = getTypicalExpenditures();
//        nusave.addExpenditure(list.getExpenditures().get(0), Optional.of(0));
//        assertEquals(nusave.getInternalList().get(0), list.getExpenditures().get(0));
//    }
//
//    @Test
//    void editExpenditure() {
//        nusave.resetData(getTypicalNusave());
//        ExpenditureList list = getTypicalExpenditures();
//        nusave.addExpenditure(list.getExpenditures().get(0), Optional.of(0));
//        nusave.editExpenditure(list.getExpenditures().get(0), list.getExpenditures().get(1), Optional.of(0));
//        assertEquals(nusave.getInternalList().get(0), list.getExpenditures().get(1));
//    }
//
//
//    @Test
//    void getTotalExpenditureValue_null_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> nusave.getTotalExpenditureValue(null));
//    }
//
//
//    @Test
//    void repopulateObservableList_isMainPage_validInput() {
//        nusave.resetData(getTypicalNusave());
//        State state = TYPICAL_MAIN_PAGE_STATE;
//        nusave.repopulateObservableList(state);
//        ObservableList<Renderable> internalList = FXCollections.unmodifiableObservableList(
//                FXCollections.observableArrayList(BUDGET_LIST.getBudgets()));
//        assertEquals(nusave.getInternalList(), internalList);
//    }
//
//    @Test
//    void getBudgetList() {
//        nusave.resetData(getTypicalNusave());
//        BudgetList budgetList = nusave.getBudgetList();
//        assertEquals(budgetList, BUDGET_LIST);
//    }
//
//    @Test
//    void getInternalList() {
//
//    }
//
//}
