package seedu.cc.testutil;

import static seedu.cc.logic.commands.CommandTestUtil.VALID_AMOUNT_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_AMOUNT_REVENUE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXPENSE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REVENUE;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_ROSES;
import static seedu.cc.logic.commands.CommandTestUtil.VALID_TAG_SUNFLOWER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.cc.model.CommonCents;
import seedu.cc.model.account.Account;
import seedu.cc.model.account.Name;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;

/**
 * A utility class containing a list of {@code Entry} objects to be used in tests.
 */
public class TypicalEntries {
    public static final Expense BUY_FLOWER_POTS = new ExpenseBuilder()
            .withDescription("bought flower pots")
            .withAmount("49.99")
            .withTags("tools", "29Sept").build();
    public static final Expense BUY_SHOVEL = new ExpenseBuilder()
            .withDescription("bought shovel")
            .withAmount("20")
            .withTags("tools", "17Aug").build();
    public static final Expense PAY_RENT = new ExpenseBuilder()
            .withDescription("paid rent")
            .withAmount("1000").build();
    public static final Revenue SELL_FLOWER_POTS = new RevenueBuilder()
            .withDescription("sold flower pots")
            .withAmount("99.99")
            .withTags("10sold").build();
    public static final Revenue SELL_FLOWER_SEEDS = new RevenueBuilder()
            .withDescription("sold flower seed")
            .withAmount("30")
            .withTags("10sunflowerseeds", "5roseseeds").build();
    public static final Revenue SELL_TOOLS = new RevenueBuilder()
            .withDescription("sold gardening tools")
            .withAmount("45.55")
            .withTags("shovel", "wateringCan", "sickle").build();

    // Manually added
    public static final Expense BUY_STRING = new ExpenseBuilder()
            .withDescription("Bought strings")
            .withAmount("9.75")
            .withTags("decoration").build();
    public static final Revenue SELL_HANDICRAFT = new RevenueBuilder()
            .withDescription("Sold handicrafts")
            .withAmount("19.70")
            .withTags("plantDecorations", "potDecorations").build();

    // Manually added - Entries's details found in {@code CommandTestUtil}
    public static final Expense BUY_ROSE_SEEDS = new ExpenseBuilder()
            .withDescription(VALID_DESCRIPTION_EXPENSE)
            .withAmount(VALID_AMOUNT_EXPENSE)
            .withTags(VALID_TAG_ROSES).build();

    public static final Revenue SELL_SUNFLOWER = new RevenueBuilder()
            .withDescription(VALID_DESCRIPTION_REVENUE)
            .withAmount(VALID_AMOUNT_REVENUE)
            .withTags(VALID_TAG_SUNFLOWER)
            .build();

    private static final String GENERAL_ACC_NAME = "General account";

    private TypicalEntries() {} // prevents instantiation

    /**
     * Returns an {@code Account} with all the typical expenses and revenues.
     */
    public static Account getTypicalAccount() {
        Account acc = new Account(new Name(GENERAL_ACC_NAME));

        for (Expense expense: getTypicalExpenses()) {
            acc.addExpense(expense);
        }

        for (Revenue revenue: getTypicalRevenues()) {
            acc.addRevenue(revenue);
        }

        return acc;
    }

    /**
     * Returns an {@code CommonCents} with all the typical expenses and revenues.
     */
    public static CommonCents getTypicalCommonCents() {
        CommonCents cc = new CommonCents();
        Account acc = getTypicalAccount();
        cc.addAccount(acc);
        return cc;
    }


    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(BUY_FLOWER_POTS, BUY_SHOVEL, PAY_RENT, BUY_ROSE_SEEDS));
    }

    public static List<Revenue> getTypicalRevenues() {
        return new ArrayList<>(Arrays.asList(SELL_FLOWER_POTS, SELL_TOOLS, SELL_FLOWER_SEEDS, SELL_SUNFLOWER));
    }

}
