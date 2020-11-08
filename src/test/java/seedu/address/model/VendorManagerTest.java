package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVendors.ALICE;
import static seedu.address.testutil.TypicalVendors.BENSON;
import static seedu.address.testutil.TypicalVendors.ELLE;
import static seedu.address.testutil.TypicalVendors.getTypicalVendorManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;
import seedu.address.model.vendor.exceptions.DuplicateVendorException;
import seedu.address.testutil.VendorBuilder;

public class VendorManagerTest {

    private final VendorManager vendorManager = new VendorManager();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), vendorManager.getVendorList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> vendorManager.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyVendorManager_replacesData() {
        VendorManager newData = getTypicalVendorManager();
        vendorManager.resetData(newData);
        assertEquals(newData, vendorManager);
    }

    @Test
    public void resetData_withDuplicateVendors_throwsDuplicateVendorException() {
        // Two vendors with the same identity fields
        Vendor editedAlice = new VendorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Vendor> newVendors = Arrays.asList(ALICE, editedAlice);
        VendorManagerStub newData = new VendorManagerStub(newVendors);

        assertThrows(DuplicateVendorException.class, () -> vendorManager.resetData(newData));
    }

    @Test
    public void hasVendor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> vendorManager.hasVendor(null));
    }

    @Test
    public void hasVendor_vendorNotInVendorManager_returnsFalse() {
        assertFalse(vendorManager.hasVendor(ALICE));
    }

    @Test
    public void hasVendor_vendorInVendorManager_returnsTrue() {
        vendorManager.addVendor(ALICE);
        assertTrue(vendorManager.hasVendor(ALICE));
    }

    @Test
    public void hasVendor_vendorWithSameIdentityFieldsInVendorManager_returnsTrue() {
        vendorManager.setVendors(new ArrayList<>());
        vendorManager.addVendor(ALICE);
        Vendor editedAlice = new VendorBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(vendorManager.hasVendor(editedAlice));
    }

    @Test
    public void getVendorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> vendorManager.getVendorList().remove(0));
    }

    @Test
    public void selectVendorIndex_validIndex_success() {
        assertEquals(this.vendorManager.getVendorIndex(), -1);
        VendorManager vendorManager = getTypicalVendorManager();
        vendorManager.selectVendor(1);
        assertEquals(vendorManager.getVendorIndex(), 1);
    }

    @Test
    public void selectVendor_validIndex_success() {
        VendorManager vendorManager1 = getTypicalVendorManager();
        vendorManager1.selectVendor(1);
        assertEquals(vendorManager1.getSelectedVendor(), BENSON);

        VendorManager vendorManager2 = getTypicalVendorManager();
        vendorManager2.selectVendor(4);
        assertEquals(vendorManager2.getSelectedVendor(), ELLE);
    }

    @Test
    public void setVendor_validVendor_success() {
        VendorManager vendorManager1 = new VendorManager();
        vendorManager1.addVendor(ELLE);
        vendorManager1.setVendor(ELLE, ALICE);
        assertFalse(vendorManager1.hasVendor(ELLE));
        assertTrue(vendorManager1.hasVendor(ALICE));
    }

    /**
     * A stub ReadOnlyVendorManager whose vendors list can violate interface constraints.
     */
    private static class VendorManagerStub implements ReadOnlyVendorManager {
        private final ObservableList<Vendor> vendors = FXCollections.observableArrayList();

        VendorManagerStub(Collection<Vendor> vendors) {
            this.vendors.setAll(vendors);
        }

        @Override
        public ObservableList<Vendor> getVendorList() {
            return vendors;
        }

        @Override
        public int getVendorIndex() {
            return 0;
        }
    }

}
