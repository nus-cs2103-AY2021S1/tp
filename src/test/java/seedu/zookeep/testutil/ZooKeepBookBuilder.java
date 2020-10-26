package seedu.zookeep.testutil;

import seedu.zookeep.model.ZooKeepBook;
import seedu.zookeep.model.animal.Animal;

/**
 * A utility class to help with building ZooKeepBook objects.
 * Example usage: <br>
 *     {@code ZooKeepBook ab = new ZooKeepBookBuilder().withAnimal(harambe).build();}
 */
public class ZooKeepBookBuilder {

    private ZooKeepBook zooKeepBook;

    public ZooKeepBookBuilder() {
        zooKeepBook = new ZooKeepBook();
    }

    public ZooKeepBookBuilder(ZooKeepBook zooKeepBook) {
        this.zooKeepBook = zooKeepBook;
    }

    /**
     * Adds a new {@code Animal} to the {@code ZooKeepBook} that we are building.
     */
    public ZooKeepBookBuilder withAnimal(Animal animal) {
        zooKeepBook.addAnimal(animal);
        return this;
    }

    public ZooKeepBook build() {
        return zooKeepBook;
    }
}
