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
}
