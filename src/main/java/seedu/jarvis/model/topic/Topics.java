package seedu.jarvis.model.topic;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Topics {
    public final ImmutableMap<Integer, Topic> allTopics;

    /**
     * Initializes a new Topics object.
     */
    public Topics() {
        Map<Integer, Topic> map = new HashMap<>();
        map.put(2, new Topic("2", "Primitives"));
        map.put(3, new Topic("3", "Iterative and Recursive Processes"));
        map.put(4, new Topic("4", "Higher-order Functions"));
        map.put(5, new Topic("5", "Lists"));
        map.put(6, new Topic("6", "List Processing"));
        map.put(7, new Topic("7", "Prep for Midterms"));
        map.put(8, new Topic("8", "Robots"));
        map.put(9, new Topic("9", "Mutable Data & Environment Model"));
        map.put(10, new Topic("10", "Memoization"));
        map.put(11, new Topic("11", "Streams"));

        allTopics = ImmutableMap.copyOf(map);
    }

    /**
     * Gets the topic for that week if it exists.
     * @param week the week to be retrieved
     * @return the Topic object that corresponds to what is covered that week
     */
    public Topic getTopic(int week) {
        return allTopics.getOrDefault(week, new Topic());
    }
}
