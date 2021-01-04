package seedu.homerce.ui;

import javafx.scene.layout.Region;

/**
 * Panel containing the information related to the package.
 */
public abstract class Panel extends UiPart<Region> {

    public Panel(String name) {
        super(name);
    }
}
