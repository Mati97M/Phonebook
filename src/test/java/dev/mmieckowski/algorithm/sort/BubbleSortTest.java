package dev.mmieckowski.algorithm.sort;

import static dev.mmieckowski.utilities.Utilities.getSortedList;
import static dev.mmieckowski.utilities.Utilities.getUnsortedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import dev.mmieckowski.PersonPhone;
import dev.mmieckowski.algorithm.execution.Timer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.naming.TimeLimitExceededException;
import java.time.Duration;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BubbleSortTest {
    private static List<PersonPhone> listToSort;
    @InjectMocks
    private BubbleSort bubbleSort;
    @Mock
    private Timer timer;

    @BeforeAll
    static void prepareListToSort() {
        listToSort = getUnsortedList();
    }

    @Test
    void sortAbortedThrowingExcetpionWhenAbortSortThresholdIsExceededTest() {
        long abortSortThreshold = 1000;
        when(timer.restart()).thenReturn(Duration.ofMillis(abortSortThreshold));

        assertThrows(TimeLimitExceededException.class, () -> bubbleSort.sort(List.of(
                        new PersonPhone("X", 1L),
                        new PersonPhone("X", 1L)),
                abortSortThreshold));
        verify(timer).restart();
    }

    @Test
    void sortPersonPhoneListTest() throws TimeLimitExceededException {
        bubbleSort.sort(listToSort, Long.MAX_VALUE);

        assertIterableEquals(getSortedList(), listToSort);
        verify(timer, atLeastOnce()).restart();
    }

    @Test
    void getNameTest() {
        assertEquals("bubble sort", bubbleSort.getName());
    }
}