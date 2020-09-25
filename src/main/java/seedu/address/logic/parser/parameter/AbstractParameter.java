package seedu.address.logic.parser.parameter;

import java.util.Optional;
import org.apache.commons.cli.Option;

public abstract class AbstractParameter {
    private final Option option;
    private final String flag;
    private final String example;
    private Optional<String> rawValue;

    protected AbstractParameter(String name, String flag, String description, String example, boolean isRequired) {
        this.rawValue = Optional.empty();
        this.flag = flag;
        this.example = example;
        this.option = Option.builder(flag)
            .longOpt(name)
            .desc(description)
            .hasArg()
            .numberOfArgs(Option.UNLIMITED_VALUES)
            .required(isRequired)
            .build();
    }

    public Optional<String> getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = Optional.of(rawValue);
    }

    public String getFlag() {
        return this.flag;
    }

    public String getExample() {
        return this.example;
    }

    public Option asOption() {
        return this.option;
    }
}
