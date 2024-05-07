package dev.mmieckowski.algorithm.search;

import dev.mmieckowski.PersonPhone;
import java.util.List;

public interface SearchAlgorithm {
    long search(List<String> targets, List<PersonPhone> personPhoneList);

    String getName();
}
