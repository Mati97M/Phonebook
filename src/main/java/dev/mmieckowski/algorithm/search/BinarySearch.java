package dev.mmieckowski.algorithm.search;

import dev.mmieckowski.PersonPhone;
import java.util.List;

public class BinarySearch implements SearchAlgorithm {
    private static final String NAME = "binary search";

    @Override
    public long search(List<String> targets, List<PersonPhone> personPhoneList) {
        long found = 0;
        for (String target : targets) {
            if (doBinarySearch(target, personPhoneList, 0, personPhoneList.size() - 1)) {
                found++;
            }
        }
        return found;
    }

    private boolean doBinarySearch(String target, List<PersonPhone> personPhoneList, int start, int end) {
        if (start > end) {
            return false;
        }
        int middle = (start + end) / 2;
        int compareResult = personPhoneList.get(middle).name().compareTo(target);
        if (compareResult == 0) {
            return true;
        } else if (compareResult < 0) {
            return doBinarySearch(target, personPhoneList, middle + 1, end);
        } else {
            return doBinarySearch(target, personPhoneList, start, middle - 1);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }
}
