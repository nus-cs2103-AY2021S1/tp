package chopchop.model;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import chopchop.model.usage.Usage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsageList<T extends Usage> {
    private final ObservableList<T> usages = FXCollections.observableArrayList();

    public UsageList() {}

    /**
     * Constructs a {@code UsageList} based on the usages.
     */
    public UsageList(List<T> usages) {
        requireAllNonNull(usages);
        this.usages.setAll(usages);
    }

    public ObservableList<T> getUsages() {
        return this.usages;
    }

    /**
     * Returns the latest usage.
     */
    public Optional<LocalDateTime> pop() {
        if (this.usages.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(this.usages.get(this.usages.size() - 1).getDate());
    }

    public int getUsageCount() {
        return this.usages.size();
    }

    public List<T> getUsagesAfter(LocalDateTime lowerBound) {
        requireNonNull(lowerBound);
        return this.usages.stream()
            .filter(x-> x.isAfter(lowerBound))
            .collect(Collectors.toList());
    }

    public List<T> getUsagesBefore(LocalDateTime upperBound) {
        requireNonNull(upperBound);
        return this.usages.stream()
            .filter(x-> x.isAfter(upperBound))
            .collect(Collectors.toList());
    }

    public List<T> getUsagesBetween(LocalDateTime start, LocalDateTime end) {
        requireAllNonNull(start, end);
        return this.usages.stream()
            .filter(x -> x.isAfter(start))
            .filter(x -> x.isBefore(end))
            .collect(Collectors.toList());
    }
}
