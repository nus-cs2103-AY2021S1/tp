package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.budget.Budget;
import seedu.address.model.expenditure.ExpenditureList;

public class TypicalBudget {

    public static final String MC_DONALDS_NAME = "McDonalds";
    public static final String MC_DONALDS_THRESHOLD = "100";
    public static final String MC_DONALDS_CREATED_ON = "2020-10-10T00:00:00.000000";

    public static final String KFC_NAME = "KFC";
    public static final String KFC_THRESHOLD = "80";
    public static final String KFC_CREATED_ON = "2020-10-09T00:00:00.000000";

    public static final String SUBWAY_NAME = "Subway";
    public static final String SUBWAY_THRESHOLD = "80";
    public static final String SUBWAY_CREATED_ON = "2020-10-08T00:00:00.000000";

    public static final Budget MC_DONALDS = new BudgetBuilder().withName(MC_DONALDS_NAME)
            .withThreshold(MC_DONALDS_THRESHOLD).withCreatedOn(MC_DONALDS_CREATED_ON)
            .withExpenditures(TypicalExpenditures.getMcDonaldsExpenditures()).build();

    public static final Budget KFC = new BudgetBuilder().withName(KFC_NAME)
            .withThreshold(KFC_THRESHOLD).withCreatedOn(KFC_CREATED_ON)
            .withExpenditures(TypicalExpenditures.getKfcExpenditures()).build();

    public static final Budget SUBWAY = new BudgetBuilder().withName(SUBWAY_NAME)
            .withThreshold(SUBWAY_THRESHOLD).withCreatedOn(SUBWAY_CREATED_ON)
            .withExpenditures(TypicalExpenditures.getSubwayExpenditures()).build();

    public static Budget getMcDonaldsBudget() {
        return new BudgetBuilder().withName(MC_DONALDS_NAME)
                .withThreshold(MC_DONALDS_THRESHOLD).withCreatedOn(MC_DONALDS_CREATED_ON)
                .withExpenditures(TypicalExpenditures.getMcDonaldsExpenditures()).build();
    }

    public static Budget getKfcBudget() {
        return new BudgetBuilder().withName(KFC_NAME)
                .withThreshold(KFC_THRESHOLD).withCreatedOn(KFC_CREATED_ON)
                .withExpenditures(TypicalExpenditures.getKfcExpenditures()).build();
    }

    public static Budget getSubwayBudget() {
        return new BudgetBuilder().withName(SUBWAY_NAME)
                .withThreshold(SUBWAY_THRESHOLD).withCreatedOn(SUBWAY_CREATED_ON)
                .withExpenditures(TypicalExpenditures.getSubwayExpenditures()).build();
    }
    public static Budget getEmptyMcDonaldsBudget() {
        return new BudgetBuilder().withName(MC_DONALDS_NAME)
                .withThreshold("100.0").withCreatedOn(LocalDateTime.now().toString())
                .withExpenditures(new ExpenditureList()).build();
    }

}
