package chopchop.model.stats;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import chopchop.commons.util.Pair;
import chopchop.model.recipe.Recipe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StatsManager {
    private ObservableList<Pair<String, LocalDateTime>> records = FXCollections.observableArrayList();

    public StatsManager(List<Pair<String, LocalDateTime>> records) {
        this.records.addAll(records);
        this.records.sort(new Comparator<Pair<String, LocalDateTime>>() {
            //Ascending order i.e. oldest one in front
            @Override
            public int compare(Pair<String, LocalDateTime> o1, Pair<String, LocalDateTime> o2) {
                if (o1.snd().isBefore(o2.snd())) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

    public ObservableList<Pair<String, LocalDateTime>> getRecords() {
        return this.records;
    }

    /**
     * Remove the last record, assumed to be latest record.
     */
    public void pop() {
        assert records.size() > 0;
        this.records.remove(records.remove(records.size() - 1));
    }

    public void add(Recipe recipe) {
        this.records.add(new Pair<>(recipe.getName(), LocalDateTime.now()));
    }

}
