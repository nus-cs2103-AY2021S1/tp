package seedu.address.logic.commands.bids;

import seedu.address.logic.commands.bidcommands.EditBidCommand;
import seedu.address.model.id.PropertyId;
import seedu.address.testutil.bids.EditBidDescriptorBuilder;

public class BidCommandTestUtil {


    public static final String VALID_PROPERTY_ID_BID_A = "P01";
    public static final String VALID_PROPERTY_ID_BID_B = "P02";

    public static final String VALID_BIDDER_ID_BID_A = "B01";
    public static final String VALID_BIDDER_ID_BID_B = "B02";

    public static final double VALID_BID_AMOUNT_BID_A = 10000.20;
    public static final double VALID_BID_AMOUNT_BID_B = 123450.20;

    public static final PropertyId DEFAULT_PROPERTY_ID = new PropertyId("P1");

    public static final EditBidCommand.EditBidDescriptor VALID_BID_A;
    public static final EditBidCommand.EditBidDescriptor VALID_BID_B;

    static {
        VALID_BID_A = new EditBidDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_BID_A)
                .withBidderId(VALID_BIDDER_ID_BID_A).withBidAmount(VALID_BID_AMOUNT_BID_A).build();
        VALID_BID_B = new EditBidDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_BID_B)
                .withBidderId(VALID_BIDDER_ID_BID_B).withBidAmount(VALID_BID_AMOUNT_BID_B).build();
    }

}

