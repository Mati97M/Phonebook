package dev.mmieckowski.algorithm.search;

import dev.mmieckowski.PersonPhone;
import dev.mmieckowski.algorithm.execution.Timer;
import lombok.Getter;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSearch implements SearchAlgorithm {
    private static final String NAME = "hash table";
    private final Map<String, Long> personPhoneMap;
    private final Timer timer;
    @Getter
    private Duration creatingTime;

    public MapSearch() {
        this(new Timer());
    }

    public MapSearch(Timer timer) {
        this.personPhoneMap = new HashMap<>();
        this.timer = timer;
    }

    @Override
    public long search(List<String> targets, List<PersonPhone> personPhoneList) {
        timer.restart();
        for (PersonPhone personPhone : personPhoneList) {
            personPhoneMap.put(personPhone.name(), personPhone.phone());
        }
        creatingTime = timer.restart();

        int found = 0;
        for (String target : targets) {
            if (personPhoneMap.containsKey(target)) {
                found++;
            }
        }
        return found;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
