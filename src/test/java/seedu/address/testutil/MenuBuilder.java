package seedu.address.testutil;

import seedu.address.model.food.MenuItem;
import seedu.address.model.menu.Menu;

public class MenuBuilder {
    private final Menu menu;

    /**
     * Creates a {@code MenuBuilder} with a default Menu.
     */
    public MenuBuilder() {
        this.menu = new Menu();
    }

    /**
     * Initializes the MenuBuilder with the data of {@code menuToCopy}.
     */
    public MenuBuilder(Menu menuToCopy) {
        this.menu = menuToCopy;
    }

    /**
     * Adds a food to the MenuBuilder.
     */
    public MenuBuilder add(MenuItem item) {
        this.menu.add(item);
        return this;
    }

    public Menu build() {
        return menu;
    }

}
