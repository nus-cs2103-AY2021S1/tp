package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Nusave;
import seedu.address.model.ReadOnlyNusave;
import seedu.address.model.budget.Budget;
import seedu.address.model.budget.Name;
import seedu.address.model.budget.Threshold;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.ExpenditureList;
import seedu.address.model.expenditure.Price;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Budget[] getSampleBudgets() {
        return new Budget[] {
            new Budget(new Name("Temasek Hall Basketball"),
                    new seedu.address.model.budget.Date("2020-10-10T00:00:00.000000"),
                    new Threshold("500").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("Temasek Hall Marketing"),
                    new seedu.address.model.budget.Date("2020-10-11T00:00:00.000000"),
                    new Threshold("1000").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("October Budget"),
                    new seedu.address.model.budget.Date("2020-10-10T00:00:00.000000"),
                    new Threshold("300").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("Monthly Bills"),
                    new seedu.address.model.budget.Date("2020-10-12T00:00:00.000000"),
                    new Threshold("200").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("NUS Hackers Monthly Budget"),
                    new seedu.address.model.budget.Date("2020-10-18T00:00:00.000000"),
                    new Threshold("600").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("NUS Fintech Society Monthly Budget"),
                    new seedu.address.model.budget.Date("2020-10-15T00:00:00.000000"),
                    new Threshold("900").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("Tembusu College Annual DnD Fund"),
                    new seedu.address.model.budget.Date("2020-10-11T00:00:00.000000"),
                    new Threshold("400").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures())))),
            new Budget(new Name("Personal Expenditure"),
                    new seedu.address.model.budget.Date("2020-10-01T00:00:00.000000"),
                    new Threshold("200").toOptional(),
                    new ExpenditureList(new ArrayList<>(Arrays.asList(getSampleExpenditures()))))
        };
    }

    public static Expenditure[] getSampleExpenditures() {
        return new Expenditure[]{
            new Expenditure(new seedu.address.model.expenditure.Name("Shirt"),
                    new Price("85.50"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "White")),
            new Expenditure(new seedu.address.model.expenditure.Name("Pants"),
                    new Price("100.50"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "Black")),
            new Expenditure(new seedu.address.model.expenditure.Name("Belt"),
                    new Price("50.90"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "Brown")),
            new Expenditure(new seedu.address.model.expenditure.Name("Shoes"),
                    new Price("89.90"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "Black")),
            new Expenditure(new seedu.address.model.expenditure.Name("Gloves"),
                    new Price("10.90"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "Black")),
            new Expenditure(new seedu.address.model.expenditure.Name("Watch"),
                    new Price("369.90"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "Black")),
            new Expenditure(new seedu.address.model.expenditure.Name("Socks"),
                    new Price("3.00"), new Date("2020-10-10T00:00:00.000000"), getTagSet("Apparel", "Black")),
        };
    }

    public static ReadOnlyNusave getSampleNusave() {
        Nusave sampleNusave = new Nusave();
        for (Budget budget : getSampleBudgets()) {
            sampleNusave.addBudgetToFront(budget);
        }
        return sampleNusave;
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
