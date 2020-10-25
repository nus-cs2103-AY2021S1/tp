package seedu.address.testutil;

import seedu.address.model.menu.Menu;

import static seedu.address.testutil.TypicalFoods.*;

public class TypicalMenus {
    public static final Menu menu = new MenuBuilder().add(PRATA).add(MILO).add(NUGGETS).build();
    private TypicalMenus() {
        //prevents instantiation
    }
}
