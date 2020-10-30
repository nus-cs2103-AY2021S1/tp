package chopchop.model;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<T> getUsageList() {
        return this.usages;
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
            .filter(x-> x.isBefore(upperBound))
            .collect(Collectors.toList());
    }

    /**
     * Returns a list of string output pairs that can be directly fed into listView.
     */
    public List<Pair<String, String>> getUsagesBetween(LocalDateTime after, LocalDateTime before) {
        if (after == null && before == null) {
            return new ArrayList<>();
        } else if (after != null && before == null) {
            return getUsagesAfter(after).stream()
                .map(x -> new Pair<>(x.getName(), x.getPrintableDate()))
                .collect(Collectors.toList());
        } else if (after == null) {
            return getUsagesBefore(before).stream()
                .map(x -> new Pair<>(x.getName(), x.getPrintableDate()))
                .collect(Collectors.toList());
        } else {
            return this.usages.stream()
                .filter(x -> x.isAfter(after))
                .filter(x -> x.isBefore(before))
                .map(x -> new Pair<>(x.getName(), x.getPrintableDate()))
                .collect(Collectors.toList());
        }

    }

    public List<T> getRecentlyUsed(int n) {
        assert n >= 0;

        var sorted = new ArrayList<>(this.usages);
        Collections.sort(sorted, new Comparator<T>() { //just in case
            @Override
            public int compare(final T o1, final T o2) {
                if (o1.getDate().compareTo(o2.getDate()) < 0) {
                    return -1;
                } else if (o1.getDate().compareTo(o2.getDate()) == 0) {
                    return Integer.compare(o2.getName().compareTo(o1.getName()), 0);
                } else {
                    return 1;
                }
            }
        });

        var output = new ArrayList<T>();

        int i = sorted.size();
        while (i-- > 0 && n-- > 0) {
            output.add(sorted.get(i));
        }

        return output;
    }

    public List<Pair<String, String>> getMostUsed() {
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
        Collections.sort(outputLst, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(final Pair<String, Integer> o1, final Pair<String, Integer> o2) {
                if (o1.snd() < o2.snd()) {
                    return 1;
                } else if (o1.snd().equals(o2.snd())) {
                    return Integer.compare(o1.fst().compareTo(o2.fst()), 0);
                } else {
                    return -1;
                }
            }
        });

        return outputLst.stream()
            .map(x -> new Pair<>(x.fst(), "No. of times made: " + x.snd().toString()))
            .collect(Collectors.toList());
    }

}
