package seedu.address.ui.statebinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.Logic;

public class StateBinderList {
    private List<StateBinder> stateBinderList;

    /**
     * Constructor for StateBinderList.
     * @param stateBinders all stateBinder objects that will update Ui text dynamically.
     */
    public StateBinderList(StateBinder ... stateBinders) {
        this.stateBinderList = new ArrayList<>();
        stateBinderList.addAll(Arrays.asList(stateBinders));
    }

    public List<StateBinder> getStateBinderAsList() {
        return this.stateBinderList;
    }

    public void bindAll(Logic logic) {
        stateBinderList.stream().forEach(stateBinder -> stateBinder.bind(logic));
    }
}
