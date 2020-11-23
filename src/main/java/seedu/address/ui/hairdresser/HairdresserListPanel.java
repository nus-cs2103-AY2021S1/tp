package seedu.address.ui.hairdresser;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.ui.EntityListPanel;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of Hairdressers.
 */
public class HairdresserListPanel extends EntityListPanel<Hairdresser> {

    private final Logger logger = LogsCenter.getLogger(HairdresserListPanel.class);

    /**
     * Creates a {@code HairdresserListPanel} with the given {@code ObservableList}.
     */
    public HairdresserListPanel(ObservableList<Hairdresser> hairdresserList) {
        super(hairdresserList);
        labelName.setText("Hairdressers");
    }

    @Override
    public UiPart<Region> getCard(Hairdresser hairdresser, int index) {
        return new HairdresserCard(hairdresser, index);
    }

}
