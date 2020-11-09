package seedu.address.model.bidbook;

import javafx.collections.ObservableList;
import seedu.address.model.bid.Bid;

public interface ReadOnlyBidBook {
    ObservableList<Bid> getBidList();
}
