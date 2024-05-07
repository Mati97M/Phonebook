package dev.mmieckowski.algorithm.sort;

import dev.mmieckowski.PersonPhone;
import dev.mmieckowski.algorithm.execution.Timer;
import lombok.NoArgsConstructor;
import javax.naming.TimeLimitExceededException;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
public class BubbleSort implements SortAlgorithm {
    static final String NAME = "bubble sort";
    Timer timer;

    BubbleSort(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void sort(List<PersonPhone> list, final long abortSortThreshold) throws TimeLimitExceededException {
        long currTime = 0;
        int size = list.size();

        if (timer == null) {
            timer = new Timer();
        }
        for (int i = 0; i < size - 1; i++) {
            currTime += timer.restart().toMillis();
            if (currTime >= abortSortThreshold) {
                throw new TimeLimitExceededException("Time Limit Exceeded while sorting");
            }
            for (int j = 0; j < size - i - 1; j++) {
                if (list.get(j).name().compareTo(list.get(j + 1).name()) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

}
