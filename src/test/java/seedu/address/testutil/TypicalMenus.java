package seedu.address.testutil;

import static seedu.address.testutil.TypicalFoods.MILO;
import static seedu.address.testutil.TypicalFoods.NUGGETS;
import static seedu.address.testutil.TypicalFoods.PRATA;

import seedu.address.model.menu.Menu;

public class TypicalMenus {
    public static final Menu MENU = new MenuBuilder().add(PRATA).add(MILO).add(NUGGETS).build();
    private TypicalMenus() {
        //prevents instantiation
    }
}
