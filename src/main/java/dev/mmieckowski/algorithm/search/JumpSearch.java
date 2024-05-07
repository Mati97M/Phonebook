package dev.mmieckowski.algorithm.search;

import dev.mmieckowski.PersonPhone;
import java.util.List;

public class JumpSearch implements SearchAlgorithm {
    private static final String NAME = "jump search";

    private static int comparingNames(String name, List<PersonPhone> data, int index) {
        return data.get(index).name().compareTo(name);
    }

    @Override
    public long search(List<String> targets, List<PersonPhone> personPhoneList) {
        int found = 0;
        for (String target : targets) {
            if (isPresent(target, personPhoneList)) {
                found++;
            }
        }
        return found;
    }

    @Override
    public String getName() {
        return NAME;
    }

    private boolean isPresent(String target, List<PersonPhone> personPhoneList) {
        int size = personPhoneList.size();
        // Finding block size to be jumped
        int step = (int) Math.floor(Math.sqrt(size));

        // Finding the block where element is
        // present (if it is present)
        int prev = 0;
        for (int minStep = Math.min(step, size) - 1; comparingNames(target, personPhoneList, minStep) < 0; minStep = Math.min(step, size) - 1) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(size));
            if (prev >= size)
                return false;
        }

        // Doing a linear search for target in block
        // beginning with prev.
        while (comparingNames(target, personPhoneList, prev) < 0) {
            prev++;

            // If we reached next block or end of
            // list, element is not present.
            if (prev == Math.min(step, size))
                return false;
        }

        // If element is found
        return personPhoneList.get(prev).name().equals(target);
    }
}
