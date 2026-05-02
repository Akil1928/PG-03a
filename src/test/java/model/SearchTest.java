package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

    private int[] sortedArr;
    private int[] unsortedArr;

    @BeforeEach
    void setUp() {
        // For binary search tests, we need a sorted array
        sortedArr = util.Utility.generatedSorted(10, 100); // Generate a sorted array
        // For min/max tests, an unsorted array is fine
        unsortedArr = new Random().ints(9, 1, 100).toArray();
    }

    @Test
    void testFindMinMax() {
        int[] arr = {5, 2, 8, 1, 9, 4, 7, 3, 6};
        Search.MinMax minMax = Search.findMinMax(arr, 0, arr.length - 1);
        assertEquals(1, minMax.getMin());
        assertEquals(9, minMax.getMax());

        int[] singleElementArr = {42};
        minMax = Search.findMinMax(singleElementArr, 0, singleElementArr.length - 1);
        assertEquals(42, minMax.getMin());
        assertEquals(42, minMax.getMax());

        int[] twoElementArr = {10, 5};
        minMax = Search.findMinMax(twoElementArr, 0, twoElementArr.length - 1);
        assertEquals(5, minMax.getMin());
        assertEquals(10, minMax.getMax());
    }

    @Test
    void testArraysBinarySearch() {
        int valueToFind = sortedArr[sortedArr.length / 2]; // A value that exists
        int index = Arrays.binarySearch(sortedArr, valueToFind);
        assertTrue(index >= 0);
        assertEquals(valueToFind, sortedArr[index]);

        int valueNotFound = 999; // A value that does not exist
        index = Arrays.binarySearch(sortedArr, valueNotFound);
        assertTrue(index < 0);
    }

    @Test
    void testCollectionsBinarySearch() {
        List<Integer> list = Arrays.stream(sortedArr).boxed().toList();

        int valueToFind = sortedArr[sortedArr.length / 2]; // A value that exists
        int index = Collections.binarySearch(list, valueToFind);
        assertTrue(index >= 0);
        assertEquals(valueToFind, list.get(index));

        int valueNotFound = 999; // A value that does not exist
        index = Collections.binarySearch(list, valueNotFound);
        assertTrue(index < 0);
    }

    @Test
    void testRecursiveSearchBinarySearch() {
        int valueToFind = sortedArr[sortedArr.length / 2]; // A value that exists
        int index = Search.binarySearch(sortedArr, valueToFind, 0, sortedArr.length - 1);
        assertTrue(index >= 0);
        assertEquals(valueToFind, sortedArr[index]);

        int valueNotFound = 999; // A value that does not exist
        index = Search.binarySearch(sortedArr, valueNotFound, 0, sortedArr.length - 1);
        assertTrue(index < 0);
    }

    @Test
    void testIterativeSearchBinarySearch() {
        int valueToFind = sortedArr[sortedArr.length / 2]; // A value that exists
        int index = Search.binarySearchIterative(sortedArr, valueToFind);
        assertTrue(index >= 0);
        assertEquals(valueToFind, sortedArr[index]);

        int valueNotFound = 999; // A value that does not exist
        index = Search.binarySearchIterative(sortedArr, valueNotFound);
        assertTrue(index < 0);
    }

    // The original test methods, kept for reference but commented out their print statements
    // and adapted to be demonstration methods rather than unit tests.
    @Test
    void minMaxDemonstration() {
        int[] arr = new Random().ints(9, 1, 100).toArray();
        System.out.println("\n" + Arrays.toString(arr));
        Search.steps.clear(); // Assuming 'steps' is a static field for demonstration
        Search.MinMax minMax = Search.findMinMax(arr, 0, arr.length - 1);
        System.out.println("Pasos realizados por el algoritmo recursivo");
        System.out.println("Array min item: " + minMax.getMin());

        for (String s : Search.steps) {
            System.out.println(s);
        }
    }

    @Test
    void binarySearchDemonstration() {
        int[] arr = util.Utility.generatedSorted(100, 100);
        System.out.println("\n" + Arrays.toString(arr));

        for (int i = 0; i < 5; i++) { // Reduced iterations for demonstration
            int value = new Random().nextInt(100); //valor a buscar
            System.out.println(binSearchResult("java.util.Arrays", arr, value));
            System.out.println(binSearchResult("java.util.Collections", arr, value));
            System.out.println(binSearchResult("recursive model.Search", arr, value));
            System.out.println(binSearchResult("iterative model.Search", arr, value));
            System.out.println("_".repeat(50));
        }
    }

    private String binSearchResult(String searchType, int[] arr, int value) {
        int index = switch (searchType) {
            case "java.util.Arrays" -> Arrays.binarySearch(arr, value);
            case "java.util.Collections" -> {
                List<Integer> list = Arrays.stream(arr).boxed().toList();
                yield Collections.binarySearch(list, value);
            }
            case "recursive model.Search" -> Search.binarySearch(arr, value, 0, arr.length - 1);
            case "iterative model.Search" -> Search.binarySearchIterative(arr, value);
            default -> 0;
        };
        return index < 0 ? searchType + " value [" + value + "] not found"
                : searchType + " value [" + value + "] found at index " + index;
    }
}
