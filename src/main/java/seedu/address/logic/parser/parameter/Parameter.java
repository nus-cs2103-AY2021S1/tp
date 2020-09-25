package seedu.address.logic.parser.parameter;

import java.util.Optional;
import java.util.function.Function;

public class Parameter<T> extends AbstractParameter {
    private Function<String, T> converter;

    public Parameter(String name, String flag, String description, String example, Function<String, T> converter) {
        super(name, flag, description, example, true);
        this.converter = converter;
    }

    public T consume() {
        Optional<String> rawValue = this.getRawValue();
        if (rawValue.isEmpty()) {
            // throw something
        } else {
            return converter.apply(rawValue.get());
        }
    }
}
