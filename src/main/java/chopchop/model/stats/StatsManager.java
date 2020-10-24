package chopchop.model.stats;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import chopchop.commons.util.Pair;

public class StatsManager {
    private TreeSet<Pair<String, LocalDateTime>> records = new TreeSet<>(new Comparator<Pair<String, LocalDateTime>>() {
        @Override
        public int compare(Pair<String, LocalDateTime> o1, Pair<String, LocalDateTime> o2) {
            if (o1.snd().isAfter(o2.snd())) {
                return -1;
            } else {
                return 1;
            }
        }
    });

    public StatsManager(List<Pair<String, LocalDateTime>> records) {
        this.records.addAll(records);
    }

    public Set<Pair<String, LocalDateTime>> getRecords() {
        return this.records;
    }

    public void pop() {
        this.records.remove(records.first());
    }

    public void add() {
        this.records.add(new Pair("NEW", LocalDateTime.now()));
    }

}
