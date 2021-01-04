package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.service.ServiceBuilder;


public class ServiceTest {

    @Test
    public void isSame() {
        // same object -> returns true
        assertTrue(LASH_LIFT.isSame(LASH_LIFT));

        // null -> returns false
        assertFalse(LASH_LIFT.isSame(null));

        // different Title and Amount -> returns false
        Service editedService = new ServiceBuilder(LASH_LIFT).withTitle("Edited").withAmount(25.5).build();
        assertFalse(LASH_LIFT.isSame(editedService));

        // different name -> returns false
        editedService = new ServiceBuilder(LASH_LIFT).withTitle("Edited").build();
        assertFalse(LASH_LIFT.isSame(editedService));

        // same title, same service code, same duration, same price, different instance -> returns true
        editedService = new ServiceBuilder(MANICURE).withTitle("Lash Lift")
            .withDuration(0.5)
            .withAmount(17.9)
            .withServiceCode("SC000").build();
        assertTrue(LASH_LIFT.isSame(editedService));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Service lashLiftCopy = new ServiceBuilder(LASH_LIFT).build();
        assertTrue(LASH_LIFT.equals(lashLiftCopy));

        // same object -> returns true
        assertTrue(LASH_LIFT.equals(LASH_LIFT));

        // null -> returns false
        assertFalse(LASH_LIFT.equals(null));

        // different type -> returns false
        assertFalse(LASH_LIFT.equals(5));

        // different services -> returns false
        assertFalse(LASH_LIFT.equals(MANICURE));

        // Same serviceCode -> returns True
        Service editedManicure = new ServiceBuilder(MANICURE).withServiceCode("SC000").build();
        assertTrue(LASH_LIFT.equals(editedManicure));

        // different serviceCode -> returns false
        Service editedLashLift = new ServiceBuilder(LASH_LIFT).withServiceCode("SC005").build();
        assertFalse(LASH_LIFT.equals(editedLashLift));
    }

    @Test
    public void addServiceCode() {
        // Prior to adding service code
        assertTrue(LASH_LIFT.getServiceCode().value.equals("SC000"));
        // After adding service code
        Service editedService = new ServiceBuilder(LASH_LIFT).build().addServiceCode("SC005");
        assertTrue(editedService.getServiceCode().value == "SC005");
    }
}
