package seedu.internhunter.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;
import static seedu.internhunter.testutil.profile.SampleProfileItems.getSampleProfileItemList;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_DATA_LIST;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_LIST_STRING;
import static seedu.internhunter.ui.GuardClauseUi.IS_EMPTY_STRING;
import static seedu.internhunter.ui.GuardClauseUi.IS_SAME_STRING;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.profile.ProfileItem;

public class GuardClauseUiTest {

    private static final String EMPTY_STRING = "";
    private static final String NON_EMPTY_STRING = "a";
    private static final String EMPTY_LIST_STRING = "[]";
    private static final String NON_EMPTY_LIST_STRING = "[a]";
    private ItemList<ApplicationItem> applicationItemList;
    private ItemList<ProfileItem> profileItemList;
    private ItemList<CompanyItem> companyItemList;

    @BeforeEach
    public void setUp() {
        applicationItemList = getSampleApplicationItemList();
        profileItemList = getSampleProfileItemList();
        companyItemList = getSampleCompanyList();
    }

    @Test
    public void isEmptyListString_true_success() {
        // string representation of an empty list
        assertTrue(IS_EMPTY_LIST_STRING.test(EMPTY_LIST_STRING));
    }

    @Test
    public void isEmptyListString_false_success() {
        // empty string
        assertFalse(IS_EMPTY_LIST_STRING.test(EMPTY_STRING));

        // string representation of a list with 1 element
        assertFalse(IS_EMPTY_LIST_STRING.test(NON_EMPTY_LIST_STRING));

        // null -> false
        assertFalse(IS_EMPTY_LIST_STRING.test(null));
    }

    @Test
    public void isEmptyDataList_true_success() {
        assertTrue(IS_EMPTY_DATA_LIST.test(FXCollections.observableArrayList()));
    }

    @Test
    public void isEmptyDataList_false_success() {
        // application item list
        assertFalse(IS_EMPTY_DATA_LIST.test(applicationItemList.getItemList()));

        // profile item list
        assertFalse(IS_EMPTY_DATA_LIST.test(profileItemList.getItemList()));

        // company item list
        assertFalse(IS_EMPTY_DATA_LIST.test(companyItemList.getItemList()));

        // null test
        assertFalse(IS_EMPTY_DATA_LIST.test(null));
    }

    @Test
    public void isEmptyString_true_success() {
        assertTrue(IS_EMPTY_STRING.test(EMPTY_STRING));
    }

    @Test
    public void isEmptyString_false_success() {
        // non empty string
        assertFalse(IS_EMPTY_STRING.test(NON_EMPTY_STRING));

        // empty list string representation
        assertFalse(IS_EMPTY_STRING.test(EMPTY_LIST_STRING));

        // non empty list string representation
        assertFalse(IS_EMPTY_STRING.test(NON_EMPTY_LIST_STRING));

        // null test
        assertFalse(IS_EMPTY_STRING.test(null));
    }

    @Test
    public void isSameString_true_success() {
        // empty string
        assertTrue(IS_SAME_STRING.test(EMPTY_STRING, EMPTY_STRING));

        // non empty string
        assertTrue(IS_SAME_STRING.test(NON_EMPTY_STRING, NON_EMPTY_STRING));
    }

    @Test
    public void isSameString_false_success() {
        // empty string against non empty string
        assertFalse(IS_SAME_STRING.test(EMPTY_STRING, NON_EMPTY_STRING));

        // empty list string against non empty list string
        assertFalse(IS_SAME_STRING.test(EMPTY_LIST_STRING, NON_EMPTY_LIST_STRING));

        // one null case in first param
        assertFalse(IS_SAME_STRING.test(null, EMPTY_STRING));

        // one null case in second param
        assertFalse(IS_SAME_STRING.test(EMPTY_STRING, null));

        // both null
        assertFalse(IS_SAME_STRING.test(null, null));
    }

}
