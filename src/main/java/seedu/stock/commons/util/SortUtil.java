package seedu.stock.commons.util;

import java.math.BigInteger;
import java.util.Comparator;

import seedu.stock.model.stock.Stock;

public class SortUtil {

    public enum Order {
        ASCENDING, DESCENDING
    }

    public enum Field {
        NAME, SOURCE, LOCATION, QUANTITY, SERIALNUMBER, BOOKMARK
    }

    /**
     * Returns a description of the sorted field.
     *
     * @param field The sorted field.
     * @return The description of the sorted field.
     */
    public static String getFieldDescription(Field field) {
        switch (field) {
        case NAME:
        case QUANTITY:
        case SOURCE:
        case LOCATION:
            return field.toString().toLowerCase();
        case SERIALNUMBER:
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
    public static Comparator<Stock> generateComparator(Field field) {
        switch (field) {
        case NAME:
            return generateNameComparator();
        case SOURCE:
            return generateSourceComparator();
        case LOCATION:
            return generateLocationComparator();
        case SERIALNUMBER:
            return generateSerialNumberComparator();
        case QUANTITY:
            return generateQuantityComparator();
        case BOOKMARK:
            return generateGeneralComparator();
        default:
            return null;
        }
    }

    /**
     * Returns a general comparator to sort the inventory.
     * Bookmarked and low stock items will be given priority.
     *
     * @return The general comparator to sort the inventory.
     */
    public static Comparator<Stock> generateGeneralComparator() {
        return new Comparator<Stock>() {
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
    }

    /**
     * Returns a reversed comparator to sort the inventory.
     *
     * @param field The field to be sorted by.
     * @return The reversed comparator to sort the inventory.
     */
    public static Comparator<Stock> generateReverseComparator(Field field) {
        Comparator<Stock> notReversed = generateComparator(field);
        if (notReversed != null) {
            return notReversed.reversed();
        } else {
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
                String sourceA = a.getSource().toString();
                String sourceB = b.getSource().toString();
                return sourceA.compareTo(sourceB);
            }
        };
    }

    private static Comparator<Stock> generateLocationComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String locationA = a.getLocation().toString();
                String locationB = b.getLocation().toString();
                return locationA.compareTo(locationB);
            }
        };
    }

    private static Comparator<Stock> generateSerialNumberComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                String serialNumberA = a.getSerialNumber().toString();
                String serialNumberB = b.getSerialNumber().toString();
                return serialNumberA.compareTo(serialNumberB);
            }
        };
    }

    private static Comparator<Stock> generateQuantityComparator() {
        return new Comparator<Stock>() {
            @Override
            public int compare(Stock a, Stock b) {
                BigInteger quantityA = new BigInteger(a.getQuantity().toString());
                BigInteger quantityB = new BigInteger(b.getQuantity().toString());
                return quantityA.compareTo(quantityB);
            }
        };
    }
}
