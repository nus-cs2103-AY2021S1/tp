package seedu.address.testutil;

import seedu.address.model.food.Food;
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
    public MenuBuilder add(Food food) {
        this.menu.add(food);
        return this;
    }

    public Menu build() {
        return menu;
    }

}
