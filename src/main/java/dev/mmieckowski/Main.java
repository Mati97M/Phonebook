package dev.mmieckowski;

import dev.mmieckowski.algorithm.execution.AlgorithmExecutor;
import dev.mmieckowski.algorithm.search.BinarySearch;
import dev.mmieckowski.algorithm.search.JumpSearch;
import dev.mmieckowski.algorithm.search.LinearSearch;
import dev.mmieckowski.algorithm.search.MapSearch;
import dev.mmieckowski.algorithm.sort.BubbleSort;
import dev.mmieckowski.algorithm.sort.QuickSort;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String dataDir = "src/main/resources/small_directory.txt";
        String targetsDir = "src/main/resources/small_find.txt";

        DataLoader dataLoader = new DataLoader();
        List<PersonPhone> personPhoneList = dataLoader.getDataFromFile(dataDir);
        List<String> targets = dataLoader.getTargetsFromFile(targetsDir);


        AlgorithmExecutor bubbleSortJumpSearchExecutor = new AlgorithmExecutor();
        bubbleSortJumpSearchExecutor.setPersonPhoneList(new ArrayList<>(personPhoneList));
        bubbleSortJumpSearchExecutor.setTargetsList(targets);

        //linear search
        long linearSearchMilis = bubbleSortJumpSearchExecutor.executeSearch(new LinearSearch());
        System.out.println();

        //bubbleSort + JumpSearch
        bubbleSortJumpSearchExecutor.executeSortSearch(new BubbleSort(), new JumpSearch(), linearSearchMilis);
        System.out.println();
        System.out.println();

        AlgorithmExecutor quickSortBinarySearchExecutor = new AlgorithmExecutor();
        quickSortBinarySearchExecutor.setPersonPhoneList(new ArrayList<>(personPhoneList));
        quickSortBinarySearchExecutor.setTargetsList(targets);

        //quickSort + BinarySearch
        quickSortBinarySearchExecutor.executeSortSearch(new QuickSort(), new BinarySearch(), linearSearchMilis);
        System.out.println();
        System.out.println();

        //map - hashtable
        AlgorithmExecutor mapSearchExecutor = new AlgorithmExecutor();
        mapSearchExecutor.setPersonPhoneList(new ArrayList<>(personPhoneList));
        mapSearchExecutor.setTargetsList(targets);
        mapSearchExecutor.executeSearch(new MapSearch());
    }


}

