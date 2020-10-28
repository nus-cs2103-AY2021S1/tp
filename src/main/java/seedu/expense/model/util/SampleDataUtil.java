package seedu.expense.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.expense.model.ExpenseBook;
import seedu.expense.model.ReadOnlyExpenseBook;
import seedu.expense.model.alias.AliasEntry;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Date;
import seedu.expense.model.expense.Description;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.Remark;
import seedu.expense.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ExpenseBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Expense[] getSampleExpenses() {
        return new Expense[]{
            new Expense(new Description("Felicia's Birthday"), new Amount("140.00"), new Date("02-07-2020"),
                new Remark("Birthday surprise with friends + birthday presents + birthday dinner"),
                new Tag("Girlfriend")),
            new Expense(new Description("Lunch with Hostel Mates"), new Amount("13"), new Date("01-07-2020"),
                EMPTY_REMARK, new Tag("Food")),
            new Expense(new Description("Grab Home"), new Amount("15"), new Date("01-07-2020"),
                new Remark("Need to stop grabbing so much!"), new Tag("Transport")),
            new Expense(new Description("ZARA Jacket"), new Amount("80"), new Date("30-06-2020"),
                EMPTY_REMARK, new Tag("Shopping")),
            new Expense(new Description("Ramen with Tyler"), new Amount("18.50"), new Date("29-06-2020"),
                new Remark("Tori King @ Tanjong Pagar"), new Tag("Food")),
            new Expense(new Description("Phone Bill Payment"), new Amount("35.90"), new Date("29-06-2020"),
                EMPTY_REMARK, new Tag("Bills")),
            new Expense(new Description("Grab to Supper"), new Amount("5"), new Date("28-06-2020"),
                EMPTY_REMARK, new Tag("Food")),
            new Expense(new Description("Movie with Felicia"), new Amount("14"), new Date("26-06-2020"),
                new Remark("Tenet was so confusing..."), new Tag("Girlfriend")),
            new Expense(new Description("Top-up Ez-Link"), new Amount("20"), new Date("25-06-2020"),
                EMPTY_REMARK, new Tag("Transport")),
            new Expense(new Description("Caifan"), new Amount("3.80"), new Date("25-06-2020"),
                EMPTY_REMARK, new Tag("Food"))
        };
    }

    public static ReadOnlyExpenseBook getSampleExpenseBook() {
        ExpenseBook sampleAb = new ExpenseBook();

        sampleAb.topupBudget(new Amount(500));
        for (Expense sampleExpense : getSampleExpenses()) {
            sampleAb.addExpense(sampleExpense);
        }
        return sampleAb;
    }

    public static AliasMap getSampleAliasMap() {
        AliasMap sampleMap = new AliasMap();

        sampleMap.addAlias(new AliasEntry("get", "find"));
        return sampleMap;
    }

    /**
     * Returns a tag set containing the list of strings given.
     * @Deprecated since v1.3
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
