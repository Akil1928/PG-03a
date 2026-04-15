package model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {
    @Test
    void minMaxTest() {
        int[] arr = new Random().ints(9, 1, 100).toArray();
        System.out.println("\n" + Arrays.toString(arr));
        Search.steps.clear();//limpiamos el arrayList
        Search.MinMax minMax = Search.findMinMax(arr, 0, arr.length - 1);
        System.out.println("Pasos realizados por el algoritmo recursivo");
        System.out.println("Array min item: " + minMax.getMin());

        //Mostramos el contenido del arrayList
        for (String s : Search.steps) {
            System.out.println(s);
        }
    }

    @Test
    void binarySearchTest() {
        int[] arr = util.Utility.generatedSorted(100, 100);
        System.out.println("\n" + Arrays.toString(arr));

        for (int i = 0; i < 20; i++) {
            int value = new Random().nextInt(100); //valor a buscar
            System.out.println(binSearchResult("java.util.Arrays", arr, value)); //Arrays.binarySearch(arr, value);
            System.out.println(binSearchResult("java.util.Collections", arr, value)); //Collections.binarySearch(list, value);
            System.out.println(binSearchResult("recursive model.Search", arr, value)); //Search.binarySearch(arr, value, 0, arr.length - 1);
            System.out.println(binSearchResult("iterative model.Search", arr, value)); //Search.binarySearch(arr, value); 
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
        return index < 0 ? searchType+"value [" + value + "] index==" + index
                :searchType+ "value [" + value + "] not found";
    }
}

