package dev.mmieckowski.algorithm.sort;

import dev.mmieckowski.PersonPhone;
import lombok.NoArgsConstructor;
import javax.naming.TimeLimitExceededException;
import java.time.Clock;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class QuickSort implements SortAlgorithm {
    static final String NAME = "quick sort";
    private long startTimeMillis = 0;
    private long abortSortThreshold;
    private Clock clock = Clock.systemDefaultZone();

    QuickSort(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void sort(List<PersonPhone> list, final long abortSortThreshold) throws TimeLimitExceededException {
        this.startTimeMillis = clock.millis();
        this.abortSortThreshold = abortSortThreshold;
        doQuickSort(list, 0, list.size() - 1);
    }

    private void doQuickSort(List<PersonPhone> list, int start, int end) throws TimeLimitExceededException {
        long duration = clock.millis() - startTimeMillis;
        if (duration > abortSortThreshold) {
            throw new TimeLimitExceededException();
        }
        if (start >= end) return;

        PersonPhone pivot = list.get((start + end) / 2);
        int left = start;
        int right = end;

        while (left < right) {
            while (left <= end && list.get(left).compareTo(pivot) < 0) {
                left++;
            }

            while (right >= start && list.get(right).compareTo(pivot) > 0) {
                right--;
            }

            if (right > left) {
                Collections.swap(list, left, right);
            }
            duration = clock.millis() - startTimeMillis;
            if (duration > abortSortThreshold) {
                throw new TimeLimitExceededException();
            }
        }
        doQuickSort(list, start, right - 1);
        doQuickSort(list, right + 1, end);

    }

    @Override
    public String getName() {
        return NAME;
    }
}
