package seedu.address.logic.parser.parameter;

import java.util.Optional;
import java.util.function.Function;
import seedu.address.logic.parser.exceptions.ParseException;

public class OptionalParameter<T> extends AbstractParameter {
    private ParameterConverter<T> converter;
    private Optional<T> value = Optional.empty();

    public OptionalParameter(String name, String flag, String description,
                             String example, ParameterConverter<T> converter) {
        super(name, flag, description, example, false);
        this.converter = converter;
    }

    @Override
    public void setValue(String rawValue) throws ParseException {
        this.value = Optional.of(converter.apply(rawValue));
    }

    public <R> Optional<R> consumeIfPresent(Function<T, R> consumer) {
        return this.value.map(consumer);
    }

    public Optional<T> getValue() {
        return value;
    }
}

