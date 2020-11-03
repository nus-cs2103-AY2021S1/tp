package seedu.address.model.food;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class MenuItem extends Food {

    private final String filePath;

    /**
     * Every field must be present and not null.
     */
    public MenuItem (String name, double price, Set<Tag> tags, String filePath) {
        super(name, price, tags);
        this.filePath = filePath;
    }

    @Override
    public Set<Tag> getTags() {
        return this.tags;
    }

    @Override
    public void setTags(Set<Tag> newTags) {
        tags.clear();
        tags.addAll(newTags);
    }

    public String getFilePath() {
        return filePath;
    }
}
