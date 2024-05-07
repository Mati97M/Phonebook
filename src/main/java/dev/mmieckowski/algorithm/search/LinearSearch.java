package dev.mmieckowski.algorithm.search;

import dev.mmieckowski.PersonPhone;
import java.util.List;

public class LinearSearch implements SearchAlgorithm {
    private static final String NAME = "linear search";

    @Override
    public long search(List<String> targets, List<PersonPhone> personPhoneList) {
        long foundTargets = 0;
        for (String target : targets) {
            for (PersonPhone personPhone : personPhoneList) {
                if (target.equals(personPhone.name())) {
                    foundTargets++;
                }
            }
        }
        return foundTargets;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
