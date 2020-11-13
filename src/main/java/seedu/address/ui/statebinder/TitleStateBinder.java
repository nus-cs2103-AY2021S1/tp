package seedu.address.ui.statebinder;

import javafx.beans.binding.Bindings;
import seedu.address.logic.Logic;
import seedu.address.ui.Title;

public class TitleStateBinder implements StateBinder {
    private Title title;

    public TitleStateBinder(Title title) {
        this.title = title;
    }

    public TitleStateBinder() { }

    @Override
    public void bind(Logic logic) {
        title.getTitle().textProperty().bind(Bindings.createStringBinding(() -> {
            logic.isBudgetPage(); //this expression must be called to always trigger change in title
            return logic.getPageTitle();
        }, logic.getIsBudgetPageProp()));
    }
}
