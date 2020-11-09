package seedu.cc.model.account;

import static seedu.cc.testutil.Assert.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for UniqueAccountList.
 * Tests for uniqueness of accounts will be implemented in future as for now, we assume only one account.
 */
public class UniqueAccountListTest {
    private final UniqueAccountList uniqueAccountList = new UniqueAccountList();

    @Test
    public void contains_nullAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountList.contains(null));
    }

    // To be implemented
    @Test
    public void contains_accountNotInList_returnsFalse() {

    }

    // To be implemented
    @Test
    public void contains_accountInList_returnsTrue() {

    }

    // To be implemented
    @Test
    public void contains_accountWithSameIdentityFieldsInList_returnsTrue() {

    }

    // To be implemented
    @Test
    public void add_nullAccount_throwsNullPointerException() {

    }

    // To be implemented
    @Test
    public void add_duplicateAccount_throwsDuplicateAccountException() {

    }

    // To be implemented
    @Test
    public void setAccount_nullTargetAccountAndNonNullEditedAccount_throwsNullPointerException() {

    }

    // To be implemented
    @Test
    public void setAccount_nonNullTargetAccountAndNullEditedAccount_throwsNullPointerException() {

    }

    @Test
    public void setAccount_nullEditedAccount_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountList.setAccount(null));
    }

    // To be implemented
    @Test
    public void setAccount_targetAccountNotInList_throwsAccountNotFoundException() {

    }

    // To be implemented
    @Test
    public void setAccount_editedAccountIsSameAccount_success() {

    }

    // To be implemented
    @Test
    public void setAccount_editedAccountHasSameIdentity_success() {

    }

    // To be implemented
    @Test
    public void setAccount_editedAccountHasDifferentIdentity_success() {

    }

    // To be implemented
    @Test
    public void setAccount_editedAccountHasNonUniqueIdentity_throwsDuplicateAccountException() {

    }

    // To be implemented
    @Test
    public void remove_nullAccount_throwsNullPointerException() {

    }

    // To be implemented
    @Test
    public void remove_accountDoesNotExist_throwsAccountNotFoundException() {

    }

    // To be implemented
    @Test
    public void remove_existingAccount_removesAccount() {

    }

    @Test
    public void setAccounts_nullUniqueAccountList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountList.setAccounts((UniqueAccountList) null));
    }

    // To be implemented
    @Test
    public void setAccounts_uniqueAccountList_replacesOwnListWithProvidedUniqueAccountList() {

    }

    @Test
    public void setAccounts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAccountList.setAccounts((List<Account>) null));
    }

    // To be implemented
    @Test
    public void setAccounts_list_replacesOwnListWithProvidedList() {

    }

    // To be implemented
    @Test
    public void setAccounts_listWithDuplicateAccounts_throwsDuplicateAccountException() {

    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAccountList.asUnmodifiableObservableList().remove(0));
    }

}
