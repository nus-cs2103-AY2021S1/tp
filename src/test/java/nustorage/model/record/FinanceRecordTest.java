package nustorage.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class FinanceRecordTest {

    @Test
    public void constructor_checkConstructorProperties_equals() {
        double sampleAmount = 1000;
        LocalDateTime sampleDateTime = LocalDateTime.now();
        boolean sampleHasInventory = true;
        int sampleID = 123456789;

        FinanceRecord withFirstConstructor = new FinanceRecord(sampleAmount);
        FinanceRecord withSecondConstructor = new FinanceRecord(sampleAmount, sampleHasInventory);
        FinanceRecord withThirdConstructor = new FinanceRecord(sampleID, sampleAmount, sampleHasInventory);
        FinanceRecord withFourthConstructor = new FinanceRecord(sampleAmount, sampleDateTime);
        FinanceRecord withFifthConstructor =
                new FinanceRecord(sampleID, sampleAmount, sampleDateTime, sampleHasInventory);
        FinanceRecord withSixthConstructor = new FinanceRecord(sampleAmount, sampleDateTime, sampleHasInventory);

        //First constructor test
        assertEquals(withFirstConstructor.getAmount(), 1000);
        assertEquals(withFirstConstructor.getID(), withFirstConstructor.hashCode());
        assertEquals(withFirstConstructor.getHasInventory(), false);

        //Second constructor test
        assertEquals(withSecondConstructor.getAmount(), 1000);
        assertEquals(withSecondConstructor.getID(), withSecondConstructor.hashCode());
        assertEquals(withSecondConstructor.getHasInventory(), true);

        //Third constructor test
        assertEquals(withThirdConstructor.getAmount(), 1000);
        assertEquals(withThirdConstructor.getID(), sampleID);
        assertEquals(withThirdConstructor.getHasInventory(), true);

        //Fourth constructor test
        assertEquals(withFourthConstructor.getAmount(), 1000);
        assertEquals(withFourthConstructor.getID(), withFourthConstructor.hashCode());
        assertEquals(withFourthConstructor.getHasInventory(), false);
        assertEquals(withFourthConstructor.getDateTime(), sampleDateTime);

        //Fifth constructor test
        assertEquals(withFifthConstructor.getAmount(), 1000);
        assertEquals(withFifthConstructor.getID(), sampleID);
        assertEquals(withFifthConstructor.getHasInventory(), true);
        assertEquals(withFifthConstructor.getDateTime(), sampleDateTime);

        //Sixth constructor test
        assertEquals(withSixthConstructor.getAmount(), 1000);
        assertEquals(withSixthConstructor.getID(), withSixthConstructor.hashCode());
        assertEquals(withSixthConstructor.getHasInventory(), true);
        assertEquals(withSixthConstructor.getDateTime(), sampleDateTime);
    }

    @Test
    public void hasSameData_financeRecordsWithSimilarData_success() {
        double sampleAmount = 2000;
        LocalDateTime sampleDateTime = LocalDateTime.now();
        boolean sampleHasInventory = true;
        int sampleID = 987654321;

        FinanceRecord firstFinanceRecord = new FinanceRecord(sampleAmount, sampleDateTime);
        FinanceRecord secondFinanceRecord =
                new FinanceRecord(sampleID, sampleAmount, sampleDateTime, sampleHasInventory);
        FinanceRecord thirdFinanceRecord = new FinanceRecord(sampleAmount, sampleDateTime, sampleHasInventory);

        assertTrue(firstFinanceRecord.hasSameData(secondFinanceRecord));
        assertTrue(secondFinanceRecord.hasSameData(firstFinanceRecord));

        assertTrue(firstFinanceRecord.hasSameData(thirdFinanceRecord));
        assertTrue(thirdFinanceRecord.hasSameData(firstFinanceRecord));

        assertTrue(secondFinanceRecord.hasSameData(thirdFinanceRecord));
        assertTrue(thirdFinanceRecord.hasSameData(secondFinanceRecord));
    }

    @Test
    public void hasSameData_financeRecordsWithDifferentData_failure() {
        double sampleAmount1 = 3000;
        double sampleAmount2 = 5;
        LocalDateTime sampleDateTime1 = LocalDateTime.of(2020, 9, 9, 23, 59);

        FinanceRecord firstFinanceRecord = new FinanceRecord(sampleAmount1);
        FinanceRecord secondFinanceRecord = new FinanceRecord(sampleAmount1, sampleDateTime1);
        FinanceRecord thirdFinanceRecord = new FinanceRecord(sampleAmount2);

        assertFalse(firstFinanceRecord.hasSameData(secondFinanceRecord));
        assertFalse(secondFinanceRecord.hasSameData(firstFinanceRecord));

        assertFalse(firstFinanceRecord.hasSameData(thirdFinanceRecord));
        assertFalse(thirdFinanceRecord.hasSameData(firstFinanceRecord));

        assertFalse(secondFinanceRecord.hasSameData(thirdFinanceRecord));
        assertFalse(thirdFinanceRecord.hasSameData(secondFinanceRecord));
    }

    @Test
    public void equals_test() {
        double sampleAmount = 1500;
        double anotherSampleAmount = 2000;
        LocalDateTime sampleDateTime = LocalDateTime.now();

        FinanceRecord firstRecord = new FinanceRecord(sampleAmount, sampleDateTime);
        FinanceRecord firstRecordCopy = new FinanceRecord(sampleAmount, sampleDateTime);
        FinanceRecord secondRecord = new FinanceRecord(anotherSampleAmount);

        //The same record should equal each other
        assertEquals(firstRecord, firstRecord);

        //Copies of the same record should not equal each other
        assertNotEquals(firstRecord, firstRecordCopy);

        //Different records should not equal each other
        assertNotEquals(firstRecord, secondRecord);
    }
}
