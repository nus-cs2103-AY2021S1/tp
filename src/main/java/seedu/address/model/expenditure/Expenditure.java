package seedu.address.model.expenditure;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.Renderable;
import seedu.address.model.tag.Tag;

public class Expenditure implements Renderable {
    private final Name name;
    private final Price price;
    private final Date createdOn;
    private final Set<Tag> tags = new HashSet<>();

    /**
     *
     * @param name
     * @param price
     * @param createdOn
     * @param tags
     */
    public Expenditure(Name name, Price price, Date createdOn, Set<Tag> tags) {
        requireAllNonNull(name, price, createdOn, tags);
        this.name = name;
        this.price = price;
        this.createdOn = createdOn;
        this.tags.addAll(tags);
    }

    /**
     * Creates a deep copy of the given {@code Expenditure}.
     * @param toClone the {@code Expenditure} to be copied.
     * @return the deep copy of the given {@code Expenditure}.
     */
    public static Expenditure clone(Expenditure toClone) {
        Name nameClone = Name.clone(toClone.name);
        Price priceClone = Price.clone(toClone.price);
        Date dateClone = Date.clone(toClone.createdOn);
        Set<Tag> tagsClone = new HashSet<>();
        for (Tag tag : toClone.tags) {
            tagsClone.add(Tag.clone(tag));
        }
        return new Expenditure(nameClone, priceClone, dateClone, tagsClone);
    }

    public Name getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public java.util.Date getFormattedCreatedOn() {
        return createdOn.getFormattedCreatedOn();
    }

    public Set<Tag> getTags() {
        return tags;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expenditure)) {
            return false;
        }

        Expenditure otherExpenditure = (Expenditure) other;
        return otherExpenditure.getName().equals(getName())
                && otherExpenditure.getPrice().equals(getPrice())
                && otherExpenditure.getCreatedOn().equals(getCreatedOn())
                && otherExpenditure.getTags().equals(getTags());
    }


    /**
     * Checks if the contents within Expenditure is the same.
     * @param other
     * @return returns whether the contents are identical or not.
     */
    public boolean contentEquals(Object other) {
        Expenditure otherExpenditure = (Expenditure) other;
        return otherExpenditure.getName().equals(getName())
                && otherExpenditure.getPrice().equals(getPrice())
                && otherExpenditure.getTags().equals(getTags());
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean contains(String str) {
        String nameLowerCase = name.value.toLowerCase();
        return nameLowerCase.contains(str.toLowerCase());
    }
}
