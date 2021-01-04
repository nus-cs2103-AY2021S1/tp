package seedu.homerce.model.revenue;

import java.util.Comparator;

public class RevenueDefaultComparator implements Comparator<Revenue> {

    @Override
    public int compare(Revenue revenue1, Revenue revenue2) {
        if (revenue1.getDate().getLocalDate().isBefore(revenue2.getDate().getLocalDate())) {
            return 1;
        } else if (revenue1.getDate().getLocalDate().isAfter(revenue2.getDate().getLocalDate())) {
            return -1;
        } else {
            return 0;
        }
    }
}
