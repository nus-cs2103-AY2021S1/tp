package seedu.stock.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.stock.SerialNumberSet;

public class TypicalSerialNumberSets {
    public static final SerialNumberSet NTUC = new SerialNumberSetBuilder().withSource("ntuc")
            .withAccumulatedQuantity("1").build();
    public static final SerialNumberSet FAIRPRICE = new SerialNumberSetBuilder().withSource("fairprice")
            .withAccumulatedQuantity("1").build();
    public static final SerialNumberSet BENGAWAN_SOLO = new SerialNumberSetBuilder().withSource("bengawansolo")
            .withAccumulatedQuantity("1").build();
    public static final SerialNumberSet GIANT = new SerialNumberSetBuilder().withSource("giant")
            .withAccumulatedQuantity("1").build();

    private TypicalSerialNumberSets() {} // prevents instantiation

    /**
     ** Returns a {@code SerialNumberSetBook} with all the typical stocks.
     */
    public static SerialNumberSetsBook getTypicalSerialNumberSetsBook() {
        SerialNumberSetsBook serialNumberSetsBook = new SerialNumberSetsBook();
        for (SerialNumberSet serialNumberSet : getTypicalSerialNumberSets()) {
            serialNumberSetsBook.addSerialNumberSet(serialNumberSet);
        }
        return serialNumberSetsBook;
    }
    public static List<SerialNumberSet> getTypicalSerialNumberSets() {
        return new ArrayList<>(Arrays.asList(NTUC, FAIRPRICE, GIANT));
    }

}
