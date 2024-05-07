package dev.mmieckowski.algorithm.sort;

import static dev.mmieckowski.utilities.Utilities.getSortedList;
import static dev.mmieckowski.utilities.Utilities.getUnsortedList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import dev.mmieckowski.PersonPhone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.naming.TimeLimitExceededException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class QuickSortTest {
    public static final String STR_START_TIME = "2014-12-22T10:15:30Z";
    public static final Clock CLOCK_START_TIME = Clock.fixed(Instant.parse(STR_START_TIME), ZoneId.of("UTC"));
    public static final long START_TIME_IN_MILLIS = CLOCK_START_TIME.millis();
    private static List<PersonPhone> listToSort;

    @InjectMocks
    private QuickSort quickSort;
    @Mock
    private Clock mockedClock;

    @BeforeAll
    static void prepareListToSort() {
        listToSort = getUnsortedList();
    }

    @Test
    void sortAbortedThrowingExcetpionWhenAbortSortThresholdIsExceededTest() {
        long abortSortThreshold = 1000;
        Clock clockThresholdTime = Clock.offset(CLOCK_START_TIME, Duration.ofMillis(abortSortThreshold));
        long thresholdTimeInMillis = clockThresholdTime.millis();
        when(mockedClock.millis()).thenReturn(START_TIME_IN_MILLIS, thresholdTimeInMillis + 1);

        assertTrue(START_TIME_IN_MILLIS < thresholdTimeInMillis);
        assertThrows(TimeLimitExceededException.class, () -> quickSort.sort(new ArrayList<>(), abortSortThreshold));
        verify(mockedClock, times(2)).millis();
    }

    @Test
    void sortPersonPhoneListTest() throws TimeLimitExceededException {
        when(mockedClock.millis()).thenReturn(START_TIME_IN_MILLIS, Long.MAX_VALUE);

        quickSort.sort(listToSort, Long.MAX_VALUE);
        assertIterableEquals(getSortedList(), listToSort);
        verify(mockedClock, atLeastOnce()).millis();
    }

    @Test
    void getNameTest() {
        assertTrue(quickSort.getName().equals("quick sort"));
    }
}