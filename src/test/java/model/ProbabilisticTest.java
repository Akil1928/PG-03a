package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ProbabilisticTest {

    private Probabilistic probabilistic;

    @BeforeEach
    void setUp() {
        probabilistic = new Probabilistic();
    }

    @Test
    void testRandomSearchFound() {
        int[] arr = {10, 20, 30, 40, 50};
        int valueToFind = 30;
        int maxAttempts = 10;
        int[] result = probabilistic.randomSearch(arr, valueToFind, maxAttempts);

        // Assert that the item was found (index is not -1)
        assertNotEquals(-1, result[0], "Item should be found");
        // Assert that attempts were made
        assertTrue(result[1] > 0, "Attempts should be greater than 0");
        // Assert that the found index contains the value
        assertEquals(valueToFind, arr[result[0]]);
    }

    @Test
    void testRandomSearchNotFound() {
        int[] arr = {10, 20, 30, 40, 50};
        int valueToFind = 99; // Not in the array
        int maxAttempts = 5;
        int[] result = probabilistic.randomSearch(arr, valueToFind, maxAttempts);

        // Assert that the item was not found
        assertEquals(-1, result[0], "Item should not be found");
        // Assert that all attempts were used
        assertEquals(maxAttempts, result[1], "All attempts should be used");
    }

    @Test
    void testMillerRabinPrimes() {
        String result;

        result = probabilistic.millerRabin("29341");
        assertTrue(result.contains("is probably prime."), "29341 should be probably prime");

        result = probabilistic.millerRabin("131071");
        assertTrue(result.contains("is probably prime."), "131071 should be probably prime");

        result = probabilistic.millerRabin("483647");
        assertTrue(result.contains("is probably prime."), "483647 should be probably prime");

        result = probabilistic.millerRabin("2147483647");
        assertTrue(result.contains("is probably prime."), "2147483647 should be probably prime");

        result = probabilistic.millerRabin("1000000007");
        assertTrue(result.contains("is probably prime."), "1000000007 should be probably prime");

        result = probabilistic.millerRabin("2305843009213693951");
        assertTrue(result.contains("is probably prime."), "2305843009213693951 should be probably prime");
    }

    @Test
    void testMillerRabinComposites() {
        String result;

        result = probabilistic.millerRabin("4"); // Smallest composite
        assertTrue(result.contains("is not prime."), "4 should be not prime");

        result = probabilistic.millerRabin("6");
        assertTrue(result.contains("is not prime."), "6 should be not prime");

        result = probabilistic.millerRabin("9");
        assertTrue(result.contains("is not prime."), "9 should be not prime");

        result = probabilistic.millerRabin("214748"); // From original test, known composite
        assertTrue(result.contains("is not prime."), "214748 should be not prime");

        result = probabilistic.millerRabin("1234545656"); // From original test, known composite
        assertTrue(result.contains("is not prime."), "1234545656 should be not prime");
    }
}
