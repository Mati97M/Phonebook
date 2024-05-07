package dev.mmieckowski.algorithm.execution;

import dev.mmieckowski.PersonPhone;
import dev.mmieckowski.algorithm.search.LinearSearch;
import dev.mmieckowski.algorithm.search.MapSearch;
import dev.mmieckowski.algorithm.search.SearchAlgorithm;
import dev.mmieckowski.algorithm.sort.SortAlgorithm;
import lombok.NoArgsConstructor;
import javax.naming.TimeLimitExceededException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmExecutor {
    private static final int ABORT_TIME_FACTOR = 10;
    private List<PersonPhone> personPhoneList;
    private List<String> targetsList;

    public AlgorithmExecutor() {
        this.personPhoneList = new ArrayList<>();
        this.targetsList = new ArrayList<>();
    }

    public long executeSearch(SearchAlgorithm alg) {
        if (targetsList.isEmpty()) {
            throw new MissingDataException("No data to perform search operation on.");
        }
        long foundNum;

        System.out.printf("Start searching (%s)...%n", alg.getName());
        Timer timer = new Timer();
        foundNum = alg.search(targetsList, personPhoneList);
        Duration duration = timer.restart();
        System.out.printf("Found %d / %d entries. Time taken: %d min. %02d sec. %02d ms.%n", foundNum, targetsList.size(), duration.toMinutes(), duration.toSecondsPart(), duration.toMillisPart());

        if (alg instanceof MapSearch mapSearch) {
            Duration creatingTime = mapSearch.getCreatingTime();
            System.out.printf("Creating time: %d min. %02d sec. %02d ms.%n", creatingTime.toMinutes(), creatingTime.toSecondsPart(), creatingTime.toMillisPart());
            Duration searchingTime = duration.minus(creatingTime);
            System.out.printf("Searching time: %d min. %02d sec. %02d ms.%n", searchingTime.toMinutes(), searchingTime.toSecondsPart(), searchingTime.toMillisPart());
        }
        return duration.toMillis();

    }

    public void executeSortSearch(SortAlgorithm sortAlgorithm, SearchAlgorithm searchAlgorithm, long abortAfterMsFactor) {
        long foundNum;
        Duration durationSortSearch;
        Duration sortDuration;
        Duration durationSearch;

        if (targetsList.isEmpty()) {
            throw new MissingDataException("No data to perform search operation on.");
        }
        System.out.printf("Start searching (%s + %s)...%n", sortAlgorithm.getName(), searchAlgorithm.getName());
        Timer timer = new Timer();
        try {
            sortAlgorithm.sort(personPhoneList, ABORT_TIME_FACTOR * abortAfterMsFactor);
        } catch (TimeLimitExceededException e) {
            sortDuration = timer.restart();
            foundNum = new LinearSearch().search(targetsList, personPhoneList);
            durationSearch = timer.restart();
            durationSortSearch = sortDuration.plus(durationSearch);
            generateReportSortSearch(foundNum, durationSortSearch, sortDuration, durationSearch, true);
            return;
        }
        sortDuration = timer.restart();
        foundNum = searchAlgorithm.search(targetsList, personPhoneList);
        durationSearch = timer.restart();
        durationSortSearch = sortDuration.plus(durationSearch);
        generateReportSortSearch(foundNum, durationSortSearch, sortDuration, durationSearch, false);

    }

    public void setPersonPhoneList(List<PersonPhone> personPhoneList) {
        this.personPhoneList = new ArrayList<>(personPhoneList);
    }

    public void setTargetsList(List<String> targetsList) {
        this.targetsList = new ArrayList<>(targetsList);
    }

    private void generateReportSortSearch(long foundNum, Duration durationSortSearch, Duration sortDuration, Duration durationSearch, boolean aborted) {
        System.out.printf("Found %d / %d entries. Time taken: %d min. %02d sec. %02d ms.", foundNum, targetsList.size(), durationSortSearch.toMinutes(), durationSortSearch.toSecondsPart(), durationSortSearch.toMillisPart());
        System.out.printf("%nSorting time: %d min. %02d sec. %02d ms.", sortDuration.toMinutes(), sortDuration.toSecondsPart(), sortDuration.toMillisPart());
        if (aborted) {
            System.out.print(" - STOPPED, moved to linear search");
        }
        System.out.printf("%nSearching time: %d min. %02d sec. %02d ms.", durationSearch.toMinutes(), durationSearch.toSecondsPart(), durationSearch.toMillisPart());
    }
}
