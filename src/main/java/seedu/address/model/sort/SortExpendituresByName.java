package seedu.address.model.sort;

import java.util.Comparator;

import seedu.address.model.expenditure.Expenditure;

public class SortExpendituresByName implements Comparator<Expenditure> {

    @Override
    public int compare(Expenditure a, Expenditure b) {
        return a.getName().value.toLowerCase().compareTo(b.getName().value.toLowerCase());
    }
}
