package seedu.address.testutil;

import static seedu.address.testutil.TypicalExpenditure.KFC_BANDITO;
import static seedu.address.testutil.TypicalExpenditure.KFC_CHEESE_FRIES;
import static seedu.address.testutil.TypicalExpenditure.KFC_ZINGER;
import static seedu.address.testutil.TypicalExpenditure.MC_CHICKEN;
import static seedu.address.testutil.TypicalExpenditure.MC_MUFFIN;
import static seedu.address.testutil.TypicalExpenditure.MC_SPICY;
import static seedu.address.testutil.TypicalExpenditure.SUBWAY_COLD_CUT_TRIO;
import static seedu.address.testutil.TypicalExpenditure.SUBWAY_COOKIE;
import static seedu.address.testutil.TypicalExpenditure.SUBWAY_SOUP;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.expenditure.ExpenditureList;

public class TypicalExpenditures {

    public static final ExpenditureList MC_DONALDS_EXPENDITURES = new ExpenditureList(
            new ArrayList<>(Arrays.asList(
                    MC_MUFFIN,
                    MC_CHICKEN,
                    MC_SPICY
            )));

    public static final ExpenditureList KFC_EXPENDITURES = new ExpenditureList(
            new ArrayList<>(Arrays.asList(
                    KFC_ZINGER,
                    KFC_CHEESE_FRIES,
                    KFC_BANDITO
            )));

    public static final ExpenditureList SUBWAY_EXPENDITURES = new ExpenditureList(
            new ArrayList<>(Arrays.asList(
                    SUBWAY_COLD_CUT_TRIO,
                    SUBWAY_COOKIE,
                    SUBWAY_SOUP
            )));

    private TypicalExpenditures() {} // prevents instantiation

    public static ExpenditureList getMcDonaldsExpenditures() {
        ExpenditureList expenditureList = new ExpenditureList();
        expenditureList.addToFront(TypicalExpenditure.getMcMuffinExpenditure());
        expenditureList.addToFront(TypicalExpenditure.getMcChickenExpenditure());
        expenditureList.addToFront(TypicalExpenditure.getMcSpicyExpenditure());
        return expenditureList;
    }

    public static ExpenditureList getKfcExpenditures() {
        ExpenditureList expenditureList = new ExpenditureList();
        expenditureList.addToFront(TypicalExpenditure.getKfcZingerExpenditure());
        expenditureList.addToFront(TypicalExpenditure.getKfcCheeseFriesExpenditure());
        expenditureList.addToFront(TypicalExpenditure.getKfcBanditoExpenditure());
        return expenditureList;
    }

    public static ExpenditureList getSubwayExpenditures() {
        ExpenditureList expenditureList = new ExpenditureList();
        expenditureList.addToFront(TypicalExpenditure.getSubwayColdCutTrioExpenditure());
        expenditureList.addToFront(TypicalExpenditure.getSubwayCookieExpenditure());
        expenditureList.addToFront(TypicalExpenditure.getSubwaySoupExpenditure());
        return expenditureList;
    }
}
