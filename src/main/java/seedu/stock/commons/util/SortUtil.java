package seedu.stock.commons.util;

import java.util.Comparator;

import seedu.stock.model.stock.Stock;

public class SortUtil {

    public static final String[] FIELDS = new String[]{"name", "quantity", "source", "location", "serialnumber"};

    /**
     * Returns a description of the sorted field.
     *
     * @param field The sorted field.
     * @return The description of the sorted field.
     */
    public static String getFieldDescription(String field) {
        switch (field) {
        case "name":
        case "quantity":
        case "source":
        case "location":
            return field;
        case "serialnumber":
            return "serial number";
        default:
            return "";
        }
    }

    /**
     * Returns a comparator to sort the inventory.
     *
     * @param field The field to be sorted by.
     * @return The comparator to sort the inventory.
     */
    public static Comparator<Stock> generateComparator(String field) {
        switch (field) {
        case "name":
            return generateNameComparator();
        case "source":
            return generateSourceComparator();
        case "location":
            return generateLocationComparator();
        case "serialnumber":
            return generateSerialNumberComparator();
        case "quantity":
            return generateQuantityComparator();
        default:
            return null;
        }
    }

    private static Comparator<Stock> generateNameComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getName().toString();
                String nameB = b.getName().toString();
                return nameA.compareTo(nameB);
            }
        };
    }

    private static Comparator<Stock> generateSourceComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getSource().toString();
                String nameB = b.getSource().toString();
                return nameA.compareTo(nameB);
            }
        };
    }

    private static Comparator<Stock> generateLocationComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getLocation().toString();
                String nameB = b.getLocation().toString();
                return nameA.compareTo(nameB);
            }
        };
    }

    private static Comparator<Stock> generateSerialNumberComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getSerialNumber().toString();
                String nameB = b.getSerialNumber().toString();
                return nameA.compareTo(nameB);
            }
        };
    }

    private static Comparator<Stock> generateQuantityComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String nameA = a.getQuantity().toString();
                String nameB = b.getQuantity().toString();
                return nameA.compareTo(nameB);
            }
        };
    }
}
