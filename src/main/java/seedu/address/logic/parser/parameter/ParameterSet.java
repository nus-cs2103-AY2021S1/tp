package seedu.address.logic.parser.parameter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.cli.Options;

import seedu.address.logic.parser.exceptions.ParameterConflictException;

public class ParameterSet {
    private final List<AbstractParameter> parameterList;
    private Options options = new Options();

    public ParameterSet() {
        this.parameterList = new ArrayList<>();
    }

    private void refreshOptions() {
        this.options = new Options();
        for (AbstractParameter parameter : this.parameterList) {
            if (parameter.getFlag().equals("")) {
                // unnamed parameter, e.g. '1' in 'DELETE 1'
                continue;
            }
            this.options.addOption(parameter.asOption());
        }
    }

    public void addParameter(AbstractParameter parameter) throws ParameterConflictException {
        this.parameterList.add(parameter);
        if (this.hasParameterConflict()) {
            throw new ParameterConflictException();
        }
        this.refreshOptions();
    }

    private boolean hasParameterConflict() {
        HashSet<String> set = new HashSet<>();
        parameterList.forEach(x -> set.add(x.getFlag()));
        return set.size() != parameterList.size();
    }

    public Options asOptions() {
        return options;
    }

    public List<AbstractParameter> getParameterList() {
        return parameterList;
    }
}
