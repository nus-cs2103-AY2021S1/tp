package seedu.address.model.account.entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ExpenseBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseListTest {

    @Test
    void contains_true() {
        ObservableList<Expense> internalList = FXCollections.observableArrayList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("food"));
        tags1.add(new Tag("restaurant"));
        Expense e1 = new Expense(new Description("dinner"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("retail"));
        tags2.add(new Tag("clothes"));
        Expense e2 = new Expense(new Description("shopping for clothes"), new Amount("25"), tags2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("craft"));
        tags3.add(new Tag("ArtFriend"));
        Expense e3 = new Expense(new Description("artsupplies"), new Amount("10.50"), tags3);

        internalList.addAll(e1,e2,e3);
        assertTrue(internalList.contains(e1));
        assertTrue(internalList.contains(e2));
        assertTrue(internalList.contains(e3));


    }

    @Test
    public void contains_false(){
        ObservableList<Expense>internalList=FXCollections.observableArrayList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("food"));
        tags1.add(new Tag("restaurant"));
        Expense e1 = new Expense(new Description("dinner"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("retail"));
        tags2.add(new Tag("clothes"));
        Expense e2 = new Expense(new Description("shoppingforclothes"), new Amount("25"), tags2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("craft"));
        tags3.add(new Tag("ArtFriend"));
        Expense e3 = new Expense(new Description("artsupplies"), new Amount("10.50"), tags3);

        internalList.addAll(e1,e2);
        assertFalse(internalList.contains(e3));
    }

    @Test
    public void add() {

        ExpenseList expenseList = new ExpenseList();
        Expense expense = (new ExpenseBuilder()).build();
        expenseList.add(expense);

        assertTrue(expenseList.contains(expense));
    }

    @Test
    public void setExpense() {

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("food"));
        tags1.add(new Tag("restaurant"));
        Expense e1 = new Expense(new Description("dinner"), new Amount("5.00"), tags1);
        ExpenseList expenseList = new ExpenseList();
        expenseList.add(e1);

        Expense e2 = (new ExpenseBuilder()).build();
        expenseList.setExpense(e1, e2);

        assertTrue(expenseList.contains(e2));
    }

    @Test
    public void remove() {
        ExpenseList expenseList = new ExpenseList();
        Expense expense = (new ExpenseBuilder()).build();
        expenseList.add(expense);
        expenseList.remove(expense);

        assertFalse(expenseList.contains(expense));
    }

    @Test
    public void setExpenses() {

        ExpenseList expenseList = new ExpenseList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("food"));
        tags1.add(new Tag("restaurant"));
        Expense e1 = new Expense(new Description("dinner"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("retail"));
        tags2.add(new Tag("clothes"));
        Expense e2 = new Expense(new Description("shopping for clothes"), new Amount("25"), tags2);

        expenseList.add(e1);
        expenseList.add(e2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("craft"));
        tags3.add(new Tag("ArtFriend"));
        Expense e3 = new Expense(new Description("artsupplies"), new Amount("10.50"), tags3);

        Expense e4 = (new ExpenseBuilder()).build();
        List<Expense> replacement = Arrays.asList(e3, e4);

        expenseList.setExpenses(replacement);

        assertFalse(expenseList.contains(e1));
        assertFalse(expenseList.contains(e2));
        assertTrue(expenseList.contains(e3));
        assertTrue(expenseList.contains(e4));
    }

    @Test
    public void testSetExpenses() {
        ExpenseList expenseList = new ExpenseList();

        Set<Tag> tags1 = new HashSet<>();
        tags1.add(new Tag("food"));
        tags1.add(new Tag("restaurant"));
        Expense e1 = new Expense(new Description("dinner"), new Amount("5.00"), tags1);

        Set<Tag> tags2 = new HashSet<>();
        tags2.add(new Tag("retail"));
        tags2.add(new Tag("clothes"));
        Expense e2 = new Expense(new Description("shopping for clothes"), new Amount("25"), tags2);

        expenseList.add(e1);
        expenseList.add(e2);

        Set<Tag> tags3 = new HashSet<>();
        tags3.add(new Tag("craft"));
        tags3.add(new Tag("ArtFriend"));
        Expense e3 = new Expense(new Description("artsupplies"), new Amount("10.50"), tags3);

        Expense e4 = (new ExpenseBuilder()).build();
        List<Expense> replacement = Arrays.asList(e3, e4);

        expenseList.setExpenses(replacement);

        assertFalse(expenseList.contains(e1));
        assertFalse(expenseList.contains(e2));
        assertTrue(expenseList.contains(e3));
        assertTrue(expenseList.contains(e4));
    }

}