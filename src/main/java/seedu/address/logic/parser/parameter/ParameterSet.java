package seedu.address.logic.parser.parameter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class ParameterSet {
    private final List<AbstractParameter> parameterList;
    private Options options;

    private void refreshOptions() {
        this.options = new Options();
        for (AbstractParameter parameter : this.parameterList) {
            this.options.addOption(parameter.asOption());
        }
    }

    public ParameterSet() {
        this.parameterList = new ArrayList<>();
    }

    public void addParameter(AbstractParameter parameter) {
        this.parameterList.add(parameter);
        this.refreshOptions();
    }

    private boolean hasParameterConflict() {
        HashSet<String> set = new HashSet<>();
        parameterList.forEach(x -> set.add(x.getFlag()));
        return set.size() == parameterList.size();
    }

    public void provideValues(CommandLine cmd) {
        for (AbstractParameter parameter : parameterList) {
            String flag = parameter.getFlag();
            if (flag.equals("")) {
                List<String> unconsumedArgs = cmd.getArgList();
                if (unconsumedArgs.size() == 0) {
                    // throw something
                }
                parameter.setRawValue(String.join(" ", unconsumedArgs));
            } else {
                parameter.setRawValue(String.join(" ", cmd.getOptionValues(flag)));
            }
        }
    }

    public String getHelp(String commandName) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(
            printWriter,
            formatter.getWidth(),
            commandName,
            "",
            this.options,
            formatter.getLeftPadding(),
            formatter.getDescPadding(),
            "");
        stringWriter.write("\nEXAMPLE: ");
        stringWriter.write(getExample(commandName));
        return stringWriter.toString();
    }

    public String getExample(String commandName) {
        return commandName + " " + parameterList.stream()
            .map(p -> p.getFlag() + p.getExample())
            .collect(Collectors.joining(" "));
    }

    public Options asOptions() {
        return options;
    }
}
