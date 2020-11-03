package seedu.expense.model.util;

import static seedu.expense.model.ExpenseBook.DEFAULT_TAG;

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
    private static final Tag TAG_GIRLFRIEND = new Tag("Girlfriend");
    private static final Tag TAG_FOOD = new Tag("Food");
    private static final Tag TAG_TRANSPORT = new Tag("Transport");
    private static final Tag TAG_SHOPPING = new Tag("Shopping");

    public static Expense[] getSampleExpenses() {
        return new Expense[]{
            new Expense(new Description("Felicia's Birthday"), new Amount("140.00"), new Date("02-07-2020"),
                new Remark("Birthday surprise with friends + birthday presents + birthday dinner"),
                TAG_GIRLFRIEND),
            new Expense(new Description("Lunch with Hostel Mates"), new Amount("13"), new Date("01-07-2020"),
                EMPTY_REMARK, TAG_FOOD),
            new Expense(new Description("Grab Home"), new Amount("15"), new Date("01-07-2020"),
                new Remark("Need to stop grabbing so much!"), TAG_TRANSPORT),
            new Expense(new Description("ZARA Jacket"), new Amount("80"), new Date("30-06-2020"),
                EMPTY_REMARK, TAG_SHOPPING),
            new Expense(new Description("Ramen with Tyler"), new Amount("18.50"), new Date("29-06-2020"),
                new Remark("Tori King @ Tanjong Pagar"), TAG_FOOD),
            new Expense(new Description("Phone Bill Payment"), new Amount("35.90"), new Date("29-06-2020"),
                EMPTY_REMARK, DEFAULT_TAG),
            new Expense(new Description("Grab to Supper"), new Amount("5"), new Date("28-06-2020"),
                EMPTY_REMARK, TAG_FOOD),
            new Expense(new Description("Movie with Felicia"), new Amount("14"), new Date("26-06-2020"),
                new Remark("Tenet was so confusing..."), TAG_GIRLFRIEND),
            new Expense(new Description("Top-up Ez-Link"), new Amount("20"), new Date("25-06-2020"),
                EMPTY_REMARK, TAG_TRANSPORT),
            new Expense(new Description("Caifan"), new Amount("3.80"), new Date("25-06-2020"),
                EMPTY_REMARK, TAG_FOOD)
        };
    }

    public static ReadOnlyExpenseBook getSampleExpenseBook() {
        ExpenseBook sampleEb = new ExpenseBook();

        sampleEb.addCategory(TAG_GIRLFRIEND);
        sampleEb.addCategory(TAG_SHOPPING);
        sampleEb.addCategory(TAG_FOOD);
        sampleEb.addCategory(TAG_TRANSPORT);

        sampleEb.getBudgets().topupBudget(new Amount("40"));
        sampleEb.topupCategoryBudget(TAG_FOOD, new Amount("30"));
        sampleEb.topupCategoryBudget(TAG_GIRLFRIEND, new Amount("150"));
        sampleEb.topupCategoryBudget(TAG_SHOPPING, new Amount("120"));
        sampleEb.topupCategoryBudget(TAG_TRANSPORT, new Amount("40"));

        for (Expense sampleExpense : getSampleExpenses()) {
            sampleEb.addExpense(sampleExpense);
        }

        return sampleEb;
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
