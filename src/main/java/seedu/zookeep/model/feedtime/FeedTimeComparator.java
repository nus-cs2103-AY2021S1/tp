package seedu.zookeep.model.feedtime;

import java.util.Comparator;

public class FeedTimeComparator implements Comparator<FeedTime> {
    @Override
    public int compare(FeedTime o1, FeedTime o2) {
        return Integer.compare(
                Integer.parseInt(o1.feedTime),
                Integer.parseInt(o2.feedTime));
    }
}
