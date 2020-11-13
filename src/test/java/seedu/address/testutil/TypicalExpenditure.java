package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.expenditure.Expenditure;

public class TypicalExpenditure {
    public static final String MC_MUFFIN_NAME = "McMuffin";
    public static final String MC_MUFFIN_PRICE = "4.50";
    public static final String MC_MUFFIN_CREATED_ON = "2020-10-02T00:00:00.000000";

    public static final String MC_CHICKEN_NAME = "McChicken";
    public static final String MC_CHICKEN_PRICE = "2.00";
    public static final String MC_CHICKEN_CREATED_ON = "2020-10-09T00:00:00.000000";

    public static final String MC_SPICY_NAME = "McSpicy";
    public static final String MC_SPICY_PRICE = "7.00";
    public static final String MC_SPICY_CREATED_ON = "2020-10-08T00:00:00.000000";

    public static final String MCDONALDS_TOTAL_PRICE = "13.50";

    public static final String KFC_ZINGER_NAME = "Zinger";
    public static final String KFC_ZINGER_PRICE = "8.00";
    public static final String KFC_ZINGER_CREATED_ON = "2020-10-07T00:00:00.000000";

    public static final String KFC_CHEESE_FRIES_NAME = "Cheese Fries";
    public static final String KFC_CHEESE_FRIES_PRICE = "4.00";
    public static final String KFC_CHEESE_FRIES_CREATED_ON = "2020-10-06T00:00:00.000000";

    public static final String KFC_BANDITO_NAME = "Bandito";
    public static final String KFC_BANDITO_PRICE = "6.50";
    public static final String KFC_BANDITO_CREATED_ON = "2020-10-05T00:00:00.000000";

    public static final String KFC_TOTAL_PRICE = "18.50";

    public static final String SUBWAY_COLD_CUT_TRIO_NAME = "Cold Cut Trio";
    public static final String SUBWAY_COLD_CUT_TRIO_PRICE = "7.50";
    public static final String SUBWAY_COLD_CUT_TRIO_CREATED_ON = "2020-10-04T00:00:00.000000";

    public static final String SUBWAY_COOKIE_NAME = "Cookie";
    public static final String SUBWAY_COOKIE_PRICE = "1.50";
    public static final String SUBWAY_COOKIE_CREATED_ON = "2020-10-03T00:00:00.000000";

    public static final String SUBWAY_SOUP_NAME = "Clam Chowder";
    public static final String SUBWAY_SOUP_PRICE = "3.00";
    public static final String SUBWAY_SOUP_CREATED_ON = "2020-10-02T00:00:00.000000";

    public static final String SUBWAY_TOTAL_PRICE = "12.00";

    public static final Expenditure MC_MUFFIN = new ExpenditureBuilder().withName(MC_MUFFIN_NAME)
            .withPrice(MC_MUFFIN_PRICE).withCreatedOn(MC_MUFFIN_CREATED_ON).build();
    public static final Expenditure MC_CHICKEN = new ExpenditureBuilder().withName(MC_CHICKEN_NAME)
            .withPrice(MC_CHICKEN_PRICE).withCreatedOn(MC_CHICKEN_CREATED_ON).build();
    public static final Expenditure MC_SPICY = new ExpenditureBuilder().withName(MC_SPICY_NAME)
            .withPrice(MC_SPICY_PRICE).withCreatedOn(MC_SPICY_CREATED_ON).build();

    public static final Expenditure KFC_ZINGER = new ExpenditureBuilder().withName(KFC_ZINGER_NAME)
            .withPrice(KFC_ZINGER_PRICE).withCreatedOn(KFC_ZINGER_CREATED_ON).build();
    public static final Expenditure KFC_CHEESE_FRIES = new ExpenditureBuilder().withName(KFC_CHEESE_FRIES_NAME)
            .withPrice(KFC_CHEESE_FRIES_PRICE).withCreatedOn(KFC_CHEESE_FRIES_CREATED_ON).build();
    public static final Expenditure KFC_BANDITO = new ExpenditureBuilder().withName(KFC_BANDITO_NAME)
            .withPrice(KFC_BANDITO_PRICE).withCreatedOn(KFC_BANDITO_CREATED_ON).build();

