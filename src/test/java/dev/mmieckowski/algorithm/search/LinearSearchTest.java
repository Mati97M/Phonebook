package dev.mmieckowski.algorithm.search;

import static dev.mmieckowski.utilities.Utilities.getTargets;
import static dev.mmieckowski.utilities.Utilities.getUnsortedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import dev.mmieckowski.PersonPhone;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

class LinearSearchTest {
    private static LinearSearch linearSearch;
    private static List<PersonPhone> personPhoneList;
    private static List<String> targets;

    @BeforeAll
    static void setUp() {
        linearSearch = new LinearSearch();
        personPhoneList = getUnsortedList();
        targets = getTargets();
    }

    @Test
    void searchTest() {
        List<String> targets = getTargets();
        int expectedFound = 5;

        long found = linearSearch.search(targets, personPhoneList);

        assertEquals(expectedFound, found);

    }

    @Test
    void getNameTest() {
        assertEquals("linear search", linearSearch.getName());
    }
}