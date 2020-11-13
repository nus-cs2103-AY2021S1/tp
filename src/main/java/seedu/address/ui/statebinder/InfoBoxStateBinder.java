package seedu.address.ui.statebinder;

import static seedu.address.model.budget.Threshold.NO_THRESHOLD_MESSAGE;

import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.scene.text.TextAlignment;
import seedu.address.logic.Logic;
import seedu.address.model.budget.Threshold;
import seedu.address.ui.InfoBox;

public class InfoBoxStateBinder implements StateBinder {
    private InfoBox infoBox;

    public InfoBoxStateBinder(InfoBox infoBox) {
        this.infoBox = infoBox;
    }

    public InfoBoxStateBinder() { }

    @Override
    public void bind(Logic logic) {
        bindMainPageInfoBoxToPageState(logic);
        bindMainPageInfoBoxToExpenditureState(logic);
    }

    private void bindMainPageInfoBoxToPageState(Logic logic) {
        bindFirstRowTextToPageState(logic);
        bindThirdRowTextToPageState(logic);
    }

    private void bindMainPageInfoBoxToExpenditureState(Logic logic) {
        bindSecondRowTextToTotalExpenditure(logic);
    }

    private void bindFirstRowTextToPageState(Logic logic) {
        infoBox.getFirstRowText().textProperty().bind(Bindings.createStringBinding(() -> {
            if (logic.isBudgetPage()) {
                return handleFirstRowTextIsBudgetPage();
            }
            return handleFirstRowTextIsMainPage();
        }, logic.getIsBudgetPageProp()));
    }

    private String handleFirstRowTextIsBudgetPage() {
        infoBox.getFirstRowText().setTextAlignment(TextAlignment.LEFT);
        return "Total:";
    }

    private String handleFirstRowTextIsMainPage() {
        infoBox.getFirstRowText().setTextAlignment(TextAlignment.CENTER);
        return InfoBox.getDefaultFirstRowText();
    }

    private void bindSecondRowTextToTotalExpenditure(Logic logic) {
        infoBox.getIsBudgetPageProp().bindBidirectional(logic.getIsBudgetPageProp());
        infoBox.getSecondRowStringProp().bindBidirectional(logic.getTotalExpenditureStringProp());
        infoBox.getThresholdStringProp().bindBidirectional(logic.getThresholdStringProp());
    }

    private void bindThirdRowTextToPageState(Logic logic) {
        infoBox.getThirdRowText().textProperty().bind(Bindings.createStringBinding(() -> {
            if (logic.isBudgetPage()) {
                return handleThirdRowTextIsBudgetPage(logic);
            }
            return handleThirdRowTextIsMainPage();
        }, logic.getIsBudgetPageProp()));
    }

    private String handleThirdRowTextIsBudgetPage(Logic logic) {
        infoBox.getThirdRowText().setTextAlignment(TextAlignment.RIGHT);
        Optional<Threshold> thresholdOptional = logic.getThreshold();
        if (thresholdOptional.isEmpty()) {
            return NO_THRESHOLD_MESSAGE;
        }
        assert thresholdOptional.isPresent();
        Double thresholdVal = Double.parseDouble(thresholdOptional.get().toString());
        return "/" + String.format("%.2f", thresholdVal);
    }

    private String handleThirdRowTextIsMainPage() {
        infoBox.getThirdRowText().setTextAlignment(TextAlignment.CENTER);
        return InfoBox.getDefaultThirdRowText();
    }
}
