package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getSampleBudgets;
import static seedu.address.model.util.SampleDataUtil.getSampleExpenditures;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.Nusave;
import seedu.address.model.budget.Budget;
import seedu.address.model.budget.Name;
import seedu.address.model.budget.Threshold;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.ExpenditureList;
import seedu.address.model.expenditure.Price;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {
    private Budget[] sampleBudget = new Budget[] {
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

    private Expenditure[] sampleExpenditures = new Expenditure[] {
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

    @Test
    void tagSetTest() {
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("Apparel"));
        tagSet.add(new Tag("Black"));

        assertEquals(tagSet, SampleDataUtil.getTagSet("Apparel", "Black"));
    }

    @Test
    void getSampleExpendituresTest() {
        Expenditure[] expected = sampleExpenditures;
        Expenditure[] actual = getSampleExpenditures();
        assertExpenditureContentEquals(expected, actual);
    }

    @Test
    void getSampleBudgetsTest() {
        Budget[] expected = sampleBudget;
        Budget[] actual = getSampleBudgets();
        assertBudgetContentEquals(expected, actual);
    }

    @Test
    void getSampleNusaveTest() {
        Nusave expected = new Nusave();
        for (Budget budget : sampleBudget) {
            expected.addBudgetToFront(budget);
        }

        assertEquals(expected, SampleDataUtil.getSampleNusave());
    }

    void assertExpenditureContentEquals(Expenditure[] expected, Expenditure[] actual) {
        assertEquals(expected.length, actual.length);
        int size = expected.length;

        for (int i = 0; i < size; i++) {
            Expenditure expectedExpenditure = expected[i];
            Expenditure actualExpenditure = actual[i];
            assertEquals(true, expectedExpenditure.contentEquals(actualExpenditure));
        }
    }

    void assertBudgetContentEquals(Budget[] expected, Budget[] actual) {
        assertEquals(expected.length, actual.length);
        int size = expected.length;

        for (int i = 0; i < size; i++) {
            Budget expectedBudget = expected[i];
            Budget actualBudget = actual[i];
            assertEquals(true, expectedBudget.contentEquals(actualBudget));
        }
    }
}
