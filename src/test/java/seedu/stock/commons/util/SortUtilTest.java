package seedu.stock.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.testutil.TypicalStocks.getTypicalStocks;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.stock.model.stock.Stock;

public class SortUtilTest {
    private List<Stock> toTestComparator;

    @BeforeEach
    public void setUp() {
        toTestComparator = getTypicalStocks();
    }

    @Test
    public void getFieldDescription() {
        // EP: invalid fields
        assertEquals("", SortUtil.getFieldDescription(SortUtil.Field.BOOKMARK));

        // EP: name field
        assertEquals("name", SortUtil.getFieldDescription(SortUtil.Field.NAME));

        // EP: quantity field
        assertEquals("quantity", SortUtil.getFieldDescription(SortUtil.Field.QUANTITY));

        // EP: source field
        assertEquals("source", SortUtil.getFieldDescription(SortUtil.Field.SOURCE));

        // EP: location field
        assertEquals("location", SortUtil.getFieldDescription(SortUtil.Field.LOCATION));

        // EP: serial number field
        assertEquals("serial number", SortUtil.getFieldDescription(SortUtil.Field.SERIALNUMBER));
    }

    @Test
    public void generateComparator_nameComparator_success() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getName().toString();
                String nameB = b.getName().toString();
                return nameA.compareTo(nameB);
            }
        };
        Comparator<Stock> nameComparator = SortUtil.generateComparator(SortUtil.Field.NAME);
        copy.sort(expectedComparator);
        toTestComparator.sort(nameComparator);
        assertEquals(copy, toTestComparator);
    }

    @Test
    public void generateComparator_quantityComparator_success() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                BigInteger quantityA = new BigInteger(a.getQuantity().toString());
                BigInteger quantityB = new BigInteger(b.getQuantity().toString());
                return quantityA.compareTo(quantityB);
            }
        };
        Comparator<Stock> quantityComparator = SortUtil.generateComparator(SortUtil.Field.QUANTITY);
        copy.sort(expectedComparator);
        toTestComparator.sort(quantityComparator);
        assertEquals(copy, toTestComparator);
    }

    @Test
    public void generateComparator_sourceComparator_success() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                String sourceA = a.getSource().toString();
                String sourceB = b.getSource().toString();
                return sourceA.compareTo(sourceB);
            }
        };
        Comparator<Stock> sourceComparator = SortUtil.generateComparator(SortUtil.Field.SOURCE);
        copy.sort(expectedComparator);
        toTestComparator.sort(sourceComparator);
        assertEquals(copy, toTestComparator);
    }

    @Test
    public void generateComparator_locationComparator_success() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                String locationA = a.getLocation().toString();
                String locationB = b.getLocation().toString();
                return locationA.compareTo(locationB);
            }
        };
        Comparator<Stock> locationComparator = SortUtil.generateComparator(SortUtil.Field.LOCATION);
        copy.sort(expectedComparator);
        toTestComparator.sort(locationComparator);
        assertEquals(copy, toTestComparator);
    }

    @Test
    public void generateComparator_serialNumberComparator_success() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                String serialNumberA = a.getSerialNumber().toString();
                String serialNumberB = b.getSerialNumber().toString();
                return serialNumberA.compareTo(serialNumberB);
            }
        };
        Comparator<Stock> serialNumberComparator = SortUtil.generateComparator(SortUtil.Field.SERIALNUMBER);
        copy.sort(expectedComparator);
        toTestComparator.sort(serialNumberComparator);
        assertEquals(copy, toTestComparator);
    }

    @Test
    public void generateComparator_generalComparator_success() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                int pointsA = 0;
                int pointsB = 0;

                if (a.getIsBookmarked()) {
                    pointsA += 2;
                }

                if (b.getIsBookmarked()) {
                    pointsB += 2;
                }

                if (a.getQuantity().isLowOnQuantity()) {
                    pointsA++;
                }

                if (b.getQuantity().isLowOnQuantity()) {
                    pointsB++;
                }

                String serialNumberA = a.getSerialNumber().toString();
                String serialNumberB = b.getSerialNumber().toString();

                if (pointsA == pointsB) {
                    return serialNumberA.compareTo(serialNumberB);
                } else if (pointsA > pointsB) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };
        Comparator<Stock> generalComparator = SortUtil.generateComparator(SortUtil.Field.BOOKMARK);
        copy.sort(expectedComparator);
        toTestComparator.sort(generalComparator);
        assertEquals(copy, toTestComparator);
    }

    @Test
    public void generateReverseComparator() {
        List<Stock> copy = new ArrayList<>(toTestComparator);
        Comparator<Stock> expectedComparator = new Comparator<>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getName().toString();
                String nameB = b.getName().toString();
                return nameB.compareTo(nameA);
            }
        };
        Comparator<Stock> reverseComparator = SortUtil.generateReverseComparator(SortUtil.Field.NAME);
        copy.sort(expectedComparator);
        toTestComparator.sort(reverseComparator);
        assertEquals(copy, toTestComparator);
    }
}
