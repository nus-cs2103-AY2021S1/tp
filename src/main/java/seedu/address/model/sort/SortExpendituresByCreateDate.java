package seedu.address.model.sort;

import java.util.Comparator;

import seedu.address.model.expenditure.Expenditure;

public class SortExpendituresByCreateDate implements Comparator<Expenditure> {

    @Override
    public int compare(Expenditure a, Expenditure b) {
        return b.getCreatedOn().compareTo(a.getCreatedOn());
    }
}
