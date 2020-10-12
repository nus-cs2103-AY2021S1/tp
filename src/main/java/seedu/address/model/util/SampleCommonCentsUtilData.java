package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.CommonCents;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Amount;
import seedu.address.model.account.entry.Description;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.ExpenseList;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.account.entry.RevenueList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code CommonCents} with sample data.
 */
public class SampleCommonCentsUtilData {
    public static Account getSampleAccount() {
        Account acc = new Account(new Name("General"));
        RevenueList accProfits = new RevenueList();
        accProfits.add(new Revenue(new Description("earrings sales"),
                new Amount("13.50"), getTagSet("craft", "accessories")));
        accProfits.add(new Revenue(new Description("phone cases sales"),
                new Amount("15.70"), getTagSet("phone", "accessories")));
        ExpenseList accExpenseList = new ExpenseList();
        accExpenseList.add(new Expense(new Description("Watercolours"),
                new Amount("12.10"), getTagSet("art", "colours")));
        accExpenseList.add(new Expense(new Description("canvas"),
                new Amount("20.15"), getTagSet("art")));
        /*
        Account acc2 = new Account(new Name("Lulu - nonbiz acc"));
        RevenueList acc2Profits = new RevenueList();
        ExpenseList acc2ExpenseList = new ExpenseList();
        accExpenseList.add(new Expense(new Description("lunch"),
                new Amount("5.45"), getTagSet("food", "hawker")));
        accExpenseList.add(new Expense(new Description("dinner at Morganfield's"),
                new Amount("24.45"), getTagSet("food", "restaurant", "Morganfields")));
        */
        return acc;
    }

    public static ReadOnlyCommonCents getSampleCommonCents() {
        CommonCents sampleCc = new CommonCents();
        sampleCc.addAccount(getSampleAccount());

        return sampleCc;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
