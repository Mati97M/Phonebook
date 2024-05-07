package dev.mmieckowski.algorithm.search;

import static dev.mmieckowski.utilities.Utilities.getSortedList;
import static dev.mmieckowski.utilities.Utilities.getTargets;
import static dev.mmieckowski.utilities.Utilities.getUnsortedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class JumpSearchTest {
    private JumpSearch jumpSearch;


    @BeforeEach
    void prepareInstance() {
        jumpSearch = new JumpSearch();
    }

    @Test
    void searchTestWhenListIsUnsortedTest() {
        List<String> targets = getTargets();
        long expected = targets.size();

        long found = jumpSearch.search(targets, getUnsortedList());

        assertNotEquals(expected, found);
    }

    @Test
    void searchTestWhenListIsSortedTest() {
        List<String> targets = getTargets();
        long expected = targets.size();

        long found = jumpSearch.search(targets, getSortedList());

        assertEquals(expected, found);
    }

    @Test
    void getNameTest() {
        assertEquals("jump search", jumpSearch.getName());
    }
}