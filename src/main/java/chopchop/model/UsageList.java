package chopchop.model;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import chopchop.commons.util.Pair;
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
     * Replaces the contents of the usage list with {@code usages}.
     */
    public void setAll(UsageList<T> usages) {
        this.usages.setAll(usages.usages);
    }

    /**
     * Adds to the stack.
     */
    public void add(T item) {
        requireAllNonNull(item);
        this.usages.add(item);
    }

    /**
     * Returns the latest usage.
     */
    public void pop(String item) {
        requireNonNull(item);
        int len = this.usages.size();
        if (len == 0) {
            return;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (this.usages.get(i).getName().equals(item)) {
                this.usages.remove(i);
                return;
            }
        }
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

    public List<T> getRecentlyUsed(int n) {
        assert n >= 0;
        int len = this.usages.size();
        List<T> output = new ArrayList<>();
        int i = len - 1;
        while (i >= 0 && n > 0) {
            output.add(this.usages.get(i));
            i--;
            n--;
        }
        return output;
    }

    public List<Pair<String, Integer>> getMostUsed() {
        ArrayList<T> newLst = new ArrayList<>(this.usages);
        ArrayList<Pair<String, Integer>> outputLst = new ArrayList<>();
        for (var i : newLst) {
            int k = 0;
            for (var j : newLst) {
                if (i.getName().equals(j.getName())) {
                    k++;
                }
            }
            if (!outputLst.contains(new Pair<>(i.getName(), k))) {
                outputLst.add(new Pair<>(i.getName(), k));
            }
        }
        Comparator<Pair<String, Integer>> compare = (p1, p2)-> p1.snd() < p2.snd() ? 1 : 0;
        outputLst.sort(compare);
        return outputLst;
    }

}
