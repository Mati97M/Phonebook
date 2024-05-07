package dev.mmieckowski.algorithm.search;

import static dev.mmieckowski.utilities.Utilities.getTargets;
import static dev.mmieckowski.utilities.Utilities.getUnsortedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
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
import java.time.Duration;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MapSearchTest {
    private static List<PersonPhone> unsortedList;
    private static List<String> targets;
    @InjectMocks
    private MapSearch mapSearch;
    @Mock
    private Timer timer;

    @BeforeAll
    static void setUp() {
        unsortedList = getUnsortedList();
        targets = getTargets();
    }

    @Test
    void searchAndCreatingTimeTest() {
        long expected = targets.size();
        when(timer.restart()).thenReturn(Duration.ofMillis(10));

        long found = mapSearch.search(targets, unsortedList);

        assertEquals(expected, found);
        verify(timer, times(2)).restart();
        assertNotNull(mapSearch.getCreatingTime());
    }

    @Test
    void getNameTest() {
        assertEquals("hash table", mapSearch.getName());
    }

    @Test
    void whenSearchWasNotCalledThenGetCreatingTimeWhenReturnsNullTest() {
        assertNull(mapSearch.getCreatingTime());
    }
}