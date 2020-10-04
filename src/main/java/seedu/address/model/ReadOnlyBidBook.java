package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.bid.Bid;

public interface ReadOnlyBidBook {


    ObservableList<Bid> getBidList();
}
