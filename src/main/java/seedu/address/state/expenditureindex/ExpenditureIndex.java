package seedu.address.state.expenditureindex;

import java.util.Optional;

public interface ExpenditureIndex {
    Optional<Integer> getExpenditureIndex();
    void setIndex(int index);
}
