package seedu.address.testutil;

import static seedu.address.testutil.TypicalMenuItems.MILO;
import static seedu.address.testutil.TypicalMenuItems.NUGGETS;
import static seedu.address.testutil.TypicalMenuItems.PRATA;

import seedu.address.model.menu.Menu;

public class TypicalMenus {
    public static final Menu MENU = new MenuBuilder().add(PRATA).add(MILO).add(NUGGETS).build();
    private TypicalMenus() {
        //prevents instantiation
    }
}
