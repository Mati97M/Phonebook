package dev.mmieckowski.algorithm.sort;

import dev.mmieckowski.PersonPhone;
import javax.naming.TimeLimitExceededException;
import java.util.List;

public interface SortAlgorithm {
    void sort(List<PersonPhone> list, final long abortSortThreshold) throws TimeLimitExceededException;

    String getName();
}