    public static final Expenditure SUBWAY_COLD_CUT_TRIO = new ExpenditureBuilder().withName(SUBWAY_COLD_CUT_TRIO_NAME)
            .withPrice(SUBWAY_COLD_CUT_TRIO_PRICE).withCreatedOn(SUBWAY_COLD_CUT_TRIO_CREATED_ON).build();
    public static final Expenditure SUBWAY_COOKIE = new ExpenditureBuilder().withName(SUBWAY_COOKIE_NAME)
            .withPrice(SUBWAY_COOKIE_PRICE).withCreatedOn(SUBWAY_COOKIE_CREATED_ON).build();
    public static final Expenditure SUBWAY_SOUP = new ExpenditureBuilder().withName(SUBWAY_SOUP_NAME)
            .withPrice(SUBWAY_SOUP_PRICE).withCreatedOn(SUBWAY_SOUP_CREATED_ON).build();

    public static Expenditure getMcMuffinExpenditure() {
        return new ExpenditureBuilder().withName(MC_MUFFIN_NAME)
                .withPrice(MC_MUFFIN_PRICE).withCreatedOn(MC_MUFFIN_CREATED_ON).build();
    }

    public static Expenditure getMcMuffinExpenditureWithTags() {
        return new ExpenditureBuilder().withName(MC_MUFFIN_NAME)
                .withPrice(MC_MUFFIN_PRICE).withCreatedOn(LocalDateTime.now().toString())
                .withTags("breakfast", "delicious").build();
    }

    public static Expenditure getMcMuffinExpenditureWithCurrentCreatedOn() {
        return new ExpenditureBuilder().withName(MC_MUFFIN_NAME)
                .withPrice(MC_MUFFIN_PRICE).withCreatedOn(LocalDateTime.now().toString()).build();
    }

    public static Expenditure getMcChickenExpenditure() {
        return new ExpenditureBuilder().withName(MC_CHICKEN_NAME)
                .withPrice(MC_CHICKEN_PRICE).withCreatedOn(MC_CHICKEN_CREATED_ON).build();
    }

    public static Expenditure getMcSpicyExpenditure() {
        return new ExpenditureBuilder().withName(MC_SPICY_NAME)
                .withPrice(MC_SPICY_PRICE).withCreatedOn(MC_SPICY_CREATED_ON).build();
    }

    public static Expenditure getKfcZingerExpenditure() {
        return new ExpenditureBuilder().withName(KFC_ZINGER_NAME)
                .withPrice(KFC_ZINGER_PRICE).withCreatedOn(KFC_ZINGER_CREATED_ON).build();
    }

    public static Expenditure getKfcCheeseFriesExpenditure() {
        return new ExpenditureBuilder().withName(KFC_CHEESE_FRIES_NAME)
                .withPrice(KFC_CHEESE_FRIES_PRICE).withCreatedOn(KFC_CHEESE_FRIES_CREATED_ON).build();
    }

    public static Expenditure getKfcBanditoExpenditure() {
        return new ExpenditureBuilder().withName(KFC_BANDITO_NAME)
                .withPrice(KFC_BANDITO_PRICE).withCreatedOn(KFC_BANDITO_CREATED_ON).build();
    }

    public static Expenditure getSubwayColdCutTrioExpenditure() {
        return new ExpenditureBuilder().withName(SUBWAY_COLD_CUT_TRIO_NAME)
                .withPrice(SUBWAY_COLD_CUT_TRIO_PRICE).withCreatedOn(SUBWAY_COLD_CUT_TRIO_CREATED_ON).build();
    }

    public static Expenditure getSubwayCookieExpenditure() {
        return new ExpenditureBuilder().withName(SUBWAY_COOKIE_NAME)
                .withPrice(SUBWAY_COOKIE_PRICE).withCreatedOn(SUBWAY_COOKIE_CREATED_ON).build();
    }

    public static Expenditure getSubwaySoupExpenditure() {
        return new ExpenditureBuilder().withName(SUBWAY_SOUP_NAME)
                .withPrice(SUBWAY_SOUP_PRICE).withCreatedOn(SUBWAY_SOUP_CREATED_ON).build();
    }
}
