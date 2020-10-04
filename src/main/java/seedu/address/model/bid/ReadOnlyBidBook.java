package seedu.address.model.bid;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

public interface ReadOnlyBidBook {


    ObservableList<Bid> getBidList();
}
