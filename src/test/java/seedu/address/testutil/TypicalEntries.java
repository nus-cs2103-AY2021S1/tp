package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ROSES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SUNFLOWER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CommonCents;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalEntries {

    public static final Expense BUYFLOWERPOTS = new ExpenseBuilder()
            .withDescription("bought flower pots")
            .withAmount("49.99")
            .withTags("tools", "29Sept").build();
    public static final Expense BUYSHOVEL = new ExpenseBuilder()
            .withDescription("bought shovel")
            .withAmount("20")
            .withTags("tools", "17Aug").build();
    public static final Expense PAYRENT = new ExpenseBuilder()
            .withDescription("paid rent")
            .withAmount("1000").build();
    public static final Revenue SELLFLOWERPOTS = new RevenueBuilder()
            .withDescription("sold flower pots")
            .withAmount("99.99")
            .withTags("10sold").build();
    public static final Revenue SELLFLOWERSEEDS = new RevenueBuilder()
            .withDescription("sold flower seed")
            .withAmount("30")
            .withTags("10sunflowerseeds", "5roseseeds").build();
    public static final Revenue SELLTOOLS = new RevenueBuilder()
            .withDescription("sold gardening tools")
            .withAmount("45.55")
            .withTags("shovel", "wateringCan", "sickle").build();

    // Manually added
    public static final Expense BUYSTRING = new ExpenseBuilder()
            .withDescription("Bought strings")
            .withAmount("9.75")
            .withTags("decoration").build();
    public static final Revenue SELLHANDICRAFT = new RevenueBuilder()
            .withDescription("Sold handicrafts")
            .withAmount("19.70")
            .withTags("plantDecorations", "potDecorations").build();

    // Manually added - Entries's details found in {@code CommandTestUtil}
    public static final Expense BUYROSESEEDS = new ExpenseBuilder()
            .withDescription(VALID_DESCRIPTION_EXPENSE)
            .withAmount(VALID_AMOUNT_EXPENSE)
            .withTags(VALID_TAG_ROSES).build();

    public static final Revenue SELLSUNFLOWER = new RevenueBuilder()
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
        cc.setAccount(acc);
        return cc;
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(BUYFLOWERPOTS, BUYSHOVEL, PAYRENT, BUYROSESEEDS));
    }

    public static List<Revenue> getTypicalRevenues() {
        return new ArrayList<>(Arrays.asList(SELLFLOWERPOTS, SELLTOOLS, SELLFLOWERSEEDS, SELLSUNFLOWER));
    }

}
