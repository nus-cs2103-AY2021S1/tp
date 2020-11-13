package seedu.address.ui.util;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.ui.theme.Theme;
import seedu.address.ui.theme.ThemeSet;

public class UiUtilTest {

    private static final Theme validTheme = ThemeSet.DARK_THEME;

    @Test
    public void setTheme_nullRoot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UiUtil.setTheme(null, validTheme));
    }
}
