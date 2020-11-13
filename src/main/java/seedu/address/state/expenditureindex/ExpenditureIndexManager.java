package seedu.address.state.expenditureindex;

import java.util.Optional;

public class ExpenditureIndexManager implements ExpenditureIndex {
    private int expenditureIndex;

    /**
     * Default constructor.
     * @param expenditureIndex the index of the expenditure.
     */
    public ExpenditureIndexManager(int expenditureIndex) {
        this.expenditureIndex = expenditureIndex;
    }

    @Override
    public Optional<Integer> getExpenditureIndex() {
        return Optional.of(this.expenditureIndex);
    }

    @Override
    public void setIndex(int index) {
        this.expenditureIndex = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        ExpenditureIndexManager that = (ExpenditureIndexManager) o;
        return expenditureIndex == that.expenditureIndex;
    }
}
