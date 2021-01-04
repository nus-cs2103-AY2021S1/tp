package seedu.homerce.model.revenue;

import java.math.BigDecimal;
import java.util.Comparator;

public class RevenueComparator implements Comparator<Revenue> {

    @Override
    public int compare(Revenue revenue1, Revenue revenue2) {
        assert revenue1.getValue().value.compareTo(BigDecimal.ZERO) >= 0;
        assert revenue2.getValue().value.compareTo(BigDecimal.ZERO) >= 0;
        return revenue1.getValue().value.compareTo(revenue2.getValue().value);
    }
}
