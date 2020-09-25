package seedu.address.logic.parser.parameter;

import java.util.Optional;
import java.util.function.Function;

public class OptionalParameter<T> extends AbstractParameter {
    private Function<String, T> converter;

    public OptionalParameter(String name, String flag, String description,
                             String example, Function<String, T> converter) {
        super(name, flag, description, example, false);
        this.converter = converter;
    }

    public <R> Optional<R> consumeIfPresent(Function<T, R> consumer) {
        return this.getRawValue().map(converter).map(consumer);
    }
}

