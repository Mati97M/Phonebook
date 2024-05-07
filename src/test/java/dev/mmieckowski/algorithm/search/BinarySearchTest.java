package dev.mmieckowski.algorithm.search;

import static dev.mmieckowski.utilities.Utilities.getSortedList;
import static dev.mmieckowski.utilities.Utilities.getTargets;
import static dev.mmieckowski.utilities.Utilities.getUnsortedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import dev.mmieckowski.PersonPhone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class BinarySearchTest {
    private static List<PersonPhone> unsortedList;
    private static List<PersonPhone> sortedList;
    private static List<String> targets;
    private BinarySearch binarySearch;

    @BeforeAll
    static void setUp() {
        unsortedList = getUnsortedList();
        sortedList = getSortedList();
        targets = getTargets();
    }

    @BeforeEach
    void prepareInstance() {
        binarySearch = new BinarySearch();
    }

    @Test
    void searchTestWhenListIsUnsortedTest() {
        long expected = targets.size();

        long found = binarySearch.search(targets, unsortedList);

        assertNotEquals(expected, found);
    }

    @Test
    void searchTestWhenListIsSortedTest() {
        long expected = targets.size();

        long found = binarySearch.search(targets, sortedList);

        assertEquals(expected, found);
    }

    @Test
    void getNameTest() {
        assertEquals("binary search", binarySearch.getName());
    }
}