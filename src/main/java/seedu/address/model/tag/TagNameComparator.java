package seedu.address.model.tag;

import java.util.Comparator;

public class TagNameComparator implements Comparator<Tag> {
    @Override
    public int compare(Tag t1, Tag t2) {
        return t1.getTagName().tagName.compareToIgnoreCase(t2.getTagName().tagName);
    }
}
