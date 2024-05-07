package dev.mmieckowski.algorithm.execution;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;
import dev.mmieckowski.algorithm.search.SearchAlgorithm;
import dev.mmieckowski.algorithm.sort.SortAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.naming.TimeLimitExceededException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AlgorithmExecutorTest {

    private static final long abortAfterMsFactor = 1000;
    private AlgorithmExecutor algorithmExecutor;
    @Mock
    private SearchAlgorithm searchAlgorithm;
    @Mock
    private SortAlgorithm sortAlgorithm;

    @BeforeEach
    void setUp() {
        algorithmExecutor = new AlgorithmExecutor();
    }

    @DisplayName("executeSearch throw Exception when targets list is empty Test")
    @Test
    void executeSearchWillThrowExceptionWhenTargetListIsEmptyTest() {
        assertThrows(MissingDataException.class,
                () -> algorithmExecutor.executeSearch(searchAlgorithm));
        verifyNoInteractions(sortAlgorithm, searchAlgorithm);
    }

    @DisplayName("executeSortSearch throw Exception when targets list is empty Test")
    @Test
    void executeSortSearchWillThrowExceptionWhenTargetListIsEmptyTest() {
        assertThrows(MissingDataException.class,
                () -> algorithmExecutor.executeSortSearch(sortAlgorithm, searchAlgorithm, abortAfterMsFactor));
        verifyNoInteractions(sortAlgorithm, searchAlgorithm);
    }

    @DisplayName("Sort and search catches exception Test")
    @Test
    void executeSortSearchWillCatchExceptionWhenSortThrowsExceptionTest() throws TimeLimitExceededException {
        algorithmExecutor.setTargetsList(List.of("Element1"));
        doThrow(new TimeLimitExceededException()).when(sortAlgorithm).sort(any(), anyLong());
        algorithmExecutor.executeSortSearch(sortAlgorithm, searchAlgorithm, abortAfterMsFactor);
    }
}