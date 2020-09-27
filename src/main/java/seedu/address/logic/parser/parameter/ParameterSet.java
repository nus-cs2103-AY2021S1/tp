package seedu.address.logic.parser.parameter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import seedu.address.logic.parser.exceptions.ParameterConflictException;
import seedu.address.logic.parser.exceptions.ParseException;

public class ParameterSet {
    private final List<AbstractParameter> parameterList;
    private Options options;

    public ParameterSet() {
        this.parameterList = new ArrayList<>();
    }

    private void refreshOptions() {
        this.options = new Options();
        for (AbstractParameter parameter : this.parameterList) {
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
