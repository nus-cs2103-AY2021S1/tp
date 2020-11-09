package seedu.address.model.task;

public class CollaborativeLink extends Link {

    public CollaborativeLink() {
        this("No collaborative link", "-");
    }

    public CollaborativeLink(String description, String url) {
        super(description, url);
    }

    /**
     * Returns a String representation of the Collaborative Link.
     * This representation includes the description and url in the format of outputFormatter.
     *
     * @return a String representation of the Collaborative Link.
     */
    @Override
    public String toString() {
        return getDescription() + " " + getUrl();
    }

    /**
     * Returns a boolean value indicating if the Collaborative Link is equal to
     * another object by determining if descriptions and url parameters
     * are equal.
     *
     * @param o an object that is compared to the Collaborative Link to determine if both are equal
     * @return true or false if the Collaborative Link is equal or not equal to the object respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof CollaborativeLink) {
            CollaborativeLink link = (CollaborativeLink) o;
            boolean isEqual = this.getDescription().equals(link.getDescription())
                    && this.getUrl().equals(link.getUrl());
            return isEqual;
        } else {
            return false;
        }
    }
}
