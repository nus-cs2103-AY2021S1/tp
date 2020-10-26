package seedu.address.model.bid;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid> {

    @Override
    public int compare(Bid bidOne, Bid bidTwo) {

        int bidOnePropertyId = bidOne.getPropertyId().getId();
        int bidTwoPropertyId = bidTwo.getPropertyId().getId();
        double bidOneAmount = bidOne.getBidAmount().getPrice();
        double bidTwoAmount = bidTwo.getBidAmount().getPrice();
        if (bidOnePropertyId < bidTwoPropertyId) {
            return -1;
        } else if (bidOnePropertyId > bidTwoPropertyId) {
            return 1;
        } else {
            if (bidOneAmount > bidTwoAmount) {
                return -1;
            } else if (bidOneAmount < bidTwoAmount) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
