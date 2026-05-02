package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrimoTest {

    @Test
    void testEsPrimo_PrimeNumbers() {
        assertTrue(Primo.esPrimo(2), "2 should be prime");
        assertTrue(Primo.esPrimo(3), "3 should be prime");
        assertTrue(Primo.esPrimo(5), "5 should be prime");
        assertTrue(Primo.esPrimo(7), "7 should be prime");
        assertTrue(Primo.esPrimo(11), "11 should be prime");
        assertTrue(Primo.esPrimo(13), "13 should be prime");
        assertTrue(Primo.esPrimo(17), "17 should be prime");
        assertTrue(Primo.esPrimo(19), "19 should be prime");
        assertTrue(Primo.esPrimo(23), "23 should be prime");
        assertTrue(Primo.esPrimo(29), "29 should be prime");
        assertTrue(Primo.esPrimo(31), "31 should be prime");
        assertTrue(Primo.esPrimo(97), "97 should be prime");
        assertTrue(Primo.esPrimo(101), "101 should be prime");
        assertTrue(Primo.esPrimo(103), "103 should be prime");
        assertTrue(Primo.esPrimo(1000003), "1000003 should be prime"); // A larger prime
    }

    @Test
    void testEsPrimo_NonPrimeNumbers() {
        assertFalse(Primo.esPrimo(0), "0 should not be prime");
        assertFalse(Primo.esPrimo(1), "1 should not be prime");
        assertFalse(Primo.esPrimo(4), "4 should not be prime");
        assertFalse(Primo.esPrimo(6), "6 should not be prime");
        assertFalse(Primo.esPrimo(8), "8 should not be prime");
        assertFalse(Primo.esPrimo(9), "9 should not be prime");
        assertFalse(Primo.esPrimo(10), "10 should not be prime");
        assertFalse(Primo.esPrimo(12), "12 should not be prime");
        assertFalse(Primo.esPrimo(15), "15 should not be prime");
        assertFalse(Primo.esPrimo(20), "20 should not be prime");
        assertFalse(Primo.esPrimo(100), "100 should not be prime");
        assertFalse(Primo.esPrimo(99), "99 should not be prime");
        assertFalse(Primo.esPrimo(1000000), "1000000 should not be prime"); // A larger non-prime
    }

    @Test
    void testEsPrimo_NegativeNumbers() {
        assertFalse(Primo.esPrimo(-1), "-1 should not be prime");
        assertFalse(Primo.esPrimo(-2), "-2 should not be prime");
        assertFalse(Primo.esPrimo(-10), "-10 should not be prime");
    }
}
